package univer.db;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class CClass extends JFrame{
	Container cp;
	ImageIcon i1, i2, i3, i4,iAp;
	JPanel p1, p2, p3,p4;
	JLabel l1, l2, l3, l4, l5, l6;
	Font f1, f2, f3, f4;
	JButton back, b, bAp;
	JTable t;
  DefaultTableModel deTable;
	JScrollPane sp;
  Student stu;
  String yese;
	JComboBox<String> cb2;

	CClass(Student stu){
    this.stu=stu;
    
		init();
    apply();
		searchM(); //search 메소드 - 누르면 아래 테이블에 데이터 나오게 하는 메소드
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
		p1.setBounds(0,0, 1058, 80);
		p1.add(l1);
		
		p2 = new JPanel(); //수강확인란
		p2.setBackground(Color.white);
		p2.setLayout(null);
		
		//레이블1 - 수강확인 텍스트
		l2 = new JLabel();
		l2.setText("수강 확인");//수강확인텍스트
		f1 = new Font("휴먼모음T", Font.PLAIN, 40);
		l2.setFont(f1);
		l2.setHorizontalAlignment(JLabel.CENTER);
		l2.setBounds(0,100, 1058, 60);
	
		//레이블2 - 회색박스
		l3 = new JLabel();//회색박스
		i2 = new ImageIcon("img/i19.png");
		l3 = new JLabel(i2);
		l3.setBounds(0,20, 1058, 500);
		l3.setHorizontalAlignment(JLabel.CENTER);
	
		//레이블3 - SEARCH 노란버튼
		i3 = new ImageIcon("img/i14.png");
		b = new JButton(i3);
		b.setBounds(720,250, 70, 51);
		b.setContentAreaFilled(false);
		b.setCursor(new Cursor(Cursor.HAND_CURSOR));


		//학기 텍스트 l5 f3
		l5 = new JLabel();
		l5.setText("학년도");
		f3 = new Font("휴먼모음T", Font.PLAIN, 25);
		l5.setFont(f3);
		l5.setBounds(250,248, 150, 50);

	
		//JCombobox2 - 학기
		cb2 = new JComboBox<String>(stu.tb.comboBox);
    yese = (String)cb2.getSelectedItem();
		cb2.setBounds(385,255, 300, 35);
    cb2.addActionListener((e)->{
      yese = (String)cb2.getSelectedItem();
			if(stu.tb.applyX(Main.stnum,yese)==1){
				bAp.setEnabled(false);
			}else if(stu.tb.applyX(Main.stnum,yese)!=1){
				bAp.setEnabled(true);
			}
    });
	
    //수강신청 버튼
    iAp = new ImageIcon("img/i20.png"); //뒤로가기버튼
    bAp = new JButton(iAp);
		bAp.setEnabled(false);
    bAp.setBounds(940, 100, 95, 47);
    bAp.setBorderPainted(false);
    bAp.setContentAreaFilled(false);
    bAp.setCursor(new Cursor(Cursor.HAND_CURSOR));

    		//패널2설정
		p2.setBounds(0,0, 1058, 350);
		p2.add(bAp); //뒤로가기
		p2.add(l2);//수강확인
		p2.add(l5);//학기
		p2.add(cb2);//콤보박스2 - 학기
		p2.add(b);//노란버튼
		p2.add(l3);//회색박스
		
    //패널3
		p3 = new JPanel(); //테이블 패널
		p3.setBackground(Color.white);
		p3.setBounds(0,380, 1058, 300);
		t = new JTable(stu.tb.rowDatatwo, stu.tb.columnNameList); //수강확인 테이블
    deTable = (DefaultTableModel) t.getModel();
		sp = new JScrollPane(t);
		sp.setPreferredSize(new Dimension(1000, 280));
		p3.add(sp);
	
		//뒤로가기 버튼
    p4 = new JPanel();
    p4.setLayout(null);
    p4.setBackground(Color.white);
		i4 = new ImageIcon("img/i5.png"); //뒤로가기버튼
		back = new JButton(i4);
		back.setBounds(959, 30, 70, 44);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
    p4.setBounds(0, 650, 1058, 100);
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

	void searchM(){ //search버튼 메소드
		ActionListener lst = (e) -> {
      stu.tb.checkStuYeseClass(Main.stnum, yese);
      deTable.setDataVector(stu.tb.rowDatatwo, stu.tb.columnNameList);
		};
		b.addActionListener(lst);
	}

  void apply(){
    ActionListener lst = (e) -> {
          stu.tb.maxApply(Main.stnum, yese);
          stu.tb.checkMajorBox();
          stu.tb.applycheckStuYeseClass(Main.stnum, yese);
          stu.tb.checkStuYeseClass(Main.stnum, null);
          new ApplyClass(this);
          setVisible(false);
    };
    bAp.addActionListener(lst);
 }

	void setUI(){ //UI 디폴트
		setTitle("GU - STUDENT/ CHECK CLASS");
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
