create or replace procedure scholarship
(
  P_MAJORNAME in STUDENT.MAJORNAME%TYPE,
  P_RGRADE in RCARD.RGRADE%TYPE,
  P_YESE in RCARD.YESE%TYPE
)
IS
  scholar NUMBER;
  scholar2 NUMBER;
BEGIN
  select MTUITION into scholar from MAJOR where MAJORNAME=P_MAJORNAME;
  select MTUITION into scholar2 from MAJOR where MAJORNAME=P_MAJORNAME;
  --수석 전액 장학금 
  scholar := scholar*0;
  update STUDENT set STUITION=scholar where STNUM in (select STNUM from RCARD natural join STUDENT where PCT = 
  (select max(PCT) from RCARD natural join STUDENT where MAJORNAME=P_MAJORNAME and GRADE=P_RGRADE and YESE=P_YESE) and MAJORNAME=P_MAJORNAME);
  --차석 반액 장학금
  scholar2 := scholar2/2;
  update STUDENT set STUITION=scholar2 where STNUM in 
  (select STNUM from RCARD where STNUM in 
  (select STNUM from 
  (select rownum AA, STNUM from 
  (select PCT,STNUM from RCARD natural join STUDENT where MAJORNAME=P_MAJORNAME and GRADE=P_RGRADE and YESE=P_YESE order by PCT desc))where AA=2));
commit;
end;
/