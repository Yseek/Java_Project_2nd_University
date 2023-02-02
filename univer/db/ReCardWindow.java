package univer.db;

import java.awt.*;
import javax.swing.*;

class ReCardWindow extends JFrame{
	Container cp;
	ImageIcon i1, i2;
	JPanel p1, p2, p3;
	JLabel l1, l2, l3, l4;
	Font f1, f2, f3;
	JTable t, t2;
	JScrollPane sp, sp2;
  Table tb;

	ReCardWindow(Table tb){
    this.tb= tb;
		init();
		setUI();
	}

	void init(){
		cp = getContentPane();
		cp.setBackground(Color.white);
		cp.setLayout(null);

		p1 = new JPanel(); // 학년/학기별타이틀
		p1.setBackground(Color.white);
		l1 = new JLabel();
		l1.setText("학년/학기별 성적조회");
		f1 = new Font("휴먼모음T", Font.PLAIN, 20);
		l1.setFont(f1);
		l1.setHorizontalAlignment(JLabel.CENTER);
		p1.setBounds(0,10, 750, 50);
		p1.add(l1);
		
		p2 = new JPanel(); //상위 테이블
		p2.setBackground(Color.white);
		p2.setBounds(0,60, 750, 100);
		t = new JTable(tb.rowDatatwoUp, tb.columnNameListUp);
		sp = new JScrollPane(t);
		sp.setPreferredSize(new Dimension(750, 70));
		p2.add(sp);
			
		p3 = new JPanel(); //하위 테이블
		p3.setBackground(Color.white);
		p3.setBounds(0, 170, 750, 420);
		t2 = new JTable(tb.rowDatatwo, tb.columnNameList);
		sp2 = new JScrollPane(t2);
		sp2.setPreferredSize(new Dimension(750, 300));
		p3.add(sp2);

		cp.add(p1);
		cp.add(p2);
		cp.add(p3);		
	}

	void setUI(){ //UI 디폴트
		setTitle("GU - STUDENT/ REPORT CARD");
		setSize(750,550);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
}
