package univer.db;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


class InsertData extends JFrame{

  JTable dataTable;
  DefaultTableModel deTable;
  Table tb;
  Container cp;

  InsertData(Table tb){
    this.tb = tb;
    cp = getContentPane();
    cp.setLayout(new BorderLayout());
    dataTable = new JTable();
    dataTable.setAutoCreateRowSorter(true);
    deTable = (DefaultTableModel) dataTable.getModel();
    setPage();
    setUI();
  }

  void setPage(){

    //상단부 구역 나눔
    JLabel manage= new JLabel("관리자모드");
    manage.setHorizontalAlignment(JLabel.CENTER);
    manage.setBackground(Color.white);
    manage.setOpaque(true);
    cp.add(manage, BorderLayout.NORTH);

    JPanel west = new JPanel();
    west.setLayout(new BorderLayout());
    cp.add(west,BorderLayout.CENTER);

    JPanel bigEast = new JPanel();
    bigEast.setLayout(new BorderLayout());
    bigEast.setPreferredSize(new Dimension(300,0));
    cp.add(bigEast,BorderLayout.EAST);

    JTextArea showmsg = new JTextArea();
    showmsg.setLineWrap(true);
    showmsg.setWrapStyleWord(true);
    showmsg.setEditable(false);
    JScrollPane shomsgScroll= new JScrollPane(showmsg,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    bigEast.add(shomsgScroll);

    JPanel eastSouth = new JPanel();
    eastSouth.setLayout(new GridLayout(2,1));
    bigEast.add(eastSouth, BorderLayout.SOUTH);

    JPanel east = new JPanel();
    east.setLayout(new GridLayout(5,1));
    eastSouth.add(east);

      JPanel eastStnum = new JPanel();
      eastStnum.setLayout(new GridLayout(1,2));
      east.add(eastStnum);

          JLabel eastStnumText = new JLabel("학번");
          eastStnumText.setHorizontalAlignment(JLabel.CENTER);
          eastStnum.add(eastStnumText);
          JTextField eastStnumInput = new JTextField();
          eastStnum.add(eastStnumInput);
      
      JPanel eastName = new JPanel();
      eastName.setLayout(new GridLayout(1,2));
      east.add(eastName);

        JLabel eastNameText = new JLabel("이름");
        eastNameText.setHorizontalAlignment(JLabel.CENTER);
        eastName.add(eastNameText);
        JTextField eastNameInput = new JTextField();
        eastName.add(eastNameInput);

      JPanel eastMajor = new JPanel();
      eastMajor.setLayout(new GridLayout(1,2));
      east.add(eastMajor);

        JLabel eastMajorText = new JLabel("학과");
        eastMajorText.setHorizontalAlignment(JLabel.CENTER);
        eastMajor.add(eastMajorText);
        JTextField eastMajorInput = new JTextField();
        eastMajor.add(eastMajorInput);

      JPanel eastYese = new JPanel();
      eastYese.setLayout(new GridLayout(1,2));
      east.add(eastYese);

        JLabel eastYeseText = new JLabel("학기");
        eastYeseText.setHorizontalAlignment(JLabel.CENTER);
        eastYese.add(eastYeseText);
        JTextField eastYeseInput = new JTextField();
        eastYese.add(eastYeseInput);

      JPanel eastGrade = new JPanel();
      eastGrade.setLayout(new GridLayout(1,2));
      east.add(eastGrade);

        JLabel eastGradeText = new JLabel("학년");
        eastGradeText.setHorizontalAlignment(JLabel.CENTER);
        eastGrade.add(eastGradeText);
        JTextField eastGradeInput = new JTextField();
        eastGrade.add(eastGradeInput);

    JPanel eastButton = new JPanel();
    eastButton.setLayout(new GridLayout(3,3));
    eastSouth.add(eastButton);

      JButton eastBtn1= new JButton("모든학생");
      eastBtn1.addActionListener((e)->{
        tb.stuShow();
        deTable.setDataVector(tb.rowDatatwo,tb.columnNameList);
      });
      eastButton.add(eastBtn1);
      JButton eastBtn2 = new JButton("개설학기");
      eastBtn2.addActionListener((e)->{
        tb.checkYese();
        deTable.setDataVector(tb.rowDatatwo,tb.columnNameList);
      });
      eastButton.add(eastBtn2);
      JButton eastBtn3 = new JButton("개설학과");
      eastBtn3.addActionListener((e)->{
        tb.checkMajor();
        deTable.setDataVector(tb.rowDatatwo,tb.columnNameList);
      });
      eastButton.add(eastBtn3);
      JButton eastBtn4 = new JButton("개설강의");
      eastBtn4.addActionListener((e)->{
        if(eastMajorInput.getText().length()==0){
          showmsg.append("학과명 입력후 검색시 학과별 개설강의 조회가능"+"\n");
          tb.checkClass();
          deTable.setDataVector(tb.rowDatatwo,tb.columnNameList);
        }else{
          tb.checkMajorClass(eastMajorInput.getText());
          deTable.setDataVector(tb.rowDatatwo,tb.columnNameList);
        }
      });
      eastButton.add(eastBtn4);
      JButton eastBtn5 = new JButton("수강내역");
      eastBtn5.addActionListener((e)->{
        try{
          if(eastStnumInput.getText().length()==0){
            showmsg.append("학번을 통해 조회하세요."+"\n");
          }else{
            if((eastYeseInput.getText().length()==0)){
              showmsg.append("학기 추가로 입력시 학기별 조회가능"+"\n");
              tb.checkEnrol(Long.parseLong(eastStnumInput.getText()));
              deTable.setDataVector(tb.rowDatatwo,tb.columnNameList);
            }else{
              tb.checkEnrolYese(Long.parseLong(eastStnumInput.getText()), eastYeseInput.getText());
              deTable.setDataVector(tb.rowDatatwo,tb.columnNameList);
            }
          }
        }catch(NumberFormatException nfe){
          showmsg.append("학번란에 숫자만 입력할 수 있습니다."+"\n");
        }
      });
      eastButton.add(eastBtn5);
      JButton eastBtn6 = new JButton("학생정보");
      eastBtn6.addActionListener((e)->{
        try {
          if(eastStnumInput.getText().length()==0){
            showmsg.append("학번을 통해 조회하세요."+"\n");
          }else{
            tb.checkRcard(Long.parseLong(eastStnumInput.getText()));
            deTable.setDataVector(tb.rowDatatwo,tb.columnNameList);
          }
        } catch (NumberFormatException nfe) {
          showmsg.append("학번란에 숫자만 입력할 수 있습니다."+"\n");
        }
      });
      eastButton.add(eastBtn6);

      JButton eastBtn7 = new JButton("장학금");
      eastBtn7.addActionListener((e)->{
        if(eastYeseInput.getText().length()==0){
          showmsg.append("학기를 통해 조회하세요."+"\n");
        }else{
          tb.scholShow(eastYeseInput.getText());
          deTable.setDataVector(tb.rowDatatwo,tb.columnNameList);
        }
      });
      eastButton.add(eastBtn7);

      JButton eastBtn8 = new JButton("원점수");
      eastBtn8.addActionListener((e)->{
        try {
          if(eastStnumInput.getText().length()==0){
            showmsg.append("학번을 통해 조회하세요."+"\n");
          }else{
            if((eastYeseInput.getText().length()==0)){
              showmsg.append("학기 추가로 입력시 학기별 조회가능"+"\n");
              tb.checkProEnt(Long.parseLong(eastStnumInput.getText()));
              deTable.setDataVector(tb.rowDatatwo,tb.columnNameList);
            }else{
              tb.checkProEntYese(Long.parseLong(eastStnumInput.getText()), eastYeseInput.getText());
              deTable.setDataVector(tb.rowDatatwo,tb.columnNameList);
            }
          }
        } catch (NumberFormatException nfe) {
          showmsg.append("학번란에 숫자만 입력할 수 있습니다."+"\n");
        }
      });
      eastButton.add(eastBtn8);
      JButton eastBtn9 = new JButton("+학생정보");
      eastBtn9.addActionListener((e)->{
        try {
          if((eastStnumInput.getText().length()==0)||(eastYeseInput.getText().length()==0)){
            showmsg.append("학번, 학기를 통해 조회하세요."+"\n");
          }else{
            tb.checkYeseCard(Long.parseLong(eastStnumInput.getText()),eastYeseInput.getText());
            deTable.setDataVector(tb.rowDatatwo,tb.columnNameList);
          }
        } catch (NumberFormatException nfe) {
          showmsg.append("학번란에 숫자만 입력할 수 있습니다."+"\n");
        }
      });
      eastButton.add(eastBtn9);

    JPanel searchResult = new JPanel();
    west.add(searchResult);

    JPanel data = new JPanel();
    data.setLayout(new GridLayout(1,4));
    west.add(data, BorderLayout.SOUTH);


    //학생입학
    JPanel adminStu = new JPanel();
    adminStu.setLayout(new GridLayout(8,1));
    data.add(adminStu);

      JPanel stuStnum = new JPanel();
      stuStnum.setLayout(new GridLayout(1,2));
      adminStu.add(stuStnum);

        JLabel stuStnumText = new JLabel("학번");
        stuStnumText.setHorizontalAlignment(JLabel.CENTER);
        stuStnum.add(stuStnumText);

        JTextField stuStnumInput = new JTextField();
        stuStnum.add(stuStnumInput);

      JPanel stuName = new JPanel();
      stuName.setLayout(new GridLayout(1,2));
      adminStu.add(stuName);

        JLabel stuNameText = new JLabel("이름");
        stuNameText.setHorizontalAlignment(JLabel.CENTER);
        stuName.add(stuNameText);

        JTextField stuNameInput = new JTextField();
        stuName.add(stuNameInput);

      JPanel stuEmail = new JPanel();
      stuEmail.setLayout(new GridLayout(1,2));
      adminStu.add(stuEmail);

        JLabel stuEmailText = new JLabel("이메일");
        stuEmailText.setHorizontalAlignment(JLabel.CENTER);
        stuEmail.add(stuEmailText);

        JTextField stuEmailInput = new JTextField();
        stuEmail.add(stuEmailInput);

      JPanel stuMobile = new JPanel();
      stuMobile.setLayout(new GridLayout(1,2));
      adminStu.add(stuMobile);

        JLabel stuMobileText = new JLabel("연락처");
        stuMobileText.setHorizontalAlignment(JLabel.CENTER);
        stuMobile.add(stuMobileText);

        JTextField stuMobileInput = new JTextField();
        stuMobile.add(stuMobileInput);

      JPanel stuStuition = new JPanel();
      stuStuition.setLayout(new GridLayout(1,2));
      adminStu.add(stuStuition);

        JLabel stuStuitionText = new JLabel("등록금");
        stuStuitionText.setHorizontalAlignment(JLabel.CENTER);
        stuStuition.add(stuStuitionText);

        JTextField stuStuFieldInput = new JTextField();
        stuStuition.add(stuStuFieldInput);

      JPanel majorName = new JPanel();
      majorName.setLayout(new GridLayout(1,2));
      adminStu.add(majorName);

        JLabel majorNameText = new JLabel("학과");
        majorNameText.setHorizontalAlignment(JLabel.CENTER);
        majorName.add(majorNameText);

        JTextField majorNameInput = new JTextField();
        majorName.add(majorNameInput);

    JPanel stuem= new JPanel();
    adminStu.add(stuem);

      JButton stuInput = new JButton("입학");
      stuInput.addActionListener((e)->{
        try {
          if((stuStnumInput.getText().length()==0)||(stuNameInput.getText().length()==0)||(stuEmailInput.getText().length()==0)||(stuMobileInput.getText().length()==0)||
          (stuStuFieldInput.getText().length()==0)||(majorNameInput.getText().length()==0)){
            showmsg.append("정보를 모두 입력하세요."+"\n");
          }else{
            tb.adminStu(Long.parseLong(stuStnumInput.getText()), stuNameInput.getText(), stuEmailInput.getText(), stuMobileInput.getText(), Long.parseLong(stuStuFieldInput.getText()), majorNameInput.getText());
            showmsg.append(tb.adminStu);
            tb.stuShow();
            deTable.setDataVector(tb.rowDatatwo,tb.columnNameList);
          }
        } catch (NumberFormatException nfe) {
          showmsg.append("학번, 등록금란에 숫자만 입력할 수 있습니다."+"\n");
        }
      });
      adminStu.add(stuInput);
    

    // 학기등록
    JPanel adminSeme = new JPanel();
    adminSeme.setLayout(new GridLayout(8,1));
    data.add(adminSeme);


      JPanel semeStnum = new JPanel();
      semeStnum.setLayout(new GridLayout(1,2));
      adminSeme.add(semeStnum);

        JLabel semeStnumText = new JLabel("학번");
        semeStnumText.setHorizontalAlignment(JLabel.CENTER);
        semeStnum.add(semeStnumText);
        JTextField semeStnumInput = new JTextField();
        semeStnum.add(semeStnumInput);

      JPanel semeYese = new JPanel();
      semeYese.setLayout(new GridLayout(1,2));
      adminSeme.add(semeYese);

        JLabel semeYeseText = new JLabel("등록학기");
        semeYeseText.setHorizontalAlignment(JLabel.CENTER);
        semeYese.add(semeYeseText);
        JTextField semeYeseInput = new JTextField();
        semeYese.add(semeYeseInput);

    JPanel semeem1 = new JPanel();
    adminSeme.add(semeem1);
    JPanel semeem2 = new JPanel();
    adminSeme.add(semeem2);
    JPanel semeem3 = new JPanel();
    adminSeme.add(semeem3);
    JPanel semeem4 = new JPanel();
    adminSeme.add(semeem4);
    JPanel semeem5 = new JPanel();
    adminSeme.add(semeem5);

      JButton semeInput = new JButton("학기등록");
      semeInput.addActionListener((e)->{
        try {
          if((semeStnumInput.getText().length()==0)||(semeYeseInput.getText().length()==0)){
            showmsg.append("정보를 모두 입력하세요."+"\n");
          }else{
            tb.adminSeme(Long.parseLong(semeStnumInput.getText()), (semeYeseInput.getText()));
            showmsg.append(tb.adminSeme);
            tb.adminSemeRe(Long.parseLong(semeStnumInput.getText()));
            deTable.setDataVector(tb.rowDatatwo,tb.columnNameList);
          }
        } catch (NumberFormatException nfe) {
          showmsg.append("학번란에 숫자만 입력할 수 있습니다."+"\n");
        }
      });
      adminSeme.add(semeInput);

    //성적부여
    JPanel giveGrade = new JPanel();
    giveGrade.setLayout(new GridLayout(8,1));
    data.add(giveGrade);


      JPanel enrolYese = new JPanel();
      enrolYese.setLayout(new GridLayout(1,2));
      giveGrade.add(enrolYese);

        JLabel enrolYeseText = new JLabel("학기");
        enrolYeseText.setHorizontalAlignment(JLabel.CENTER);
        enrolYese.add(enrolYeseText);

        JTextField enrolYeseInput = new JTextField();
        enrolYese.add(enrolYeseInput);

      JPanel enrolStnum = new JPanel();
      enrolStnum.setLayout(new GridLayout(1,2));
      giveGrade.add(enrolStnum);

        JLabel enrolStnumText = new JLabel("학번");
        enrolStnumText.setHorizontalAlignment(JLabel.CENTER);
        enrolStnum.add(enrolStnumText);

        JTextField enrolStnumInput = new JTextField();
        enrolStnum.add(enrolStnumInput);

      JPanel enrolCcode = new JPanel();
      enrolCcode.setLayout(new GridLayout(1,2));
      giveGrade.add(enrolCcode);

        JLabel enrolCcodeText = new JLabel("강의코드");
        enrolCcodeText.setHorizontalAlignment(JLabel.CENTER);
        enrolCcode.add(enrolCcodeText);

        JTextField enrolCcodeInput = new JTextField();
        enrolCcode.add(enrolCcodeInput);

      JPanel giveGradee = new JPanel();
      giveGradee.setLayout(new GridLayout(1,2));
      giveGrade.add(giveGradee);

        JLabel giveGradeText = new JLabel("점수");
        giveGradee.add(giveGradeText);
        giveGradeText.setHorizontalAlignment(JLabel.CENTER);
        JTextField giveGradeInput = new JTextField();
        giveGradee.add(giveGradeInput);

      JPanel gem1 = new JPanel();
      giveGrade.add(gem1);
      JPanel gem2 = new JPanel();
      giveGrade.add(gem2);
      JPanel gem3 = new JPanel();
      giveGrade.add(gem3);

      JButton enrolInput = new JButton("성적입력");
      enrolInput.addActionListener((e)->{
        try {
          if((enrolYeseInput.getText().length()==0)||(enrolStnumInput.getText().length()==0)||(enrolCcodeInput.getText().length()==0)||(giveGradeInput.getText().length()==0)){
            showmsg.append("정보를 모두 입력하세요."+"\n");
          }else{
            tb.giveGp(Float.parseFloat(giveGradeInput.getText()), enrolYeseInput.getText(), Long.parseLong(enrolStnumInput.getText()), enrolCcodeInput.getText());
            tb.checkEnrolYese(Long.parseLong(enrolStnumInput.getText()), enrolYeseInput.getText());
            deTable.setDataVector(tb.rowDatatwo,tb.columnNameList);
          }
        } catch (NumberFormatException nfe) {
          showmsg.append("학번, 점수란에 숫자만 입력할 수 있습니다."+"\n");
        }
      });
      giveGrade.add(enrolInput);

    //장학금 수여 
    JPanel giveSchol = new JPanel();
    giveSchol.setLayout(new GridLayout(8,1));
    data.add(giveSchol);

      JPanel scholMajorName = new JPanel();
      scholMajorName.setLayout(new GridLayout(1,2));
      giveSchol.add(scholMajorName);

        JLabel scholMajorNameTEXT = new JLabel("학과");
        scholMajorNameTEXT.setHorizontalAlignment(JLabel.CENTER);
        scholMajorName.add(scholMajorNameTEXT);
        JTextField scholMajorNameInput = new JTextField();
        scholMajorName.add(scholMajorNameInput);
  
      JPanel scholGrade = new JPanel();
      scholGrade.setLayout(new GridLayout(1,2));
      giveSchol.add(scholGrade);

        JLabel scholGradeText = new JLabel("학년");
        scholGradeText.setHorizontalAlignment(JLabel.CENTER);
        scholGrade.add(scholGradeText);
        JTextField scholGradeInput = new JTextField();
        scholGrade.add(scholGradeInput);

      JPanel scholYese = new JPanel();
      scholYese.setLayout(new GridLayout(1,2));
      giveSchol.add(scholYese);

        JLabel scholYeseText = new JLabel("학기");
        scholYeseText.setHorizontalAlignment(JLabel.CENTER);
        scholYese.add(scholYeseText);
        JTextField scholYeseInput = new JTextField();
        scholYese.add(scholYeseInput);

    JPanel scholem1 = new JPanel();
    giveSchol.add(scholem1);
    JPanel scholem2 = new JPanel();
    giveSchol.add(scholem2);
    JPanel scholem3 = new JPanel();
    giveSchol.add(scholem3);
    JPanel scholem4 = new JPanel();
    giveSchol.add(scholem4);

      JButton scholYeseButton = new JButton("장학금수여");
      scholYeseButton.addActionListener((e)->{
        try {
          if(scholMajorNameInput.getText().length()==0||scholGradeInput.getText().length()==0||scholYeseInput.getText().length()==0){
            showmsg.append("정보를 모두 입력하세요."+"\n");
          }else{
            tb.scholar(scholMajorNameInput.getText(), Integer.parseInt(scholGradeInput.getText()),scholYeseInput.getText());
            deTable.setDataVector(tb.rowDatatwo,tb.columnNameList);
          }
        } catch (NumberFormatException nfe) {
          showmsg.append("학년란에 숫자만 입력할 수 있습니다."+"\n");
        }
      });
      giveSchol.add(scholYeseButton);
    
    JScrollPane sp= new JScrollPane(dataTable);
    sp.setPreferredSize(new Dimension(1040, 470));
    searchResult.add(sp);

  }

  void setUI(){
    setTitle("Gasan University / MANAGER");
    setSize(1400,780);
    setResizable(false);
    setVisible(true);
    setLocationRelativeTo(null);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e){
        setVisible(false);
      }
    });
  }
}
