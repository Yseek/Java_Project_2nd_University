package univer.db;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


class ReCard extends JFrame{
	Container cp;
	ImageIcon i1, i2;
	JPanel p1, p2, p3, p4;
	JLabel l1, l2, l3;
	Font f1, f2;
	JButton back;
	JTable t1, t2;
	JScrollPane sp, sp2;
  Student stu;
  String selectedYese;

	ReCard(Student stu){
    this.stu= stu;
		init();
		back(); //뒤로가기 메소드
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
		p1.setBounds(0,10, 1058, 80);
		p1.add(l1);
		
		p2 = new JPanel(); //전체 성적 조회란
		p2.setBackground(Color.white);
		p2.setLayout(new GridLayout(2,1));
		l2 = new JLabel();
		l2.setText("전체 성적 조회");
		f1 = new Font("휴먼모음T", Font.PLAIN, 40);
		l2.setFont(f1);
		l2.setHorizontalAlignment(JLabel.CENTER);

		t1 = new JTable(stu.tb.rowDatatwoUp, stu.tb.columnNameListUp); //전체성적조회 테이블
		sp = new JScrollPane(t1);
		p2.add(l2);
		p2.setBounds(0,100, 1058, 200);
		p2.add(sp);
			
		p3 = new JPanel(); //학기별 성적 조회란
		p3.setBackground(Color.white);
		p3.setLayout(new GridLayout(2,1));
		l3 = new JLabel();
		l3.setText("학기별 성적 내역");
		f2 = new Font("휴먼모음T", Font.PLAIN, 40);
		l3.setFont(f2);
		l3.setHorizontalAlignment(JLabel.CENTER);
		//테이블
		t2 = new JTable(stu.tb.rowDatatwo, stu.tb.columnNameList); //학기별 성적 조회 테이블
    t2.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e){
        selectedYese = (String)t2.getValueAt(t2.getSelectedRow(), 0);
        stu.tb.checkYeseCard(Main.stnum,selectedYese);
        stu.tb.totalRcardYese(Main.stnum,selectedYese);
      }
    });
		sp2 = new JScrollPane(t2);
		p3.add(l3);
		p3.setBounds(0,250, 1058, 400);
		p3.add(sp2);
		
		p4 = new JPanel();
		p4.setLayout(null);
		p4.setBackground(Color.white);
		i2 = new ImageIcon("img/i5.png"); //뒤로가기버튼
		back = new JButton(i2);
		back.setBounds(950, 20, 70, 44);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		p4.setBounds(0, 650, 1058, 65);
		p4.add(back);
		
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
		setTitle("GU - STUDENT/ REPORT CARD");
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

