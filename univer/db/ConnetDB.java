package univer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnetDB{
  String url="jdbc:oracle:thin:@localhost:1521:JAVA";
  Connection con;

  ConnetDB(){
    init();
    new Table(this);
  }

  void init(){
    try {
      Class.forName("oracle.jdbc.OracleDriver");
      con = DriverManager.getConnection(url, "princess", "0629");
      System.out.println("오라클 연결 성공");
    } catch (ClassNotFoundException cnfe) {
      System.out.println("드라이버 로딩 실패: "+cnfe);
    } catch(SQLException se){
      System.out.println("오라클과 연결 실패: "+ se);
    }
  }

  public static void main(String[] args) {
    new ConnetDB();
  }
}