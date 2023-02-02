package univer.db;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class ApplyClass extends JFrame{
  Container cp;
  ImageIcon i1, iback, iAp;
  JPanel p1, p2, p3, p4, p5, pt;
  JLabel l1, l2, l3, l4,maxApply;
  Font f1, f2, f3;
  JButton back;
  JTable t, t2;
  DefaultTableModel tDe, t2De;
  JScrollPane sp, sp2;
  String ccode;
  CClass ccl;
  int applyNUm;
  
  ApplyClass(CClass ccl){
  this.ccl=ccl;
  init();
  back(); //뒤로가기 메소드
  setUI();
  }

  void init(){
    cp = getContentPane();
    cp.setBackground(Color.white);
    cp.setLayout(null);
    t = new JTable(ccl.stu.tb.rowDatatwo, ccl.stu.tb.columnNameList); 
    tDe =(DefaultTableModel)t.getModel(); 
    t2 = new JTable(ccl.stu.tb.rowDatatwoUp, ccl.stu.tb.columnNameListUp); //강좌목록 테이블
    t2De = (DefaultTableModel) t2.getModel();
    maxApply = new JLabel(applyNUm+"/"+ccl.stu.tb.maxApply);
    maxApply.setBounds(250, 5, 200, 20);
    applyNUm=0;
    int row =t2.getRowCount();
    for(int i =0; i<row ;i++){
      applyNUm+=Integer.parseInt(String.valueOf(t2.getValueAt(i,1)));
    }
    maxApply.setText(applyNUm+"/"+ccl.stu.tb.maxApply); 
    maxApply.repaint();

    //패널1
    p1 = new JPanel(); //가산대 포털 타이틀
    p1.setBackground(Color.white);
    i1 = new ImageIcon("img/i12.png");
    l1 = new JLabel(i1);
    p1.setBounds(0,0, 1058, 80);
    p1.add(l1);
    
    //패널2
    p2 = new JPanel(); //수강신청 패널
    p2.setBackground(Color.white);
    p2.setLayout(null);
    
    //레이블1 - 수강신청 텍스트 l2 f1
    l2 = new JLabel();
    l2.setText("수강 신청");//수강확인텍스트
    f1 = new Font("휴먼모음T", Font.PLAIN, 40);
    l2.setFont(f1);
    l2.setHorizontalAlignment(JLabel.CENTER);
    l2.setBounds(0,100, 1058, 60);
  
    //레이블2 - 강좌목록 텍스트 l3 f2
    l3 = new JLabel();
    l3.setText("강좌 목록");//강좌목록 텍스트
    f2 = new Font("휴먼모음T", Font.PLAIN, 20);
    l3.setFont(f2);
    l3.setBounds(30, 155, 100, 20);

    //콤보박스
    JComboBox<String> selectMajorBox = new JComboBox<String>(ccl.stu.tb.majorName);
    selectMajorBox.setBounds(155, 155, 150, 20);
    selectMajorBox.addActionListener((e)->{
      ccl.stu.tb.checkMajorClass((String)selectMajorBox.getSelectedItem());
      tDe.setDataVector(ccl.stu.tb.rowDatatwo, ccl.stu.tb.columnNameList);
    });
    
    //패널2설정
    p2.setBounds(0,0, 1058, 180);
    p2.add(l2);//수강신청 타이틀 텍스트
    p2.add(l3);//강좌목록 텍스트
    p2.add(selectMajorBox);
          
    //패널3 - 강좌목록 테이블 패널
    p3 = new JPanel(); //테이블 패널
    p3.setBackground(Color.white);
    p3.setBounds(0, 190, 1058, 270);

    //강좌목록 테이블
    t.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e){
        ccode = (String)t.getValueAt(t.getSelectedRow(), 0);
        ccl.stu.tb.applyClass(ccode, Main.stnum, ccl.yese);
        ccl.stu.tb.applycheckStuYeseClass(Main.stnum, ccl.yese);
        t2De.setDataVector(ccl.stu.tb.rowDatatwoUp, ccl.stu.tb.columnNameListUp);
        applyNUm=0;
        int row =t2.getRowCount();
        for(int i =0; i<row ;i++){
          applyNUm+=Integer.parseInt(String.valueOf(t2.getValueAt(i,1)));
        }
        if(applyNUm>ccl.stu.tb.maxApply){
          ccl.stu.tb.deleteApply(Main.stnum,ccl.yese,ccode);
          ccl.stu.tb.applycheckStuYeseClass(Main.stnum, ccl.yese);
          t2De.setDataVector(ccl.stu.tb.rowDatatwoUp, ccl.stu.tb.columnNameListUp);
          JOptionPane.showMessageDialog(null, "최대수강학점입니다.");
        }else{
          maxApply.setText(applyNUm+"/"+ccl.stu.tb.maxApply); 
          maxApply.repaint();
        }
      }
    });

    t2.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e){
        applyNUm=0;
        int row =t2.getRowCount();
        ccode = (String)t2.getValueAt(t2.getSelectedRow(), 0);
        ccl.stu.tb.deleteApply(Main.stnum,ccl.yese,ccode);
        ccl.stu.tb.applycheckStuYeseClass(Main.stnum, ccl.yese);
        t2De.setDataVector(ccl.stu.tb.rowDatatwoUp, ccl.stu.tb.columnNameListUp);
        for(int i =0; i<row-1 ;i++){
          applyNUm+=Integer.parseInt(String.valueOf(t2.getValueAt(i,1)));
        }
        maxApply.setText(applyNUm+"/"+ccl.stu.tb.maxApply); 
        maxApply.repaint();
      }
    });
    sp = new JScrollPane(t);
    sp.setPreferredSize(new Dimension(1000, 230));
    p3.add(sp);

    //패널4
    p4 = new JPanel();
    p4.setLayout(null);
    p4.setBackground(Color.white);
    p4.setBounds(0, 470, 1058, 30);


    //레이블3 - 수강신청 내역 텍스트 l4 f3
    l4 = new JLabel();
    l4.setText("수강 신청 내역");//강좌목록 텍스트
    f3 = new Font("휴먼모음T", Font.PLAIN, 20);
    l4.setFont(f3);
    l4.setBounds(25, 5, 200, 20);
    p4.add(maxApply);
    p4.add(l4);

    //패널 테이블용
    pt = new JPanel();
    pt.setBackground(Color.white);
    pt.setBounds(0, 500, 1058, 190);

    //수강 신청 내역 테이블
 
    sp2 = new JScrollPane(t2);
    sp2.setPreferredSize(new Dimension(1000, 150));
    pt.add(sp2);
  
    //패널5
    p5 = new JPanel();
    p5.setLayout(null);
    p5.setBackground(Color.white);
    iback = new ImageIcon("img/i5.png"); //뒤로가기버튼
    back = new JButton(iback);
    back.setBounds(960, 10, 70, 44);
    back.setBorderPainted(false);
    back.setContentAreaFilled(false);
    back.setCursor(new Cursor(Cursor.HAND_CURSOR));
    p5.setBounds(0, 680, 1058, 70);
    p5.add(back);

    cp.add(p1);
    cp.add(p2);
    cp.add(p3);
    cp.add(p4);
    cp.add(pt);
    cp.add(p5);
  }

  void back(){ //뒤로가기 메소드
    ActionListener lst = (e) -> {
          ccl.stu.tb.checkStuYeseClass(Main.stnum, null);
          new CClass(ccl.stu);
          setVisible(false);
    };
    back.addActionListener(lst);
  }

  void setUI(){ //UI 디폴트
    setTitle("GU - STUDENT/ APPLY CLASS");
    setSize(1058,780);
    setVisible(true);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e){
        ccl.stu.tb.closeAll();
      }
    });
  }
}
