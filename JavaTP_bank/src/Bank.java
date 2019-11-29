
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
	static int myindex = 0;

	// login 로그인
	// create 계좌 생성
	// deposit 입금
	// withdrawal 출금
	// remittance 송금
	// user 유저정보
	// record 입출금기록

	Container container;
	JFrame F_login, F_choose, F_deposit, F_withdrawal, F_remittance, F_user, F_record, F_create;
	JDialog D_login_error, D_create_error;
	JTextField T_account, T_money, T_name, T_Createpassword;
	JPasswordField T_password;
	JButton btn_login, btn_create, btn_exit, btn_login_confirm, btn_login_cancel, btn_login_error_confirm,
			btn_create_error_confirm, btn_create_error_cancel, btn_deposit, btn_withdrawal, btn_remittance, btn_user,
			btn_record, btn_deposit_confirm, btn_deposit_cancel, btn_withdrawal_confirm, btn_withdrawal_cancel,
			btn_remittance_confirm, btn_remittance_cancel, btn_user_confirm, btn_record_confirm, btn_create_confirm,
			btn_create_cancel;

	int cnt = 0;
	String info_path, record_path;

	Bank() {
		first();
		container = this.getContentPane();
		btn_login = new JButton("로그인");
		btn_login.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
		btn_create = new JButton("계좌 생성");
		btn_create.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
		btn_exit = new JButton("나가기");
		btn_exit.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
		btn_login_confirm = new JButton("확인");
		btn_login_cancel = new JButton("취소");

		btn_login.setBounds(30, 25, 90, 35);
		btn_create.setBounds(150, 25, 110, 35);
		btn_exit.setBounds(85, 90, 90, 35);

		container.add(btn_login);
		container.add(btn_create);
		container.add(btn_exit);
		container.setBackground(Color.decode("#BAE8E8"));

		btn_login.setBackground(Color.decode("#DFF6F0"));
		btn_login.setFocusPainted(false);

		btn_create.setBackground(Color.decode("#DFF6F0"));
		btn_create.setFocusPainted(false);

		btn_exit.setBackground(Color.decode("#DFF6F0"));
		btn_exit.setFocusPainted(false);

		btn_login.addActionListener(this);
		btn_exit.addActionListener(this);
		btn_create.addActionListener(this);

		this.setTitle("은행");
		this.setLayout(null);
		this.setSize(300, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_login) {	//로그인
			login();
		} else if (e.getSource() == btn_create) {	//계좌 생성
			F_create = new JFrame("계좌 생성");
			JPanel P_create = new JPanel();
			btn_create_confirm = new JButton("생성");
			btn_create_cancel = new JButton("취소");

			P_create.setBackground(Color.decode("#BAE8E8"));
			JLabel L_create = new JLabel("이름 : ");
			L_create.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
			L_create.setHorizontalAlignment(JLabel.CENTER);
			JLabel L_password = new JLabel("비밀 번호(숫자 4자리) : ");
			L_password.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
			T_name = new JTextField("", 10);
			T_Createpassword = new JTextField("", 10);
			btn_create_confirm.setBackground(Color.decode("#DFF6F0"));
			btn_create_cancel.setBackground(Color.decode("#DFF6F0"));

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
			
		} else if (e.getSource() == btn_create_confirm) {	//계좌 생성 확인
			int flag = 0;
			for (int i = 0; i < info.size(); i++) {
				if ((T_name.getText().equals(info.get(i).name))) {
				flag = 1;
				D_create_error = new JDialog();
				D_create_error.setTitle("중복 에러");
				D_create_error.setLayout(null);
				D_create_error.getContentPane().setForeground(Color.decode("#BAE8E8"));
				D_create_error.getContentPane().setBackground(Color.decode("#BAE8E8"));
				D_create_error.setSize(280,150);
				
				JLabel L_create_error1 = new JLabel("동일한 이름의 사용자가 있습니다");
				L_create_error1.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
				L_create_error1.setHorizontalAlignment(JLabel.CENTER);
				L_create_error1.setBounds(0,0,270,25);

				JLabel L_create_error2 = new JLabel("생성하시겠습니까?");
				L_create_error2.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
				L_create_error2.setHorizontalAlignment(JLabel.CENTER);
				L_create_error2.setBounds(0,30,270,25);
				
				btn_create_error_confirm = new JButton("확인");
				btn_create_error_confirm.setBounds(65,65,60,25);
				btn_create_error_confirm.setBackground(Color.decode("#DFF6F0"));

				btn_create_error_cancel = new JButton("취소");
				btn_create_error_cancel.setBounds(135,65,60,25);
				btn_create_error_cancel.setBackground(Color.decode("#DFF6F0"));
				
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
			if(flag != 1) {
			F_create.dispose();
			create();
			}
		} else if (e.getSource() == btn_create_cancel) { //계좌 생성 취소
			F_create.dispose();
		} else if (e.getSource() == btn_exit) { //나가기
			exit();
		} else if(e.getSource() == btn_create_error_confirm){ //계좌 중복 생성 확인
			D_create_error.dispose();
			F_create.dispose();
			create();
		} else if(e.getSource() == btn_create_error_cancel) { //계좌 중복 생성 취소
			D_create_error.dispose();
		}else if (e.getSource() == btn_login_confirm) {
			F_login.setVisible(false);
			while (true) {
				if (T_account.getText().equals(info.get(cnt).account)
						&& T_password.getText().equals(info.get(cnt).password)) {
					F_login.setVisible(false);
					myindex = cnt;

					F_choose = new JFrame("선택");
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

					
					F_choose.add(btn_deposit);
					F_choose.add(btn_withdrawal);
					F_choose.add(btn_remittance);
					F_choose.add(btn_user);
					F_choose.add(btn_record);

					btn_deposit.addActionListener(this);
					btn_withdrawal.addActionListener(this);
					btn_remittance.addActionListener(this);
					btn_user.addActionListener(this);
					btn_record.addActionListener(this);
					break;
				} else {
					cnt++;
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
					break;
				}
			}
		} else if (e.getSource() == btn_login_cancel) {
			F_login.dispose();
		} else if (e.getSource() == btn_login_error_confirm) {
			D_login_error.dispose();	
		} else if (e.getSource() == btn_deposit) {
			F_choose.setVisible(false);
			deposit();
		} else if (e.getSource() == btn_withdrawal) {
			F_choose.setVisible(false);
			withdrawal();
		} else if (e.getSource() == btn_remittance) {
			F_choose.setVisible(false);
			remittance();
		} else if (e.getSource() == btn_user) {
			F_choose.setVisible(false);
			user();
		} else if (e.getSource() == btn_record) {
			F_choose.setVisible(false);
			record();

		} else if (e.getSource() == btn_deposit_confirm) {
			F_deposit.dispose();
			info.get(cnt).money += Long.parseLong(T_money.getText());
			System.out.println(info.get(cnt).name + " 님의 잔액은 " + info.get(cnt).money + " 입니다.");
			try {
				FileWriter fw = new FileWriter(info_path);
				for (int i = 0; i < info.size(); i++) {
					fw.write(info.get(i).name + "\t" + info.get(i).account + "\t" + info.get(i).password + "\t"
							+ info.get(i).money + "\n");
				}
				fw.close();
				F_choose.setVisible(true);
			} catch (IOException E) {

			}

		} else if (e.getSource() == btn_deposit_cancel) {
			F_deposit.dispose();
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
				info.add(
						new Info(str.nextToken(), str.nextToken(), str.nextToken(), Integer.parseInt(str.nextToken())));
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
		Random ran = new Random();
		for (int i = 0; i < 10; i++) {
			account += ran.nextInt(9) + 1;
			account *= 10;
		}
		account /= 10;
		String account_S = Long.toString(account);
		try {
			FileWriter fw = new FileWriter(info_path, true);
			fw.write(T_name.getText() + "\t" + account_S + "\t" + T_Createpassword.getText() + "\t" + "0" + "\n"); // 이름	///계좌정보 //비밀번호 //잔고
			info.add(new Info(T_name.getText(), account_S, T_Createpassword.getText(), 0));
			fw.close();
		} catch (IOException E) {
			System.out.println("예외 발생");
		}
		record_path += "\\shubbing\\" + T_name.getText()+"_"+account_S+".txt";
		System.out.println(record_path);
		File recordfile = new File(record_path);
		try {
			recordfile.createNewFile();
		} catch (IOException E) {
			System.out.println("record 파일 만들기 오류");
		}
		record_path = System.getProperty("user.dir");
	}

	public void deposit() { // 입금
		F_deposit = new JFrame();
		JLabel L_money = new JLabel("금액 : ");

		btn_deposit_confirm = new JButton("확인");
		btn_deposit_cancel = new JButton("취소");
		T_money = new JTextField("", 20);
		L_money.setBounds(50, 50, 50, 20);
		L_money.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
		T_money.setBounds(100, 50, 100, 25);
		btn_deposit_confirm.setBounds(40, 100, 80, 30);
		btn_deposit_cancel.setBounds(160, 100, 80, 30);
		btn_deposit_confirm.addActionListener(this);
		btn_deposit_cancel.addActionListener(this);
		F_deposit.add(L_money);
		F_deposit.add(T_money);
		F_deposit.add(btn_deposit_confirm);
		F_deposit.add(btn_deposit_cancel);
		F_deposit.setSize(300, 200);
		F_deposit.setLayout(null);
		F_deposit.setVisible(true);
	}

	public void withdrawal() { // 출금
		/*
		 * try { System.out.print("출금하실 금액을 입력하세요 : "); int money = br.read(); while
		 * (money > info.get(myindex).money) { System.out.println("잔액이 부족합니다.");
		 * System.out.println("잔액 : " + info.get(myindex).money); money = br.read(); }
		 * info.get(myindex).money -= money; System.out.println("남은 잔액 : " +
		 * info.get(myindex).money); } catch (IOException e) {
		 * System.out.println("예외 발생"); }
		 */
	}

	public void remittance() { // 송금
		try {
			System.out.print("송금하실 계좌 번호를 입력하세요(\'-\'제외) : ");
			String account = br.readLine();
			System.out.println("현재 잔액 : " + info.get(myindex).money);
			System.out.println("송금하실 금액을 입력하세요(숫자만 입력) : ");
			int money = br.read();
			while (money > info.get(myindex).money) {
				System.out.println("잔액이 부족합니다.");
				System.out.println("잔액 : " + info.get(myindex).money);
				money = br.read();
			}
			for (int i = 0; i < info.size(); i++) {
				if (account.equals(info.get(i).account)) {
					System.out.println("예금주 명 : " + info.get(i).name);
					info.get(myindex).money -= money;
					info.get(i).money += money;
					System.out.println("현재 잔액 : " + info.get(myindex).money);
				}
			}
		} catch (IOException e) {
			System.out.println("예외 발생");
		}
	}

	public void user() { // 계좌정보
		System.out.println("이름 : " + info.get(myindex).name);
		System.out.println("계좌 번호 : " + info.get(myindex).account);
		System.out.println("비밀번호 : " + info.get(myindex).password);
		System.out.println("잔고 : " + info.get(myindex).money);
	}

	public void record() { // 입출금기록

	}

	public void exit() { // 나가기
		System.exit(0);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Bank bank = new Bank();
		new Initial_Setting();
	}
}
