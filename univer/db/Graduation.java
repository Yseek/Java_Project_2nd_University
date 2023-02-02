package univer.db;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Graduation extends JFrame{
	Container cp;
	ImageIcon i1, i2, i3, i4, i5, i6, i7, i8;
	JPanel p1, p2, p3;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16;
	Font f1, f2, f3, f4, f5, f6, f7, f8, f9, f10;
	JButton back, b;
  Student stu;
	
	Graduation(Student stu){
    this.stu=stu;
		init();
		back(); 
		setUI();
	}

void init(){
		cp = getContentPane();
		cp.setBackground(Color.white);
		cp.setLayout(null);
				
		p1 = new JPanel(); //가산대 포털 타이틀
		p1.setBackground(Color.white);
		i1 = new ImageIcon("img/i12.png");
		l1 = new JLabel(i1);
		p1.setBounds(0,0, 1058, 80);
		p1.add(l1);
		
		p2 = new JPanel(); //졸업 가능 여부 패널
		p2.setBackground(Color.white);
		p2.setLayout(null);
		
		//레이블2 - 졸업가능 여부 텍스트
		l2 = new JLabel();
		l2.setText("졸업 가능 여부");//텍스트
		f1 = new Font("휴먼모음T", Font.PLAIN, 40);
		l2.setFont(f1);
		l2.setHorizontalAlignment(JLabel.CENTER);
		l2.setBounds(0,15, 1058, 60);
		
		//레이블3 회색박스
		l3 = new JLabel();//회색박스
		i2 = new ImageIcon("img/i13.png");
		l3 = new JLabel(i2);
		l3.setBounds(0,30, 1058, 500);
		l3.setHorizontalAlignment(JLabel.CENTER);

		//취득학점 텍스트 l4 f2
		l4 = new JLabel();
		l4.setText("취득 학점");
		f2 = new Font("휴먼모음T", Font.PLAIN, 25);
		l4.setFont(f2);
		l4.setBounds(300,180, 150, 50);
	
		//졸업가능학점 텍스트 l5 f3
		l5 = new JLabel();
		l5.setText("졸업 가능 학점");
		f3 = new Font("휴먼모음T", Font.PLAIN, 25);
		l5.setFont(f3);
		l5.setBounds(270,250, 200, 50);
	
		//남은 학점 텍스트 l6 f4
		l6 = new JLabel();
		l6.setText("남은 학점");
		f4 = new Font("휴먼모음T", Font.PLAIN, 25);
		l6.setFont(f4);
		l6.setBounds(300, 320, 150, 50);
		
		//흰색박스1 l7 i3
		l7 = new JLabel();//흰색박스1
		i3 = new ImageIcon("img/i15.png");
		l7 = new JLabel(i3);
		l7.setBounds(455,185, 200, 36);

		//흰색박스2 l8 i4
		l8 = new JLabel();//흰색박스2
		i4 = new ImageIcon("img/i15.png");
		l8 = new JLabel(i4);
		l8.setBounds(455,255, 200, 36);

		//흰색박스3 l9 i5
		l9 = new JLabel();//흰색박스3
		i5 = new ImageIcon("img/i15.png");
		l9 = new JLabel(i5);
		l9.setBounds(455,325, 200, 36);

		//레이블10 - l10 f5 취득학점 데이터 텍스트
		l10 = new JLabel();
		l10.setText(String.valueOf(stu.tb.i));//텍스트
		f5 = new Font("휴먼모음T", Font.PLAIN, 20);
		l10.setFont(f5);
		l10.setBounds(460,175, 1058, 60);

		//레이블11 - l11 f6 졸업가능 학점 텍스트
		l11 = new JLabel();
		l11.setText(String.valueOf(stu.tb.j));//텍스트
		f6 = new Font("휴먼모음T", Font.PLAIN, 20);
		l11.setFont(f6);
		l11.setBounds(460,245, 1058, 60);

		//레이블12 - l12 f7 남은 학점 텍스트
		l12 = new JLabel();
		l12.setText(String.valueOf(stu.tb.k));//텍스트
		f7 = new Font("휴먼모음T", Font.PLAIN, 20);
		l12.setFont(f7);
		l12.setBounds(458, 315, 1058, 60);

		//흰색박스-졸업여부 l13 i8
		l13 = new JLabel();//흰색박스
		i8 = new ImageIcon("img/i17.png");
		l13 = new JLabel(i8); 
		l13.setBounds(228,480, 600, 82);

		//텍스트 - 가능 l15 f9 (if (졸업학점<=취득학점 or 남은학점>0))
		l15 = new JLabel();

		f9 = new Font("휴먼모음T", Font.PLAIN, 40);
		l15.setFont(f9);
		l15.setHorizontalAlignment(JLabel.CENTER);
		l15.setBounds(0,495, 1058, 60);

		//텍스트 - 불가능 l16 f10 (if (졸업학점>취득학점 or 남은학점=<0))
		l16 = new JLabel();

		f10 = new Font("휴먼모음T", Font.PLAIN, 40);
		l16.setFont(f10);
		l16.setHorizontalAlignment(JLabel.CENTER);
		l16.setBounds(0,495, 1058, 60);

		//졸업 여부 텍스트 l14 f8
		l14 = new JLabel();
		l14.setText("졸업이 "+"            " +"합니다.");//텍스트
		f8 = new Font("휴먼모음T", Font.PLAIN, 40);
		l14.setFont(f8);
		l14.setHorizontalAlignment(JLabel.CENTER);
		l14.setBounds(0,495, 1058, 60);

		//CHECK 노란버튼
		i6 = new ImageIcon("img/i16.png");
		b = new JButton(i6);
    b.addActionListener((e)->{  
      if(stu.tb.k<=0){
        l15.setText("가능");//텍스트
        l15.setForeground(Color.BLUE);
        l15.repaint();
      }else{
        l16.setText("불가능");//텍스트
        l16.setForeground(Color.RED);
        l16.repaint();
      }
    });
		b.setBounds(720,250, 70, 51);
		b.setContentAreaFilled(false);
		b.setCursor(new Cursor(Cursor.HAND_CURSOR));


		//뒤로가기 버튼
		i7 = new ImageIcon("img/i5.png");
		back = new JButton(i7);
		back.setBounds(950, 600, 70, 44);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
		//패널2 설정
		p2.setBounds(0,90, 1058, 650);
    p2.add(l15); //텍스트 - 가능
    p2.add(l16); //텍스트 - 불가능
		p2.add(l14); //텍스트 - 졸업여부  
		p2.add(l13); //흰색박스 - 졸업여부
		p2.add(back); //뒤로가기 버튼
		p2.add(b); //CHECK버튼
		p2.add(l2); //텍스트-졸업가능여부
		p2.add(l4); //텍스트-취득학점
		p2.add(l5); //텍스트-졸업가능학점
		p2.add(l6); //텍스트-남은학점
		p2.add(l10); //텍스트-취득학점 데이터 텍스트
		p2.add(l11); //텍스트-졸업가능학점 데이터 텍스트
		p2.add(l12); //텍스트-남은 학점 데이터 텍스트
		p2.add(l7); //흰색박스1
		p2.add(l8); //흰색박스2
		p2.add(l9); //흰색박스3
		p2.add(l3);//회색박스

		cp.add(p1);
		cp.add(p2);	
	}

	void back(){ //뒤로가기 메소드
		ActionListener lst = (e) -> {
				new Student(stu.tb);
				setVisible(false);
		};
		back.addActionListener(lst);
	}

	void setUI(){ //UI 디폴트
		setTitle("GU - STUDENT/	CHECK-GRADUATION");
		setSize(1058,780);
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
