package univer.db;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Student extends JFrame{
	Container cp;
	ImageIcon i1, i2, i3, i4, i5, i6;
	JPanel p1;
	JLabel l1, l2, l3, l4, l5, l6, l7;
	JButton b1, b2, b3, b4, back;
	Font f1, f2, f3, f4, f5, f6;
  Table tb;

	Student(Table tb){
    this.tb=tb;
		init();
		bm1(); //메소드: 성적
		bm2(); //메소드: 수강확인
		bm3(); //메소드: 등록/장학금
		bm4(); //메소드: 졸업가능여부
		back(); //뒤로가기 메소드
		setUI();
    System.out.println(tb.stuName);
	}

	void init(){
		cp = getContentPane();
		p1 = new JPanel(){ //배경이미지
          public void paintComponent(Graphics g) {
            g.drawImage(new ImageIcon("img/i10.png").getImage(), 0, 0, null);
            //setOpaque(false);
            }
        };
		l2 = new JLabel();
		l2.setForeground(Color.white);
		l2.setText("이름");
		f1 = new Font("휴먼모음T", Font.PLAIN, 25);
		l2.setFont(f1);
		l2.setBounds(53, 510, 100, 100);

		l5 = new JLabel(); //★이름 들어갈 부분
		l5.setForeground(Color.white);
		l5.setText(tb.stuName);
		f4 = new Font("휴먼모음T", Font.PLAIN, 25);
		l5.setFont(f4);
		l5.setBounds(150, 510, 1000, 100);

		l3 = new JLabel(); //학생정보란 학번
		l3.setForeground(Color.white);
		l3.setText("학번");
		f2 = new Font("휴먼모음T", Font.PLAIN, 25);
		l3.setFont(f2);
		l3.setBounds(53, 550, 100, 100);
		
		l6 = new JLabel(); //★학번 들어갈 부분
		l6.setForeground(Color.white);
		l6.setText(tb.stuStnum);
		f5 = new Font("휴먼모음T", Font.PLAIN, 25);
		l6.setFont(f5);
		l6.setBounds(150, 550, 1000, 100);

		l4 = new JLabel(); //학생정보란 전공
		l4.setForeground(Color.white);
		l4.setText("전공");
		f3 = new Font("휴먼모음T", Font.PLAIN, 25);
		l4.setFont(f3);
		l4.setBounds(53, 590, 100, 100);

		l7 = new JLabel(); //★전공 들어갈 부분
		l7.setForeground(Color.white);
		l7.setText(tb.stuMajorName);
		f6 = new Font("휴먼모음T", Font.PLAIN, 25);
		l7.setFont(f6);
		l7.setBounds(150, 590, 1000, 100);

		i3 = new ImageIcon("img/i6.png"); //버튼 이미지들
		i4 = new ImageIcon("img/i7.png");
		i5 = new ImageIcon("img/i8.png");
		i6 = new ImageIcon("img/i9.png");

		b1 = new JButton(i3); //버튼들
		b2 = new JButton(i4);
		b3 = new JButton(i5);
		b4 = new JButton(i6);

		b1.setBounds(410, 250, 300, 146); //성적
		b1.setContentAreaFilled(false);
		b1.setCursor(new Cursor(Cursor.HAND_CURSOR));

		b2.setBounds(730, 250, 300, 146); //수강확인
		b2.setContentAreaFilled(false);
		b2.setCursor(new Cursor(Cursor.HAND_CURSOR));

		b3.setBounds(410, 500, 300, 146); //등록/장학금
		b3.setContentAreaFilled(false);
		b3.setCursor(new Cursor(Cursor.HAND_CURSOR));

		b4.setBounds(730, 500, 300, 146); //졸업가능여부
		b4.setContentAreaFilled(false);
		b4.setCursor(new Cursor(Cursor.HAND_CURSOR));

		i2 = new ImageIcon("img/i5.png"); //뒤로가기버튼
		back = new JButton(i2);
		back.setBounds(950, 680, 70, 44);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		cp.add(b1); //버튼들
		cp.add(b2);
		cp.add(b3);
		cp.add(b4);
		cp.add(l2);	// 이름 레이블
		cp.add(l5);	// 이름 데이터 레이블
		cp.add(l3);	//학번 레이블
		cp.add(l6);	//학번 데이터 레이블
		cp.add(l4); //전공 레이블
		cp.add(l7); //전공 데이터 레이블
		cp.add(back); //뒤로가기 버튼
		cp.add(p1); //배경이미지
	}

	void bm1(){//성적 버튼 이벤트 메소드
		ActionListener lst = (e) -> {
        tb.totalRcard(Main.stnum);
        tb.checkRcard(Main.stnum);
				new ReCard(this);
				setVisible(false);
		};
		b1.addActionListener(lst);
	}

	void bm2(){//수강확인 버튼 이벤트 메소드
		ActionListener lst = (e) -> {
        tb.makeBox(Main.stnum);
        tb.checkStuYeseClass(1L,null);
				new CClass(this); 
				setVisible(false);
		};
		b2.addActionListener(lst);
	}

	void bm3(){//등록/장학 버튼 이벤트 메소드
		ActionListener lst = (e) -> {
      tb.money(Main.stnum);
      new TuitionWindow(this);
      setVisible(false);
		};
		b3.addActionListener(lst);
	}

	void bm4(){//졸업 가능 여부 버튼 이벤트 메소드
		ActionListener lst = (e) -> {
        tb.gradu(Main.stnum);
				new Graduation(this);
				setVisible(false);
		};
		b4.addActionListener(lst);
	}

	void back(){ //뒤로가기 메소드
		ActionListener lst = (e) -> {
				new Main(tb);
				setVisible(false);
		};
		back.addActionListener(lst);
	}

	void setUI(){ //UI 디폴트
		setTitle("Gasan University - STUDENT");
		setSize(1058,780);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e){
        tb.closeAll();
      }
    });
	}
}
