package univer.db;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


class Main extends JFrame{
	Container cp;
	ImageIcon i1, i2;
	JPanel p1;
	JLabel l1, l2;
	JTextField tf;
	JPasswordField pf;
	Font f1, f2;
	JButton b;
  Table tb;
  static long stnum;


	Main(Table tb){
    this.tb = tb;
		init();
		setUI();
	}

	void init(){
		cp = getContentPane();

		p1 = new JPanel(){ //배경이미지
          public void paintComponent(Graphics g) {
            g.drawImage(new ImageIcon("img/i1.png").getImage(), 0, 0, null);
            //setOpaque(false);
            }
        };
		l1 = new JLabel();
		l1.setText("이름: ");
		f1 = new Font("휴먼모음T", Font.PLAIN, 20);
		l1.setFont(f1);
		l1.setBounds(328, 492, 200, 30);
		cp.add(l1);

		tf = new JTextField(); //로그인 아이디창
		tf.setBounds(380, 492, 200, 30);
		cp.add(tf);

		l2 = new JLabel(); 
		l2.setText("학번/사번: ");
		f2 = new Font("휴먼모음T", Font.PLAIN, 20);
		l2.setFont(f2);
		l2.setBounds(290, 532, 200, 30);
		cp.add(l2);

		pf = new JPasswordField(); //로그인 비번창
		pf.setBounds(380, 532, 200, 30);
		cp.add(pf);

		i2 = new ImageIcon("img/i2.png"); //로그인버튼
		b = new JButton(i2);
    b.addActionListener((e)->{
      String name = tf.getText();
      String pw="";
      char[] cha= pf.getPassword();
      for(char c: cha){
        Character.toString(c);
        pw +=c;
      }
      int j = tb.login(name,pw);
      if(j==1){
        //setVisible(true);
      }else{
        try{
          stnum = Long.parseLong(pw);
          int i = tb.login(name, stnum);
          if(i==1){
            setVisible(false);
          }else{
            JOptionPane.showMessageDialog(rootPane, "정보가 없습니다");
          }
        }catch(NumberFormatException nfe){
          JOptionPane.showMessageDialog(rootPane, "정보가 없습니다");
        }
      }
    });
		b.setBounds(600, 467, 150, 122);
		b.setContentAreaFilled(false);
		b.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		cp.add(b);
		cp.add(p1);
	}

	void setUI(){ //UI 디폴트
		setTitle("Gasan University");
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
