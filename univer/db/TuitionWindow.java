package univer.db;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TuitionWindow extends JFrame{
   Container cp;
   ImageIcon i1, i2, it, ib;
   JPanel pt, p1, p2, p3, p4;
   JLabel lt, l1, l2, l3, l4;
   Font f1, f2, f3;
   JButton back;
   JTable t;
   JScrollPane sp;
   Student stu;

   TuitionWindow(Student stu){
    this.stu=stu;
      init();
      back();
      setUI();
   }

   void init(){
      cp = getContentPane();
      cp.setBackground(Color.white);
      cp.setLayout(null);
      
      pt = new JPanel();
      it = new ImageIcon("img/i18.png");
      lt = new JLabel(it);
      pt.setBounds(0, 0, 700, 52);
      pt.add(lt);

      p1 = new JPanel(); //등록금 조회 타이틀
      p1.setBackground(Color.white);
      l1 = new JLabel();
      l1.setText("등록금 조회");
      f1 = new Font("휴먼모음T", Font.PLAIN, 30);
      l1.setFont(f1);
      l1.setHorizontalAlignment(JLabel.CENTER);
      p1.setBounds(0,80, 700, 50);
      p1.add(l1);
      
      p2 = new JPanel(); //등록금 테이블
      p2.setBackground(Color.WHITE);
      p2.setBounds(0,150, 700, 100);

      JPanel dataBox = new JPanel();
      dataBox.setPreferredSize(new Dimension(400,100));
      dataBox.setBackground(Color.white);
      dataBox.setLayout(new GridLayout(2,2));

        Font dataBoxfont = new Font("휴먼모음T",Font.PLAIN,30);
        JLabel tuti = new JLabel("등록금");
        tuti.setFont(dataBoxfont);
        tuti.setHorizontalAlignment(JLabel.CENTER);
        dataBox.add(tuti);
        JLabel schol = new JLabel("장학금");
        schol.setFont(dataBoxfont);
        schol.setHorizontalAlignment(JLabel.CENTER);
        dataBox.add(schol);
        JLabel tutidata = new JLabel(stu.tb.tuition);
        tutidata.setFont(dataBoxfont);
        tutidata.setHorizontalAlignment(JLabel.CENTER);
        dataBox.add(tutidata);
        JLabel scholdata = new JLabel(stu.tb.scholarship);
        scholdata.setFont(dataBoxfont);
        scholdata.setHorizontalAlignment(JLabel.CENTER);
        dataBox.add(scholdata);

      p2.add(dataBox);

      p3 = new JPanel(); //총 납부 ... 텍스트
      p3.setBackground(Color.white);
      p3.setLayout(null);
      l2 = new JLabel();
      l2.setText("총 납부 해야 하는 금액은");
      f2 = new Font("휴먼모음T", Font.PLAIN, 20);
      l2.setFont(f2);
      l2.setHorizontalAlignment(JLabel.CENTER);
      l2.setBounds(0,50, 700, 37);

      //흰색박스
      i1 = new ImageIcon("img/i15.png");
      JPanel whiteBox = new JPanel(){
        public void paintComponent(Graphics g) {
          g.drawImage(i1.getImage(),0,0,null);
          setOpaque(false);
        }        
      };
      whiteBox.setBounds(180, 100, 200, 37);
      l3 = new JLabel(String.valueOf(stu.tb.money));
      l3.setHorizontalAlignment(JLabel.CENTER);
      l3.setFont(f2);
      whiteBox.add(l3);
      //텍스트
      l4 = new JLabel();
      l4.setText("원 입니다.");
      f3 = new Font("휴먼모음T", Font.PLAIN, 20);
      l4.setFont(f3);
      //l4.setHorizontalAlignment(JLabel.CENTER);
      l4.setBounds(400, 100, 700, 37);
      p3.setBounds(0, 230, 700, 150);
      //패널add
      p3.add(l2);
      p3.add(whiteBox);
      p3.add(l4);
      
      p4 = new JPanel(); //back버튼 패널
      p4.setBackground(Color.white);
      p4.setLayout(null);
      ib = new ImageIcon("img/i5.png");
      back = new JButton(ib);
      back.setBorderPainted(false);
      back.setContentAreaFilled(false);
      back.setCursor(new Cursor(Cursor.HAND_CURSOR));
      back.setBounds(600, 10, 70, 44);
      p4.setBounds(0, 380, 700, 100);
      p4.add(back);

      cp.add(pt);
      cp.add(p1);
      cp.add(p2);
      cp.add(p3);
      cp.add(p4);
   }

   void back(){ //뒤로가기 메소드
      ActionListener lst = (e) -> {
            new Student(stu.tb);
            setVisible(false);
      };
      back.addActionListener(lst);
   }

   void setUI(){ //UI 디폴트
      setTitle("GU - STUDENT/ TUITION");
      setSize(700,500);
      setVisible(true);
      setLocationRelativeTo(null);
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e){
          stu.tb.closeAll();
        }
      });
   }
}