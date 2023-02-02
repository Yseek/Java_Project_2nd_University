--학기 등록시 RCARD 생성
create or replace trigger CRERCARD_TRI
AFTER
  insert on RSTUDENT
  for each ROW
BEGIN
  insert into RCARD(YESE,STNUM) values(:new.YESE, :new.STNUM);
  insert into SGRADE(YESE,STNUM) values(:new.YESE, :new.STNUM);
  insert into TTCREDIT(YESE,STNUM) values(:new.YESE, :new.STNUM);
  insert into TGCREDIT(YESE,STNUM) values(:new.YESE, :new.STNUM);
  insert into TOTALCREDIT(YESE,STNUM) values(:new.YESE, :new.STNUM);
  update STUDENT set STUITION=(select MTUITION from MAJOR where MAJORNAME=(select MAJORNAME from STUDENT WHERE
  STNUM=:new.STNUM)) where STNUM=:new.STNUM;
END;
/

--수강신청시 RCARD TSUB업데이트,교수가 평가할 수 있는 테이블(PROENTER),백분율(PCTT) 에 입력
create or replace TRIGGER RCARDNUM_TRI
AFTER
  insert on Y_S_RCARD
  for each ROW
DECLARE
  R_YESE Y_S_RCARD.YESE%TYPE;
  R_STNUM Y_S_RCARD.STNUM%TYPE;
  R_CCODE Y_S_RCARD.CCODE%TYPE;
BEGIN
  insert into RCARDNUM(YESE,STNUM,CCODE) values(:new.YESE,:new.STNUM,:new.CCODE);
  insert into PROENTER(YESE,STNUM,CCODE) values(:new.YESE,:new.STNUM,:new.CCODE);
  insert into PCTT(YESE,STNUM,CCODE) values(:new.YESE, :new.STNUM,:new.CCODE);
  update RCARD set TSUB = (select count(*) from RCARDNUM where YESE = :new.YESE and STNUM = :new.STNUM)
  where YESE =:new.YESE and STNUM = :new.STNUM;
END;
/

--수강신청 취소시 RCARD TSUB업데이트 and PROENTER,PCTT,RCARDNUM 테이블에서 삭제
create or replace TRIGGER RCARDNUM_DELETE_TRI
AFTER
  delete on Y_S_RCARD
  for each ROW
DECLARE
  R_YESE Y_S_RCARD.YESE%TYPE;
  R_STNUM Y_S_RCARD.STNUM%TYPE;
  R_CCODE Y_S_RCARD.CCODE%TYPE;
BEGIN
  R_YESE := :old.YESE;
  R_STNUM := :old.STNUM;
  R_CCODE := :old.CCODE;
  delete from RCARDNUM where YESE=R_YESE and STNUM = R_STNUM and CCODE = R_CCODE;
  update RCARD set TSUB = (select count(*) from RCARDNUM where YESE = R_YESE and STNUM = R_STNUM)
  where YESE = R_YESE and STNUM = R_STNUM;
  delete from PROENTER where YESE = R_YESE and STNUM = R_STNUM and CCODE = R_CCODE; 
  delete from PCTT where YESE = R_YESE and STNUM = R_STNUM and CCODE = R_CCODE; 
END;
/

--학점부여시 RCARD 이수과목 , 취득학점 ,평점 평균 업데이트
create or replace trigger RCARDNUM_TRI2
AFTER
  update of GP on Y_S_RCARD
  for EACH ROW
DECLARE
  R_YESE Y_S_RCARD.YESE%TYPE;
  R_STNUM Y_S_RCARD.STNUM%TYPE;
  R_CCODE Y_S_RCARD.CCODE%TYPE;
BEGIN
  R_YESE := :new.YESE;
  R_STNUM := :new.STNUM;
  R_CCODE := :new.CCODE;
  update RCARDNUM set GP = :new.GP where YESE = R_YESE and STNUM = R_STNUM and CCODE = R_CCODE;
  update RCARDNUM set GPPER = :new.GP*(select CGRADE from CLASS natural join PCTT where STNUM = :new.STNUM and ccode= :new.ccode and yese=:new.yese) 
  where YESE = R_YESE and STNUM = R_STNUM and CCODE = R_CCODE;
  update RCARD set CSUB = (select count(*) from RCARDNUM where YESE = R_YESE and STNUM = R_STNUM and GP >0.5)
  where YESE = R_YESE and STNUM = R_STNUM; 
  update TGCREDIT set GCREDIT = (select sum(CGRADE) from CLASS where CCODE in (select CCODE from RCARDNUM where YESE = R_YESE and STNUM = R_STNUM and GP >0.5))
  where YESE = R_YESE and STNUM = R_STNUM; 
  update RCARD set GCREDIT = (select sum(CGRADE) from CLASS where CCODE in (select CCODE from RCARDNUM where YESE = R_YESE and STNUM = R_STNUM and GP >0.5))
  where YESE = R_YESE and STNUM = R_STNUM; 
  update RCARD set GPA = (select sum(GPPER) from RCARDNUM where YESE = R_YESE and STNUM = R_STNUM)/(select sum(CGRADE) from CLASS natural join PCTT where STNUM = :new.STNUM and yese=:new.yese)
  where YESE = R_YESE and STNUM = R_STNUM; 
  update RCARD set TGPA = (select sum(GPPER) from RCARDNUM where YESE <= R_YESE and STNUM = R_STNUM)/(select sum(CGRADE) from CLASS natural join PCTT where STNUM = :new.STNUM and yese<=:new.yese)
  where YESE = R_YESE and STNUM = R_STNUM; 
END;
/

--RCARD에서 취득학점 변경시 STUDENT 총취득학점 업데이트
create or replace trigger TOTALCREDIT_TRI
AFTER
  update of GCREDIT on TGCREDIT
  for each ROW
DECLARE
  R_STNUM TGCREDIT.STNUM%TYPE;
BEGIN
  update TOTALCREDIT set GCREDIT = :new.GCREDIT where STNUM= :new.STNUM and YESE = :new.YESE;
  update TTCREDIT set TCREDIT = (select sum(GCREDIT) from TOTALCREDIT where STNUM=:new.STNUM and YESE <= :new.YESE)
  where STNUM= :new.STNUM and YESE = :new.YESE;
  update STUDENT set TCREDIT = (select sum(GCREDIT) from TOTALCREDIT where STNUM=:new.STNUM)
  where STNUM= :new.STNUM;
end;
/

--학년 배정 트리거 
create or replace trigger GRADE_TRI2
AFTER
  update of TCREDIT on TTCREDIT
  for each ROW
DECLARE
  R_STNUM STUDENT.STNUM%TYPE;
BEGIN
  R_STNUM := :new.STNUM;
  update SGRADE set GRADE=1 where STNUM=R_STNUM and YESE =:new.YESE and (select count(*) from RCARD where STNUM=R_STNUM and YESE<=:new.YESE)>0;
  update SGRADE set GRADE=2 where STNUM=R_STNUM and YESE =:new.YESE and (select count(*) from RCARD where STNUM=R_STNUM and YESE<=:new.YESE)>2;
  update SGRADE set GRADE=3 where STNUM=R_STNUM and YESE =:new.YESE and (select count(*) from RCARD where STNUM=R_STNUM and YESE<=:new.YESE)>4;
  update SGRADE set GRADE=4 where STNUM=R_STNUM and YESE =:new.YESE and (select count(*) from RCARD where STNUM=R_STNUM and YESE<=:new.YESE)>6;
end;
/

-- 학년을 테이블에 배분 
create or replace trigger GRADE_TRI4
AFTER
  update of GRADE on SGRADE
  for each ROW
DECLARE
  R_YESE SGRADE.YESE%TYPE;
  R_STNUM SGRADE.STNUM%TYPE;
BEGIN
  R_STNUM := :new.STNUM;
  R_YESE := :new.YESE;
  update STUDENT set GRADE = :new.GRADE where STNUM = R_STNUM;
  update RCARD set RGRADE = :new.GRADE where YESE = R_YESE and STNUM = R_STNUM;
end;
/


--교수가 학점을 부여하면 Y_S_RCARD에 0.5단위로 평점이 부여되고 등급과 환산점수 입력
create or replace trigger PROENTER_TRI
AFTER
  update of GP on PROENTER
  for EACH ROW
DECLARE
  R_YESE PROENTER.YESE%TYPE;
  R_STNUM PROENTER.STNUM%TYPE;
  R_CCODE PROENTER.CCODE%TYPE;
BEGIN
  R_YESE := :new.YESE;
  R_STNUM := :new.STNUM;
  R_CCODE := :new.CCODE;
  update Y_S_RCARD set GP = (TRUNC((:new.GP+0.8)*2)/2-0.5)
  where YESE = R_YESE and STNUM = R_STNUM and CCODE = R_CCODE;
  update Y_S_RCARD set SGRADE = (select SGRADE from PERTABLE where GP=:new.GP)
  where YESE = R_YESE and STNUM = R_STNUM and CCODE = R_CCODE; 
  update Y_S_RCARD set SCALED = (select SCALED from PERTABLE where GP=:new.GP)
  where YESE = R_YESE and STNUM = R_STNUM and CCODE = R_CCODE; 
END;
/

-- 환산점수가 업데이트 되면 학기별 백분율 입력
create or replace trigger PCR_TRI
AFTER
  update of SCALED on Y_S_RCARD
  for EACH ROW
DECLARE
  R_YESE Y_S_RCARD.YESE%TYPE;
  R_STNUM Y_S_RCARD.STNUM%TYPE;
  R_CCODE Y_S_RCARD.CCODE%TYPE;
BEGIN
  R_YESE := :new.YESE;
  R_STNUM := :new.STNUM;
  R_CCODE := :new.CCODE;
  update PCTT set PCT = :new.SCALED where YESE = R_YESE and STNUM = R_STNUM and CCODE = R_CCODE;
  update PCTT set PCTPER = :new.SCALED*(select CGRADE from CLASS natural join PCTT where STNUM = :new.STNUM and ccode= :new.ccode and yese=:new.yese)
  where YESE = R_YESE and STNUM = R_STNUM and CCODE = R_CCODE;
  update RCARD set PCT = (select sum(PCTPER) from PCTT where YESE = R_YESE and STNUM = R_STNUM)/(select sum(CGRADE) from CLASS natural join PCTT where STNUM = :new.STNUM and yese=:new.yese)
  where YESE = R_YESE and STNUM = R_STNUM;
END;
/

