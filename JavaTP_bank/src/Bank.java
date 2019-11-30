
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.*;

public class Bank extends JFrame implements ActionListener {
    static ArrayList<Info> info = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // login 로그인(완료)
    // create 계좌 생성(완료)
    // deposit 입금(완료)
    // withdrawal 출금
    // remittance 송금
    // user 유저정보
    // record 입출금기록

    Container container;
    JFrame F_login, F_choose, F_deposit, F_withdrawal, F_remittance, F_user, F_record, F_create;
    JDialog D_login_error, D_create_error, D_info, D_withdrawal_error, D_withdrawal_confirm,D_deposit_confirm,D_remittance_error;
    JTextField T_account, T_money, T_name, T_Createpassword;
    JPasswordField T_password;
    JButton btn_login, btn_create, btn_exit, btn_login_confirm, btn_login_cancel, btn_login_error_confirm,
            btn_create_error_confirm, btn_create_error_cancel, btn_info_confirm, btn_deposit, btn_withdrawal, btn_remittance, btn_user,
            btn_record, btn_deposit_confirm, btn_deposit_cancel,btn_deposit_check, btn_withdrawal_confirm, btn_withdrawal_cancel,
            btn_remittance_confirm, btn_remittance_cancel,btn_remittance_error, btn_user_confirm, btn_record_confirm, btn_create_confirm,
            btn_create_cancel, btn_withdrawal_error, btn_withdrawal_check,btn_choose_exit;

    int myindex = 0, flag = 0;
    String info_path, record_path;

    Bank() {
        first();
        container = this.getContentPane();
        container.setBackground(Color.decode("#BAE8E8"));

        btn_login = new JButton("로그인");
        btn_login.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
        btn_login.setBounds(30, 25, 90, 35);
        btn_login.setBackground(Color.decode("#DFF6F0"));
        btn_login.setFocusPainted(false);

        btn_create = new JButton("계좌 생성");
        btn_create.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
        btn_create.setBackground(Color.decode("#DFF6F0"));
        btn_create.setFocusPainted(false);

        btn_exit = new JButton("나가기");
        btn_exit.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
        btn_exit.setBackground(Color.decode("#DFF6F0"));
        btn_exit.setFocusPainted(false);

        btn_login_confirm = new JButton("확인");
        btn_login_cancel = new JButton("취소");

        btn_create.setBounds(150, 25, 110, 35);
        btn_exit.setBounds(85, 90, 90, 35);

        container.add(btn_login);
        container.add(btn_create);
        container.add(btn_exit);

        btn_login.addActionListener(this);
        btn_exit.addActionListener(this);
        btn_create.addActionListener(this);

        this.setTitle("은행");
        this.setLayout(null);
        this.setSize(300, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_login) {    //로그인
            login();
        } else if (e.getSource() == btn_create) {    //계좌 생성
            F_create = new JFrame("계좌 생성");
            JPanel P_create = new JPanel();
            P_create.setBackground(Color.decode("#BAE8E8"));
            btn_create_confirm = new JButton("생성");
            btn_create_confirm.setBackground(Color.decode("#DFF6F0"));

            btn_create_cancel = new JButton("취소");
            btn_create_cancel.setBackground(Color.decode("#DFF6F0"));


            JLabel L_create = new JLabel("이름 : ");
            L_create.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
            L_create.setHorizontalAlignment(JLabel.CENTER);

            JLabel L_password = new JLabel("비밀 번호(숫자 4자리) : ");
            L_password.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));

            T_name = new JTextField("", 10);
            T_Createpassword = new JTextField("", 10);


            btn_create_confirm.addActionListener(this);
            btn_create_cancel.addActionListener(this);

            F_create.add(P_create);
            P_create.add(L_create);
            P_create.add(T_name);
            P_create.add(L_password);
            P_create.add(T_Createpassword);
            P_create.add(btn_create_confirm);
            P_create.add(btn_create_cancel);

            P_create.setLayout(new FlowLayout());
            F_create.setSize(300, 150);
            F_create.setVisible(true);

        } else if (e.getSource() == btn_create_confirm) {    //계좌 생성 확인
            int i;
            for (i = 0; i < info.size(); i++) {
                if ((T_name.getText().equals(info.get(i).name))) {
                    D_create_error = new JDialog();
                    D_create_error.setTitle("중복 에러");
                    D_create_error.setLayout(null);
                    D_create_error.getContentPane().setForeground(Color.decode("#BAE8E8"));
                    D_create_error.getContentPane().setBackground(Color.decode("#BAE8E8"));
                    D_create_error.setSize(280, 150);

                    JLabel L_create_error1 = new JLabel("동일한 이름의 사용자가 있습니다");
                    L_create_error1.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
                    L_create_error1.setHorizontalAlignment(JLabel.CENTER);
                    L_create_error1.setBounds(0, 0, 270, 25);

                    JLabel L_create_error2 = new JLabel("생성하시겠습니까?");
                    L_create_error2.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
                    L_create_error2.setHorizontalAlignment(JLabel.CENTER);
                    L_create_error2.setBounds(0, 30, 270, 25);

                    btn_create_error_confirm = new JButton("확인");
                    btn_create_error_confirm.setBackground(Color.decode("#DFF6F0"));
                    btn_create_error_confirm.setBounds(65, 65, 60, 25);
                    btn_create_error_confirm.setFocusPainted(false);

                    btn_create_error_cancel = new JButton("취소");
                    btn_create_error_cancel.setBackground(Color.decode("#DFF6F0"));
                    btn_create_error_cancel.setBounds(135, 65, 60, 25);
                    btn_create_error_cancel.setFocusPainted(false);

                    D_create_error.add(L_create_error1);
                    D_create_error.add(L_create_error2);
                    D_create_error.add(btn_create_error_confirm);
                    D_create_error.add(btn_create_error_cancel);
                    D_create_error.setVisible(true);

                    btn_create_error_confirm.addActionListener(this);
                    btn_create_error_cancel.addActionListener(this);
                    break;
                }
            }
            if (i == info.size()) {
                F_create.dispose();
                create();
            }
        } else if (e.getSource() == btn_create_cancel) { //계좌 생성 취소
            F_create.dispose();
        } else if (e.getSource() == btn_exit) { //나가기
            exit();
        } else if (e.getSource() == btn_create_error_confirm) { //계좌 중복 생성 확인
            D_create_error.dispose();
            F_create.dispose();
            create();
        } else if (e.getSource() == btn_create_error_cancel) { //계좌 중복 생성 취소
            D_create_error.dispose();
        } else if (e.getSource() == btn_login_confirm) {
            int cnt = 0;
            if (flag == 0) {
                while (cnt < info.size()) {
                    if (T_account.getText().equals(info.get(cnt).account) && T_password.getText().equals(info.get(cnt).password)) {
                        flag = 1;
                        F_choose = new JFrame("선택");
                        F_login.setVisible(false);
                        myindex = cnt;

                        record_path+= "\\shubbing\\" + info.get(myindex).name + "_" + info.get(myindex).account + ".txt";

                        F_choose.setSize(530, 400);
                        F_choose.setLayout(null);
                        F_choose.getContentPane().setBackground(Color.decode("#BAE8E8"));
                        F_choose.setVisible(true);

                        btn_deposit = new JButton("입금");
                        btn_deposit.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
                        btn_deposit.setBackground(Color.decode("#DFF6F0"));
                        btn_deposit.setFocusPainted(false);
                        btn_deposit.setBounds(25, 150, 80, 30);

                        btn_withdrawal = new JButton("출금");
                        btn_withdrawal.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
                        btn_withdrawal.setBackground(Color.decode("#DFF6F0"));
                        btn_withdrawal.setFocusPainted(false);
                        btn_withdrawal.setBounds(115, 150, 80, 30);

                        btn_remittance = new JButton("송금");
                        btn_remittance.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
                        btn_remittance.setBackground(Color.decode("#DFF6F0"));
                        btn_remittance.setFocusPainted(false);
                        btn_remittance.setBounds(205, 150, 80, 30);

                        btn_user = new JButton("정보");
                        btn_user.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
                        btn_user.setBackground(Color.decode("#DFF6F0"));
                        btn_user.setFocusPainted(false);
                        btn_user.setBounds(295, 150, 80, 30);

                        btn_record = new JButton("입출금기록");
                        btn_record.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
                        btn_record.setBackground(Color.decode("#DFF6F0"));
                        btn_record.setFocusPainted(false);
                        btn_record.setBounds(385, 150, 110, 30);

                        btn_choose_exit = new JButton("나가기");
                        btn_choose_exit.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
                        btn_choose_exit.setBackground(Color.decode("#DFF6F0"));
                        btn_choose_exit.setFocusPainted(false);
                        btn_choose_exit.setBounds(205, 200, 80, 30);

                        F_choose.add(btn_deposit);
                        F_choose.add(btn_withdrawal);
                        F_choose.add(btn_remittance);
                        F_choose.add(btn_user);
                        F_choose.add(btn_record);
                        F_choose.add(btn_choose_exit);

                        btn_deposit.addActionListener(this);
                        btn_withdrawal.addActionListener(this);
                        btn_remittance.addActionListener(this);
                        btn_user.addActionListener(this);
                        btn_record.addActionListener(this);
                        btn_choose_exit.addActionListener(this);

                        F_choose.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        break;
                    } else {
                        cnt++;
                    }
                }
                if (cnt == info.size()) {
                    D_login_error = new JDialog();
                    D_login_error.setTitle("에러");
                    D_login_error.setSize(300, 100);
                    D_login_error.setLayout(new FlowLayout());
                    D_login_error.getContentPane().setBackground(Color.decode("#BAE8E8"));

                    JLabel error_msg = new JLabel("계좌번호 또는 비밀번호가 틀렸습니다.");
                    error_msg.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));

                    btn_login_error_confirm = new JButton("확인");
                    btn_login_error_confirm.setBackground(Color.decode("#DFF6F0"));
                    btn_login_error_confirm.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));

                    D_login_error.add(error_msg);
                    D_login_error.add(btn_login_error_confirm);

                    btn_login_error_confirm.addActionListener(this);
                    D_login_error.setVisible(true);
                }
            }
        } else if (e.getSource() == btn_login_cancel) {
            F_login.dispose();
        } else if (e.getSource() == btn_login_error_confirm) {
            D_login_error.dispose();
        } else if (e.getSource() == btn_info_confirm) {
            D_info.dispose();
        } else if (e.getSource() == btn_deposit) {
            F_choose.dispose();
            deposit();
        } else if (e.getSource() == btn_withdrawal) {
            F_choose.dispose();
            withdrawal();
        } else if (e.getSource() == btn_remittance) {
            F_choose.dispose();
            remittance();
        } else if (e.getSource() == btn_user) {
            F_choose.dispose();
            user();
        } else if (e.getSource() == btn_record) {
            F_choose.dispose();
            F_record = new JFrame();
            F_record.setSize(500,500);
            F_record.setVisible(true);

            String path = System.getProperty("user.dir");
            path += "\\shubbing\\"+info.get(myindex).name+"_"+info.get(myindex).account+".txt";
            try {
                BufferedReader br = new BufferedReader(new FileReader(path));

                JTextArea A_record = new JTextArea(480,480);
                A_record.setFont(new Font("맑은 고딕",Font.CENTER_BASELINE,15));
                A_record.setEditable(false);

                JScrollPane scroll_record = new JScrollPane(A_record);
                scroll_record.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);


                String str=br.readLine()+"\n";
                String record =  br.readLine();
                while(record != null) {
                    str+= record+"\n";
                    record=br.readLine();
                }
                A_record.setText(str);

                btn_record_confirm = new JButton("확인");
                btn_record_confirm.setBackground(Color.decode("#DFF6F0"));
                btn_record_confirm.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
                btn_record_confirm.setFocusPainted(false);
                btn_record_confirm.setSize(80,30);

                F_record.getContentPane().add("Center",scroll_record);
                F_record.getContentPane().add("South",btn_record_confirm);
                btn_record_confirm.addActionListener(this);
                F_record.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                br.close();
            }catch (IOException e1) {
                System.out.println("에러");
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }else if (e.getSource() == btn_choose_exit) {
            F_choose.dispose();
            F_choose.setVisible(false);
            flag= 0;
            F_choose = null;
        } else if(e.getSource() == btn_record_confirm) {
            F_record.dispose();
            F_choose.setVisible(true);
        }else if (e.getSource() == btn_deposit_confirm) {

            F_deposit.dispose();
            D_deposit_confirm = new JDialog();
            D_deposit_confirm.setSize(200, 100);

            JLabel L_confirm = new JLabel(Integer.parseInt(T_money.getText()) + " 원을 입금합니다.");
            L_confirm.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));

            info.get(myindex).money += Long.parseLong(T_money.getText());
            F_write();
            record("입금",T_money.getText());
            JLabel L_money = new JLabel("잔액 : " + info.get(myindex).money);
            L_money.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));

            btn_deposit_check = new JButton("확인");
            btn_deposit_check.setFocusPainted(false);

            D_deposit_confirm.setLayout(new FlowLayout());
            D_deposit_confirm.add(L_confirm);
            D_deposit_confirm.add(L_money);
            D_deposit_confirm.add(btn_deposit_check);
            btn_deposit_check.addActionListener(this);
            D_deposit_confirm.setVisible(true);
            F_write();

        } else if (e.getSource() == btn_deposit_cancel) {
            F_deposit.dispose();
            F_choose.setVisible(true);
        }
        else if (e.getSource() == btn_deposit_check) {
            D_deposit_confirm.dispose();
            F_deposit.dispose();
            F_choose.setVisible(true);
        }
        else if (e.getSource() == btn_withdrawal_confirm) {
            if (info.get(myindex).money < Integer.parseInt(T_money.getText())) {
                D_withdrawal_error = new JDialog();
                D_withdrawal_error.setSize(150, 150);

                JLabel L_error = new JLabel("잔액이 부족합니다");
                L_error.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));

                JLabel L_money = new JLabel("잔액 : " + info.get(myindex).money);
                L_money.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));

                btn_withdrawal_error = new JButton("확인");
                btn_withdrawal_error.setFocusPainted(false);

                D_withdrawal_error.setLayout(new FlowLayout());
                D_withdrawal_error.add(L_error);
                D_withdrawal_error.add(L_money);
                D_withdrawal_error.add(btn_withdrawal_error);

                btn_withdrawal_error.addActionListener(this);

                D_withdrawal_error.setVisible(true);
            } else {
                D_withdrawal_confirm = new JDialog();
                D_withdrawal_confirm.setSize(200, 100);

                JLabel L_confirm = new JLabel(Integer.parseInt(T_money.getText()) + " 원을 출금합니다.");
                L_confirm.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));

                info.get(myindex).money -= Integer.parseInt(T_money.getText());
                F_write();
                record("출금",T_money.getText());
                JLabel L_money = new JLabel("잔액 : " + info.get(myindex).money);
                L_money.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));

                btn_withdrawal_check = new JButton("확인");
                btn_withdrawal_check.setFocusPainted(false);

                D_withdrawal_confirm.setLayout(new FlowLayout());
                D_withdrawal_confirm.add(L_confirm);
                D_withdrawal_confirm.add(L_money);
                D_withdrawal_confirm.add(btn_withdrawal_check);
                btn_withdrawal_check.addActionListener(this);
                D_withdrawal_confirm.setVisible(true);
            }
            F_withdrawal.dispose();
            F_choose.setVisible(true);
        } else if (e.getSource() == btn_withdrawal_cancel) {
            F_withdrawal.dispose();
            F_choose.setVisible(true);
        } else if (e.getSource() == btn_withdrawal_error) {
            D_withdrawal_error.dispose();
            T_money.setText("");
            T_money.requestFocus();
        } else if (e.getSource() == btn_withdrawal_check) {
            D_withdrawal_confirm.dispose();
        }else if (e.getSource() == btn_remittance_confirm) {
            int i;
            F_remittance.dispose();
            for(i=0;i<info.size();i++){
                if((T_account.getText()).equals(info.get(i).account)){
                    if (info.get(myindex).money < Integer.parseInt(T_money.getText())) {
                        D_remittance_error = new JDialog();
                        D_remittance_error.setSize(150, 150);

                        JLabel L_error = new JLabel("잔액이 부족합니다");
                        L_error.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));

                        JLabel L_money = new JLabel("잔액 : " + info.get(myindex).money);
                        L_money.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));

                        btn_remittance_error = new JButton("확인");
                        btn_remittance_error.setFocusPainted(false);

                        D_remittance_error.setLayout(new FlowLayout());
                        D_remittance_error.add(L_error);
                        D_remittance_error.add(L_money);
                        D_remittance_error.add(btn_remittance_error);

                        btn_remittance_error.addActionListener(this);
                        D_remittance_error.setVisible(true);
                        break;
                    }
                    info.get(i).money+=Integer.parseInt(T_money.getText());
                    info.get(myindex).money-=Integer.parseInt(T_money.getText());
                    F_write();
                    record("송금",T_money.getText(),T_account.getText());
                    break;
                }
            }
            if(i==info.size()){
                D_remittance_error = new JDialog();
                D_remittance_error.setSize(300, 150);

                JLabel L_error = new JLabel("잘못된 계좌번호입니다.");
                L_error.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
                btn_remittance_error = new JButton("확인");
                btn_remittance_error.setFocusPainted(false);

                D_remittance_error.setLayout(new FlowLayout());
                D_remittance_error.add(L_error);
                D_remittance_error.add(btn_remittance_error);
                btn_remittance_error.addActionListener(this);
                D_remittance_error.setVisible(true);
            }
            F_choose.setVisible(true);
        }else if (e.getSource() == btn_remittance_cancel) {
            F_remittance.dispose();
            F_choose.setVisible(true);
        }
        else if (e.getSource() == btn_remittance_error) {
            D_remittance_error.dispose();
            T_account.setText("");
            T_account.requestFocus();
            T_money.setText("");
        }else if(e.getSource() == btn_user_confirm) {
            F_user.dispose();
            F_user.setVisible(false);
            F_user=null;
            F_choose.setVisible(true);
        }
    }

    public void first() {
        File file;
        info_path = System.getProperty("user.dir");
        record_path = info_path;
        info_path += "\\shubbing\\cus_Info.txt";
        try {
            file = new File(info_path);
            FileReader fr = new FileReader(file);
            BufferedReader f_br = new BufferedReader(fr);
            StringTokenizer str;
            String tmp = f_br.readLine();
            while (tmp != null) {
                str = new StringTokenizer(tmp, "\t");
                info.add(new Info(str.nextToken(), str.nextToken(), str.nextToken(), Integer.parseInt(str.nextToken())));
                tmp = f_br.readLine();
            }
        } catch (IOException e) {
            System.out.println("예외 발생");
        }
    }

    public void login() { // 로그인
        F_login = new JFrame("로그인");
        JPanel P_login = new JPanel();
        P_login.setBackground(Color.decode("#BAE8E8"));

        JLabel L_account = new JLabel("계좌 번호 : ");
        L_account.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
        L_account.setHorizontalAlignment(JLabel.CENTER);

        JLabel L_password = new JLabel("비밀 번호 : ");
        L_password.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));

        T_account = new JTextField("", 10);
        T_password = new JPasswordField("", 10);

        btn_login_confirm.setBackground(Color.decode("#DFF6F0"));
        btn_login_cancel.setBackground(Color.decode("#DFF6F0"));

        btn_login_confirm.addActionListener(this);
        btn_login_cancel.addActionListener(this);

        F_login.add(P_login);
        P_login.add(L_account);
        P_login.add(T_account);
        P_login.add(L_password);
        P_login.add(T_password);
        P_login.add(btn_login_confirm);
        P_login.add(btn_login_cancel);
        P_login.setLayout(new FlowLayout());
        F_login.setSize(250, 125);
        F_login.setVisible(true);
    }

    public void create() { // 계좌생성
        long account = 0;
        int check;
        String account_S;
        Random ran = new Random();
        while (true) {
            for (int i = 0; i < 10; i++) {
                account += ran.nextInt(9) + 1;
                account *= 10;
            }
            account /= 10;
            account_S = Long.toString(account);

            for (check = 0; check < info.size(); check++) {
                if (account_S.equals(info.get(check).account))
                    break;
            }
            if (check == info.size())
                break;
        }
        try {
            FileWriter fw = new FileWriter(info_path, true);
            fw.write(T_name.getText() + "\t" + account_S + "\t" + T_Createpassword.getText() + "\t" + "0" + "\n"); // 이름   ///계좌정보 //비밀번호 //잔고
            info.add(new Info(T_name.getText(), account_S, T_Createpassword.getText(), 0));
            fw.close();
        } catch (IOException E) {
            System.out.println("예외 발생");
        }
        record_path=System.getProperty("user.dir");
        record_path += "\\shubbing\\" + T_name.getText() + "_" + account_S + ".txt";
        File recordfile = new File(record_path);

        try {
            recordfile.createNewFile();
        } catch (IOException E) {
            System.out.println("record 파일 만들기 오류");
        }
        record_path= System.getProperty("user.dir");
        D_info = new JDialog();
        D_info.setTitle("정보");
        D_info.setSize(300, 150);
        D_info.setLayout(null);
        D_info.getContentPane().setBackground(Color.decode("#BAE8E8"));
        JLabel L_account = new JLabel("계좌 : " + account_S);
        L_account.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
        L_account.setBounds(80, 0, 300, 25);

        JLabel L_name = new JLabel("이름 : " + T_name.getText());
        L_name.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
        L_name.setBounds(80, 27, 300, 25);

        JLabel L_password = new JLabel("비밀번호 : " + T_Createpassword.getText());
        L_password.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
        L_password.setBounds(80, 54, 300, 25);

        btn_info_confirm = new JButton("확인");
        btn_info_confirm.setBounds(110, 81, 60, 25);
        btn_info_confirm.setBackground(Color.decode("#DFF6F0"));
        btn_info_confirm.setFocusPainted(false);

        btn_info_confirm.addActionListener(this);

        D_info.add(L_account);
        D_info.add(L_name);
        D_info.add(L_password);
        D_info.add(btn_info_confirm);
        D_info.setVisible(true);
    }

    public void deposit() { // 입금
        F_deposit = new JFrame();
        F_deposit.setSize(300, 200);
        F_deposit.setLayout(null);
        F_deposit.getContentPane().setBackground(Color.decode("#BAE8E8"));

        JLabel L_money = new JLabel("금액 : ");
        L_money.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
        L_money.setBounds(50, 50, 50, 20);

        btn_deposit_confirm = new JButton("확인");
        btn_deposit_confirm.setBackground(Color.decode("#DFF6F0"));
        btn_deposit_confirm.setBounds(40, 100, 80, 30);

        btn_deposit_cancel = new JButton("취소");
        btn_deposit_cancel.setBackground(Color.decode("#DFF6F0"));
        btn_deposit_cancel.setBounds(160, 100, 80, 30);


        T_money = new JTextField("", 20);
        T_money.setBounds(100, 50, 100, 25);

        btn_deposit_confirm.addActionListener(this);
        btn_deposit_cancel.addActionListener(this);

        F_deposit.add(L_money);
        F_deposit.add(T_money);
        F_deposit.add(btn_deposit_confirm);
        F_deposit.add(btn_deposit_cancel);
        F_deposit.setVisible(true);
    }

    public void withdrawal() { // 출금
        F_withdrawal = new JFrame();
        F_withdrawal.setSize(300, 200);
        F_withdrawal.setLayout(null);
        F_withdrawal.getContentPane().setBackground(Color.decode("#BAE8E8"));
        JLabel L_money = new JLabel("금액 : ");
        L_money.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
        L_money.setBounds(50, 50, 50, 20);

        T_money = new JTextField("", 20);
        T_money.setBounds(100, 50, 100, 25);

        btn_withdrawal_confirm = new JButton("확인");
        btn_withdrawal_confirm.setBackground(Color.decode("#DFF6F0"));
        btn_withdrawal_confirm.setBounds(40, 100, 80, 30);

        btn_withdrawal_cancel = new JButton("취소");
        btn_withdrawal_cancel.setBackground(Color.decode("#DFF6F0"));
        btn_withdrawal_cancel.setBounds(160, 100, 80, 30);

        btn_withdrawal_confirm.addActionListener(this);
        btn_withdrawal_cancel.addActionListener(this);


        F_withdrawal.add(L_money);
        F_withdrawal.add(T_money);
        F_withdrawal.add(btn_withdrawal_confirm);
        F_withdrawal.add(btn_withdrawal_cancel);
        F_withdrawal.setVisible(true);
    }

    public void remittance() { // 송금
        F_remittance = new JFrame();
        F_remittance.setSize(380, 200);
        F_remittance.setLayout(null);
        F_remittance.getContentPane().setBackground(Color.decode("#BAE8E8"));

        JLabel L_account = new JLabel("송금할 계좌번호 : ");
        L_account.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
        L_account.setBounds(50, 20, 300, 20);

        JLabel L_money = new JLabel("금액 : ");
        L_money.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
        L_money.setBounds(100, 50, 50, 20);

        btn_remittance_confirm = new JButton("확인");
        btn_remittance_confirm.setBackground(Color.decode("#DFF6F0"));
        btn_remittance_confirm.setBounds(40, 100, 80, 30);

        btn_remittance_cancel = new JButton("취소");
        btn_remittance_cancel.setBackground(Color.decode("#DFF6F0"));
        btn_remittance_cancel.setBounds(160, 100, 80, 30);

        T_account = new JTextField("", 30);
        T_account.setBounds(190, 20, 100, 25);
        T_money = new JTextField("", 20);
        T_money.setBounds(180, 50, 100, 25);

        btn_remittance_confirm.addActionListener(this);
        btn_remittance_cancel.addActionListener(this);

        F_remittance.add(L_money);
        F_remittance.add(T_money);
        F_remittance.add(L_account);
        F_remittance.add(T_account);
        F_remittance.add(btn_remittance_confirm);
        F_remittance.add(btn_remittance_cancel);
        F_remittance.setVisible(true);
    }

    public void user() { // 계좌정보
        F_user = new JFrame();
        F_user.setSize(300,200);
        F_user.setLayout(null);
        F_user.getContentPane().setBackground(Color.decode("#BAE8E8"));

        JLabel L_name = new JLabel("이름 : "+ info.get(myindex).name);
        L_name.setFont(new Font("말은 고딕",Font.CENTER_BASELINE,15));
        L_name.setBounds(90,0,300,25);
        JLabel L_account = new JLabel("계좌 번호 : "+info.get(myindex).account);
        L_account.setFont(new Font("말은 고딕",Font.CENTER_BASELINE,15));
        L_account.setBounds(90,30,300,25);

        JLabel L_password = new JLabel("비밀 번호 : "+info.get(myindex).password);
        L_password.setFont(new Font("말은 고딕",Font.CENTER_BASELINE,15));
        L_password.setBounds(90,60,300,25);

        JLabel L_money = new JLabel("잔액 : "+info.get(myindex).money);
        L_money.setFont(new Font("말은 고딕",Font.CENTER_BASELINE,15));
        L_money.setBounds(90,90,300,25);

        btn_user_confirm = new JButton("확인");
        btn_user_confirm.setFont(new Font("말은 고딕",Font.CENTER_BASELINE,15));
        btn_user_confirm.setBackground(Color.decode("#DFF6F0"));
        btn_user_confirm.setFocusPainted(false);
        btn_user_confirm.setBounds(100,120,80,30);

        F_user.add(L_name);
        F_user.add(L_account);
        F_user.add(L_password);
        F_user.add(L_money);
        F_user.add(btn_user_confirm);
        F_user.setVisible(true);
        btn_user_confirm.addActionListener(this);
    }

    public void record(String what,String money) { // 입출금기록
        try {
            FileWriter fw= new FileWriter(record_path,true);
            fw.write("[ "+what+" ]"+" : "+money+" 원을 "+ what +" 하였습니다. | 잔금 : "+info.get(myindex).money+"\n");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void record(String what,String money,String toAccount) { // 입출금기록
        try {
            FileWriter fw= new FileWriter(record_path,true);
            fw.write("[ "+ what +" ]"+" : "+ money +" 원을 "+ toAccount +" 로 " + what +" 하였습니다. | 잔금 : "+info.get(myindex).money+"\n");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exit() { // 나가기
        System.exit(0);
    }

    public void F_write() {
        try {
            FileWriter fw = new FileWriter(info_path);
            for (int i = 0; i < info.size(); i++) {
                fw.write(info.get(i).name + "\t" + info.get(i).account + "\t" + info.get(i).password + "\t" + info.get(i).money + "\n");
            }
            fw.close();
            F_choose.setVisible(true);
        } catch (IOException E) {

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Bank bank = new Bank();
        new Initial_Setting();
    }
}