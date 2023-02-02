package univer.db;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

class Table {
  PreparedStatement pstm1, pstm2, pstm3,pstm7,pstm8,adSemePstm,adSemeRePstm,giveGpPstm,giveGpRePstm,checkYesePstm, checkClassPstm, checkClassMajorPstm,checkMajorPstm
  ,checkEnrolPstm,checkEnrolYesePstm, checkRcardPstm,checkYeseCardPstm,checkProEntPstm,checkProEntYesePstm ,loginPstm,loginMPstm, stuDataPstm, totalRcardPstm,totalRcardYesePstm
  ,makeBoxPstm,checkStuYeseClassPstm,graduPstm,moneyPstm, applyClassPstm,deleteApplyPstm, maxApplyPstm,applyXPstm;
  CallableStatement cstmt;
  String sql1="select STNUM 학번, NAME 이름, EMAIL 이메일, MOBILE 연락처, TCREDIT 총취득학점, GRADE 학년, MAJORNAME 학과, STUITION 등록금  from STUDENT";
  String checkYeseStr= "select YESE 개설학기 from YEAR_SEME";
  String sql2="select STNUM 학번, YESE 학기 from RCARD where STNUM=? order by YESE";
  String sql3="select YESE 학기 ,STNUM 학번, CCODE 강의코드 from PROENTER where STNUM=? and YESE=?";
  String checkClassMajorStr="select CCODE 강좌코드, CNAME 과목명, CATEGORY 카테고리, CGRADE 학점, CPNAME 교수명, MAJORNAME 개설학과 from CLASS where MAJORNAME=?";
  String checkMajorStr="select MAJORNAME 학과명, MTUITION 학과등록금, G_CREDIT 졸업요건학점 from MAJOR";
  String sql7="select MAJORNAME 학과, GRADE 학년, YESE 학기 from STUDENT natural join RCARD where YESE=? order by YESE, GRADE";
  String sql8="insert into STUDENT(STNUM, NAME, EMAIL, MOBILE, STUITION, MAJORNAME) values(?, ? , ? , ?, ?, ?)";
  String adSeme="insert into RSTUDENT(STNUM, YESE) values(?,?)";
  String adSemeRe="select YESE 학기, STNUM 학번, NAME 이름 from RCARD natural join STUDENT where STNUM=?";
  String giveGp="update PROENTER set GP=? where YESE =? and STNUM=? and CCODE=?";
  String giveGpRe="select YESE 학기, NAME 이름, RGRADE 학년, TSUB 총과목, CSUB 이수과목, GCREDIT 취득학점, GPA 평점평균, TGPA 누적평점평균, PCT 백분율 from RCARD natural join STUDENT where STNUM=? and YESE=?";
  String checkClassStr="select CCODE 강좌코드, CNAME 과목명, CATEGORY 카테고리, CGRADE 학점, CPNAME 교수명, MAJORNAME 개설학과 from CLASS";
  String checkEnrolStr="select YESE 학기, GP 평점, SGRADE 등급, SCALED 환산점수, CCODE 강의코드 from Y_S_RCARD where STNUM=?";
  String checkEnrolYeseStr="select YESE 학기, GP 평점, SGRADE 등급, SCALED 환산점수, CCODE 강의코드 from Y_S_RCARD where STNUM=? and YESE=?";
  String checkRcardStr="select YESE 학기, RGRADE 학년, TSUB 총과목, CSUB 이수과목, GCREDIT 이수학점, GPA 평점평균, TGPA 누적평점평균, PCT 백분율 from RCARD where STNUM=? order by 1";
  String checkYeseCardStr="select CCODE 강좌코드, CNAME 과목명, CATEGORY 이수구분, CGRADE 학점, GP 평점, SGRADE 등급, PNAME 교수명, MAJORNAME 개설학과 from CLASS natural join PROFESSOR natural join Y_S_RCARD where STNUM=? and YESE=?";
  String checkProEntStr="select YESE 학기 ,GP 원점수, CCODE 과목명 from PROENTER where STNUM=?";
  String checkProEntYeseStr="select YESE 학기 ,GP 원점수, CCODE 과목명 from PROENTER where STNUM=? and YESE=?";
  String callSchol="call scholarship(?, ?, ?)";
  String loginStr="select * from STUDENT where NAME=? and STNUM=?";
  String loginMStr="select * from MANAGER where MNAME=? and MNUM=?";
  String stuDataStr="select NAME, STNUM, MAJORNAME from STUDENT where STNUM=?";
  String totalRcardStr="select TCREDIT 총취득학점, trunc((select sum(GPPER) from RCARDNUM where STNUM = ?)/(select sum(CGRADE) from CLASS natural join PCTT where STNUM =?),3) 평균평점, trunc((select sum(PCTPER) from PCTT where STNUM = ?)/(select sum(CGRADE) from CLASS natural join PCTT where STNUM =?),2) 백분율 from STUDENT natural join RCARD where STNUM=? group by TCREDIT";
  String totalRcardYeseStr="select TSUB 총과목, CSUB 이수과목, GCREDIT 취득학점, GPA 평점평균, PCT 총평점 from RCARD where STNUM=? and YESE=?";
  String makeBoxStr="select YESE from RCARD where STNUM=? order by YESE";
  String checkStuYeseClassStr="select CCODE 강좌코드, CGRADE 학점, MAJORNAME 전공, (select GRADE from SGRADE where STNUM=? and YESE=?) 학년 , CATEGORY 이수구분, CNAME 강좌명 ,CPNAME 담당교수 from ClASS where CCODE in (select CCODE from Y_S_RCARD where CCODE in (select CCODE from Y_S_RCARD where STNUM=? and YESE=?) and STNUM=? and YESE=?)";
  String graduStr="select TCREDIT, G_CREDIT from STUDENT natural join MAJOR where STNUM=?";
  String moneyStr="select MTUITION 등록금, MTUITION-STUITION 장학금 from STUDENT natural join MAJOR where STNUM=?";
  String applyClassStr="insert into Y_S_RCARD(CCODE, STNUM, YESE) values (?,?,?)";
  String deleteApplyStr="delete Y_S_RCARD where STNUM=? and YESE=? and CCODE=?";
  String maxApplyStr ="select MAX_C from RSTUDENT where STNUM=? and YESE=?";
  String applyXStr="select sum(GP) from Y_S_RCARD where STNUM=? and YESE=?";
  
  String adminStu, adminSeme, giveGpcheck , stuName, stuStnum,stuMajorName;
  ConnetDB db;

  Table(ConnetDB db){
    this.db = db;
    try {
      pstm1 = db.con.prepareStatement(sql1);
      pstm2 = db.con.prepareStatement(sql2);
      pstm3 = db.con.prepareStatement(sql3);
      pstm7 = db.con.prepareStatement(sql7);
      pstm8 = db.con.prepareStatement(sql8);
      adSemePstm = db.con.prepareStatement(adSeme);
      adSemeRePstm = db.con.prepareStatement(adSemeRe);
      giveGpPstm = db.con.prepareStatement(giveGp);
      giveGpRePstm= db.con.prepareStatement(giveGpRe);
      checkYesePstm=db.con.prepareStatement(checkYeseStr);
      checkClassPstm = db.con.prepareStatement(checkClassStr);
      checkClassMajorPstm = db.con.prepareStatement(checkClassMajorStr);
      checkMajorPstm = db.con.prepareStatement(checkMajorStr);
      checkEnrolPstm = db.con.prepareStatement(checkEnrolStr);
      checkEnrolYesePstm = db.con.prepareStatement(checkEnrolYeseStr);
      checkRcardPstm = db.con.prepareStatement(checkRcardStr);
      checkYeseCardPstm = db.con.prepareStatement(checkYeseCardStr);
      checkProEntPstm = db.con.prepareStatement(checkProEntStr);
      checkProEntYesePstm= db.con.prepareStatement(checkProEntYeseStr);
      loginPstm= db.con.prepareStatement(loginStr);
      loginMPstm = db.con.prepareStatement(loginMStr);
      stuDataPstm= db.con.prepareStatement(stuDataStr);
      totalRcardPstm = db.con.prepareStatement(totalRcardStr);
      totalRcardYesePstm = db.con.prepareStatement(totalRcardYeseStr);
      makeBoxPstm = db.con.prepareStatement(makeBoxStr);
      checkStuYeseClassPstm = db.con.prepareStatement(checkStuYeseClassStr);
      graduPstm = db.con.prepareStatement(graduStr);
      moneyPstm = db.con.prepareStatement(moneyStr);
      applyClassPstm= db.con.prepareStatement(applyClassStr);
      deleteApplyPstm = db.con.prepareStatement(deleteApplyStr);
      maxApplyPstm = db.con.prepareStatement(maxApplyStr);
      applyXPstm=db.con.prepareStatement(applyXStr);
      cstmt = db.con.prepareCall(callSchol);
    } catch (SQLException se) {
    }
    new Main(this);
  }
  Vector<String> columnNameList , columnNameListUp, comboBox;
  Vector<Object> rowData, rowDataUp;
  Vector<Vector<Object>> rowDatatwo, rowDatatwoUp;
  int i,j,k,money,maxApply;
  String tuition, scholarship;


  int applyX(long stnum, String yese){
    try {
      applyXPstm.setLong(1, stnum);
      applyXPstm.setString(2, yese);
      ResultSet rs = applyXPstm.executeQuery();
      rs.next();
      if(rs.getString(1)!=null){
        rs.close();
        return 1;
      }else{
        rs.close();
        return -1;
      }
    } catch (SQLException se) {
      System.out.println(se);
    }
    return 0;
  }
  //수강최대학점
  void maxApply(long stnum, String yese){
    try {
      maxApplyPstm.setLong(1, stnum);
      maxApplyPstm.setString(2, yese);
      ResultSet rs = maxApplyPstm.executeQuery();
      if(rs.next()){
        maxApply = rs.getInt(1);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }
  //수강취소
  void deleteApply(long stnum, String yese, String ccode){
    try {
      System.out.println(stnum + yese + ccode);
      deleteApplyPstm.setLong(1, stnum);
      deleteApplyPstm.setString(2, yese);
      deleteApplyPstm.setString(3, ccode);
      int i = deleteApplyPstm.executeUpdate();
      if(i>0){
        System.out.println(i+"행 업데이트");
      }
    } catch (SQLException se) {
    }
  }
  //수강신청 
  void applyClass(String ccode, long stnum, String yese){
    try {
      applyClassPstm.setString(1, ccode);
      applyClassPstm.setLong(2, stnum);
      applyClassPstm.setString(3,yese);
      int i = applyClassPstm.executeUpdate();
      if(i>=1){
        System.out.println("굿");
      }
    } catch (SQLException se) {
    }
  }
  //장학금 확인
  void money(long stnum){
    try {
      moneyPstm.setLong(1, stnum);
      ResultSet rs = moneyPstm.executeQuery();
      if(rs.next()){
        tuition = rs.getString(1);
        scholarship = rs.getString(2);
        money = rs.getInt(1)-rs.getInt(2);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }

  // 졸업요건 확인
  void gradu(long stnum){
    try {
      graduPstm.setLong(1, stnum);
      ResultSet rs = graduPstm.executeQuery();
      if(rs.next()){
        i = rs.getInt(1);
        j = rs.getInt(2);
      }
      rs.close();
    } catch (SQLException se) {
    }
    if(i>=j){
      k=0;
    }else{
      k=j-i;
    }
  }

  //수강신청 확인 테이블
  void applycheckStuYeseClass(long stnum, String yese){
    columnNameListUp = new Vector<String>();
    rowDatatwoUp = new Vector<Vector<Object>>();
    try {
      checkStuYeseClassPstm.setLong(1, stnum);
      checkStuYeseClassPstm.setString(2,yese);
      checkStuYeseClassPstm.setLong(3, stnum);
      checkStuYeseClassPstm.setString(4,yese);
      checkStuYeseClassPstm.setLong(5, stnum);
      checkStuYeseClassPstm.setString(6,yese);
      ResultSet rs = checkStuYeseClassPstm.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameListUp.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowDataUp = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowDataUp.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwoUp.add(rowDataUp);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }
  //수강확인 테이블
  void checkStuYeseClass(long stnum, String yese){
    columnNameList = new Vector<String>();
    rowDatatwo = new Vector<Vector<Object>>();
    try {
      checkStuYeseClassPstm.setLong(1, stnum);
      checkStuYeseClassPstm.setString(2,yese);
      checkStuYeseClassPstm.setLong(3, stnum);
      checkStuYeseClassPstm.setString(4,yese);
      checkStuYeseClassPstm.setLong(5, stnum);
      checkStuYeseClassPstm.setString(6,yese);
      ResultSet rs = checkStuYeseClassPstm.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameList.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowData = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowData.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwo.add(rowData);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }
  //수강확인 콤보박스 생성
  void makeBox(long stnum){
    comboBox = new Vector<String>();
    try {
      makeBoxPstm.setLong(1, stnum);
      ResultSet rs = makeBoxPstm.executeQuery();
      while(rs.next()){
        comboBox.add(rs.getString(1));
      }
      rs.close();
    } catch (SQLException se) {
    }
  }

  //RCARD 학기별
  void totalRcardYese(long stnum, String yese){
    columnNameListUp = new Vector<String>();
    rowDatatwoUp = new Vector<Vector<Object>>();
    try {
      totalRcardYesePstm.setLong(1, stnum);
      totalRcardYesePstm.setString(2,yese);
      ResultSet rs = totalRcardYesePstm.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameListUp.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowDataUp = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowDataUp.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwoUp.add(rowDataUp);
      }
      rs.close();
    } catch (SQLException se){
    }
    new ReCardWindow(this);
  }
  //RCARD 상단 테이블 추출
  void totalRcard(long stnum){
    columnNameListUp = new Vector<String>();
    rowDatatwoUp = new Vector<Vector<Object>>();
    try {
      totalRcardPstm.setLong(1, stnum);
      totalRcardPstm.setLong(2, stnum);
      totalRcardPstm.setLong(3, stnum); 
      totalRcardPstm.setLong(4, stnum);
      totalRcardPstm.setLong(5, stnum);
      ResultSet rs = totalRcardPstm.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameListUp.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowDataUp = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowDataUp.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwoUp.add(rowDataUp);
      }
      rs.close();
    } catch (SQLException se){
    }
  }

  //학생데이터 추출(Student 화면)
  void stuData(long stnum){
    try {
      stuDataPstm.setLong(1, stnum);
      ResultSet rs = stuDataPstm.executeQuery();
      if(rs.next()){
        stuName = rs.getString(1);
        stuStnum = rs.getString(2);
        stuMajorName = rs.getString(3);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }

  //로그인 체크
  int login(String name, long stnum){
    try{
      loginPstm.setString(1,name);
      loginPstm.setLong(2, stnum);
      ResultSet rs = loginPstm.executeQuery();
      if(rs.next()){
        stuData(stnum);
        new Student(this);
        rs.close();
        return 1;
      }else{
        rs.close();
        return -1;
      }
      
    }catch(SQLException se){
    }
    return 0;
  }

  int login(String name, String pw){
    try{
      loginMPstm.setString(1,name);
      loginMPstm.setString(2, pw);
      ResultSet rs = loginMPstm.executeQuery();
      if(rs.next()){
        new InsertData(this);
        rs.close();
        return 1;
      }else{
        rs.close();
        return -1;
      }
    }catch(SQLException se){}
    return 0;
  }

  //특정학생원점수 학기별 조회
  void checkProEntYese(long stnum, String yese){
    columnNameList = new Vector<String>();
    rowDatatwo = new Vector<Vector<Object>>();
    try {
      checkProEntYesePstm.setLong(1, stnum);
      checkProEntYesePstm.setString(2,yese);
      ResultSet rs = checkProEntYesePstm.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameList.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowData = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowData.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwo.add(rowData);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }

  //특정학생원점수 조회
  void checkProEnt(long stnum){
    columnNameList = new Vector<String>();
    rowDatatwo = new Vector<Vector<Object>>();
    try {
      checkProEntPstm.setLong(1, stnum);
      ResultSet rs = checkProEntPstm.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameList.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowData = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowData.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwo.add(rowData);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }

  //특정 학기/학년별 성적조회
  void checkYeseCard(long stnum, String yese){
    columnNameList = new Vector<String>();
    rowDatatwo = new Vector<Vector<Object>>();
    try {
      checkYeseCardPstm.setLong(1, stnum);
      checkYeseCardPstm.setString(2, yese);
      ResultSet rs = checkYeseCardPstm.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameList.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowData = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowData.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwo.add(rowData);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }
  //특정학생성적정보
  void checkRcard(long stnum){
    columnNameList = new Vector<String>();
    rowDatatwo = new Vector<Vector<Object>>();
    try {
      checkRcardPstm.setLong(1, stnum);
      ResultSet rs = checkRcardPstm.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameList.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowData = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowData.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwo.add(rowData);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }

  //특정학생 수강신청 내역 조회
  void checkEnrol(long stnum){
    columnNameList = new Vector<String>();
    rowDatatwo = new Vector<Vector<Object>>();
    try {
      checkEnrolPstm.setLong(1, stnum);
      ResultSet rs = checkEnrolPstm.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameList.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowData = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowData.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwo.add(rowData);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }

  //특정학생 특정학기 수강내역 조회
  void checkEnrolYese(long stnum, String yese){
    columnNameList = new Vector<String>();
    rowDatatwo = new Vector<Vector<Object>>();
    try {
      checkEnrolYesePstm.setLong(1, stnum);
      checkEnrolYesePstm.setString(2, yese);
      ResultSet rs = checkEnrolYesePstm.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameList.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowData = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowData.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwo.add(rowData);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }
  //개설학과 조회
  void checkMajor(){
    columnNameList = new Vector<String>();
    rowDatatwo = new Vector<Vector<Object>>();
    try {
      ResultSet rs = checkMajorPstm.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameList.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowData = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowData.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwo.add(rowData);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }
  Vector<String> majorName;
  //
  void checkMajorBox(){
    majorName = new Vector<String>();
    try {
      ResultSet rs = checkMajorPstm.executeQuery();
      while(rs.next()){
        majorName.add(rs.getString(1));
      }
      rs.close();
    } catch (SQLException se) {
    }
  }

  //개설강의 조회
  void checkClass(){
    columnNameList = new Vector<String>();
    rowDatatwo = new Vector<Vector<Object>>();
    try {
      ResultSet rs = checkClassPstm.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameList.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowData = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowData.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwo.add(rowData);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }
  //학과별 개설강의 조회
  void checkMajorClass(String majorName){
    columnNameList = new Vector<String>();
    rowDatatwo = new Vector<Vector<Object>>();
    try {
      checkClassMajorPstm.setString(1, majorName);
      ResultSet rs = checkClassMajorPstm.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameList.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowData = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowData.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwo.add(rowData);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }
//총원 조회
  void stuShow(){
    columnNameList = new Vector<String>();
    rowDatatwo = new Vector<Vector<Object>>();
    try {
      ResultSet rs = pstm1.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameList.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowData = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowData.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwo.add(rowData);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }

    //학생 입학 (학번, 이름, 이메일, 휴대폰번호, 등록금, 학과)
  void adminStu(Long stnum, String name, String email, String mobile, Long stuition, String majorname){
    try {
      pstm8.setLong(1, stnum);
      pstm8.setString(2, name);
      pstm8.setString(3, email);
      pstm8.setString(4, mobile);
      pstm8.setLong(5, stuition);
      pstm8.setString(6, majorname);
      int i = pstm8.executeUpdate(); 
      if(i>0){
        System.out.println(i+"개의 row 입력 성공");
        adminStu = "입학완료"+"\n";
      }else{
        System.out.println("adminStu 입력실패");
      }
    } catch (SQLException se) {
      System.out.println("학생등록 실패: "+se);
      adminStu = "중복 학번 혹은 없는 학과입니다."+"\n";
    }
  }
//학생별 등록학기 조회
  void semeShow(Long stnum){
    columnNameList = new Vector<String>();
    rowDatatwo = new Vector<Vector<Object>>();
    try {
      pstm2.setLong(1, stnum);
      ResultSet rs = pstm2.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameList.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowData = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowData.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwo.add(rowData);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }

    //학기 등록
    void adminSeme(Long stnum, String yese){
      try {
        adSemePstm.setLong(1, stnum);
        adSemePstm.setString(2, yese);
        int i = adSemePstm.executeUpdate(); 
        if(i>0){
          adminSeme = "학기등록 성공"+"\n";
        }else{
          adminSeme = "학기등록 실패"+"\n";
        }
      } catch (SQLException se) {
        adminSeme="없는 학번이거나 학기가 아직 개설되지 않았습니다."+"\n";
      }
    }
    //학기 등록 결과확인
    void adminSemeRe(Long stnum){
      columnNameList = new Vector<String>();
      rowDatatwo = new Vector<Vector<Object>>();
      try {
        adSemeRePstm.setLong(1, stnum);
        ResultSet rs = adSemeRePstm.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        for(int i=1; i<=columnCount;i++){
          columnNameList.add(rsmd.getColumnName(i));
        }
        while(rs.next()){
          rowData = new Vector<Object>();
          for(int i=1; i<=columnCount;i++){
            rowData.add(rs.getObject(rsmd.getColumnName(i)));
          }
          rowDatatwo.add(rowData);
        }
        rs.close();
      } catch (SQLException se) {
      }
    }

  //성적부여
  void giveGp(float gp,String yese, Long stnum, String ccode){
    try {
      giveGpPstm.setFloat(1, gp);
      giveGpPstm.setString(2, yese);
      giveGpPstm.setLong(3, stnum);
      giveGpPstm.setString(4, ccode);
      int i = giveGpPstm.executeUpdate(); 
      if(i>0){
        giveGpcheck = "성적부여 성공"+"\n";
      }else{
        giveGpcheck = "성적부여 할 데이터가 없습니다."+"\n";
      }
    } catch (SQLException se) {
        giveGpcheck = "데이터를 올바른 형태로 입력하세요."+"\n";
    }
  }
  //성적부여 결과 조회
  void giveGpRe(Long stnum, String yese){
    columnNameList = new Vector<String>();
    rowDatatwo = new Vector<Vector<Object>>();
    try {
      giveGpRePstm.setLong(1, stnum);
      giveGpRePstm.setString(2, yese);
      ResultSet rs = giveGpRePstm.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameList.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowData = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowData.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwo.add(rowData);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }
  // 개설학기 확인
  void checkYese(){
    columnNameList = new Vector<String>();
    rowDatatwo = new Vector<Vector<Object>>();
    try {
      ResultSet rs = checkYesePstm.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameList.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowData = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowData.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwo.add(rowData);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }
  
//학기별 수강신청한 수업조회
  void yeseClassShow(Long stnum, String yese){
    columnNameList = new Vector<String>();
    rowDatatwo = new Vector<Vector<Object>>();
    try {
      pstm3.setLong(1, stnum);
      pstm3.setString(2, yese);
      ResultSet rs = pstm3.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameList.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowData = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowData.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwo.add(rowData);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }
  //학기별 과, 학년 조회
  void scholShow(String yese){
    columnNameList = new Vector<String>();
    rowDatatwo = new Vector<Vector<Object>>();
    try {
      pstm7.setString(1, yese);
      ResultSet rs = pstm7.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for(int i=1; i<=columnCount;i++){
        columnNameList.add(rsmd.getColumnName(i));
      }
      while(rs.next()){
        rowData = new Vector<Object>();
        for(int i=1; i<=columnCount;i++){
          rowData.add(rs.getObject(rsmd.getColumnName(i)));
        }
        rowDatatwo.add(rowData);
      }
      rs.close();
    } catch (SQLException se) {
    }
  }

  void closeAll(){
    try {
      pstm1.close();pstm2.close();pstm3.close();pstm7.close();pstm8.close();adSemePstm.close();adSemeRePstm.close();giveGpPstm.close();giveGpRePstm.close();
      checkYesePstm.close(); checkClassPstm.close(); checkClassMajorPstm.close();checkMajorPstm.close();
      checkEnrolPstm.close();checkEnrolYesePstm.close(); checkRcardPstm.close();checkYeseCardPstm.close();checkProEntPstm.close();
      checkProEntYesePstm.close(); loginPstm.close();loginMPstm.close(); stuDataPstm.close(); totalRcardPstm.close();totalRcardYesePstm.close();
      makeBoxPstm.close();checkStuYeseClassPstm.close();graduPstm.close();moneyPstm.close(); applyClassPstm.close();deleteApplyPstm.close(); maxApplyPstm.close();cstmt.close();
      db.con.close();applyXPstm.close();
    } catch (SQLException se) {
    }
    System.out.println("정상작동");
  }

    //장학금 부여
    void scholar(String majorName, int grade ,String yese){
      try {
        cstmt.setString(1, majorName);
        cstmt.setInt(2, grade);
        cstmt.setString(3,yese);
        cstmt.execute();
      } catch (SQLException se) {
      }
    }
}
