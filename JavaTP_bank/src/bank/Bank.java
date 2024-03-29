package bank;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
	JFrame F_login, F_choose, F_deposit, F_withdrawal, F_remittance, F_user, F_record;
	JDialog D_error;
	JTextField T_account, T_money;
	JPasswordField T_password;
	JButton btn_login, btn_create, btn_exit, btn_login_confirm, btn_login_cancel, btn_error_confirm, btn_deposit,
			btn_withdrawal, btn_remittance, btn_user, btn_record, btn_deposit_confirm, btn_deposit_cancel,
			btn_withdrawal_confirm, btn_withdrawal_cancel, btn_remittance_confirm, btn_remittance_cancel,
			btn_user_confirm, btn_record_confirm;

	Bank() {
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

		this.setLayout(null);
		this.setSize(300, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_login) {
			login();
		} else if (e.getSource() == btn_create) {
			create();
		} else if (e.getSource() == btn_exit) {
			exit();
		} else if (e.getSource() == btn_login_confirm) {
			int cnt = 0;
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
					btn_deposit.setFont(new Font("맑은 고딕",Font.CENTER_BASELINE,15));
					btn_withdrawal = new JButton("출금");
					btn_withdrawal.setFont(new Font("맑은 고딕",Font.CENTER_BASELINE,15));
					btn_remittance = new JButton("송금");
					btn_remittance.setFont(new Font("맑은 고딕",Font.CENTER_BASELINE,15));
					btn_user = new JButton("정보");
					btn_user.setFont(new Font("맑은 고딕",Font.CENTER_BASELINE,15));
					btn_record = new JButton("입출금기록");
					btn_record.setFont(new Font("맑은 고딕",Font.CENTER_BASELINE,15));

					btn_deposit.setBackground(Color.decode("#DFF6F0"));
					btn_deposit.setFocusPainted(false);

					btn_withdrawal.setBackground(Color.decode("#DFF6F0"));
					btn_withdrawal.setFocusPainted(false);

					btn_remittance.setBackground(Color.decode("#DFF6F0"));
					btn_remittance.setFocusPainted(false);

					btn_user.setBackground(Color.decode("#DFF6F0"));
					btn_user.setFocusPainted(false);

					btn_record.setBackground(Color.decode("#DFF6F0"));
					btn_record.setFocusPainted(false);

					btn_deposit.setBounds(25, 150, 80, 30);
					btn_withdrawal.setBounds(115, 150, 80, 30);
					btn_remittance.setBounds(205, 150, 80, 30);
					btn_user.setBounds(295, 150, 80, 30);
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
					D_error = new JDialog();
					D_error.setTitle("에러");
					D_error.setSize(250, 100);
					D_error.setLayout(new FlowLayout());
					JLabel error_msg = new JLabel("계좌번호 또는 비밀번호가 틀렸습니다.");
					btn_error_confirm = new JButton("확인");
					D_error.add(error_msg);
					D_error.add(btn_error_confirm);
					btn_error_confirm.addActionListener(this);
					D_error.setVisible(true);
					break;
				}
			}
		} else if (e.getSource() == btn_login_cancel) {
			F_login.dispose();
		} else if (e.getSource() == btn_error_confirm) {
			D_error.dispose();
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
		}
	}

	public void first() {
		File file;
		String path = System.getProperty("user.dir");
		path += "\\shubbing\\cus_Info.txt";
		try {
			file = new File(path);
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
//		try {
//			System.out.println("계좌를 입력해주세요(\'-\'제외)");
//			String account = br.readLine();
//			System.out.println(account);
//			System.out.println("계좌 비밀번호를 입력해주세요");
//			String password = br.readLine();

//		} catch (IOException e) {
//			System.out.println("예외 발생");
//		}
		// return 0;
	}

	public void create() { // 계좌생성
		String path = System.getProperty("user.dir");
		path += "\\shubbing";
		path += "\\cus_Info.txt";
		Random ran = new Random();
		String name, account_S, password_S;
		long account = 0;
		int password;
		try {
			System.out.println("계좌를 생성합니다.\n생성할 계좌의 이름을 입력해주세요.");
			name = br.readLine();
			for (int i = 0; i < 10; i++) {
				account += ran.nextInt(9) + 1;
				account *= 10;
			}
			account /= 10;
			account_S = Long.toString(account);
			System.out.println("계좌번호는 : " + account / 10000000 + " - " + (account % 10000000) / 1000 + " - "
					+ account % 1000 + " 입니다.");
			System.out.println("비밀번호를 생성해주세요(숫자 4자리)");
			password_S = br.readLine();
			password = Integer.parseInt(password_S);
			FileWriter fw = new FileWriter(path);
			fw.write(name + "\t" + account_S + "\t" + password_S + "\t" + 0); // 이름 계좌정보 비밀번호 잔고

			info.add(new Info(name, account_S, password_S, 0));

			fw.close();

		} catch (Exception e) {
			System.out.println("예외 발생 ㅂㅇ");
			return;
		}
	}

	public void deposit() { // 입금
		F_deposit = new JFrame();
		F_deposit.setSize(300, 200);
		F_deposit.setLayout(null);
		F_deposit.setVisible(true);
		JLabel L_money = new JLabel("금액 : ");
		
		btn_deposit_confirm = new JButton("확인");
		btn_deposit_cancel = new JButton("취소");
		T_money = new JTextField("", 20);
		L_money.setBounds(25, 50, 50, 20);
		L_money.setFont(new Font("맑은 고딕",Font.CENTER_BASELINE,15 ));
		T_money.setBounds(50, 50, 100, 25);
		btn_deposit_confirm.setBounds(100, 300, 50, 25);
		btn_deposit_cancel.setBounds(170, 300, 50, 25);
		F_deposit.add(L_money);
		F_deposit.add(T_money);
		F_deposit.add(btn_deposit_confirm);
		F_deposit.add(btn_deposit_cancel);
//		try {
//			System.out.print("입금하실 금액을 입력하세요 : ");
//			int money = br.read();
//			info.get(myindex).money += money;
//		} catch (IOException e) {
//			System.out.println("예외 발생");
//		}
	}

	public void withdrawal() { // 출금
		try {
			System.out.print("출금하실 금액을 입력하세요 : ");
			int money = br.read();
			while (money > info.get(myindex).money) {
				System.out.println("잔액이 부족합니다.");
				System.out.println("잔액 : " + info.get(myindex).money);
				money = br.read();
			}
			info.get(myindex).money -= money;
			System.out.println("남은 잔액 : " + info.get(myindex).money);
		} catch (IOException e) {
			System.out.println("예외 발생");
		}
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
		bank.first();
		while (true) {

			System.out.println("1.계좌입력 2.계좌생성");
			int cmd;
			cmd = br.read();
			br.read();
//			if (cmd == '1') {
//				if (bank.login() == 1) {
//					while (true) {
//						System.out.println("1.입금 2.출금 3.송금 4.정보 5.입출금내역 (번호만 입력)");
//						br.skip(cmd);
//
//						cmd = br.read();
//						if (cmd == '1') {
//							bank.deposit();
//						} else if (cmd == '2') {
//							bank.withdrawal();
//						} else if (cmd == '3') {
//							bank.remittance();
//						} else if (cmd == '4') {
//							bank.user();
//						} else if (cmd == '5') {
//							bank.record();
//						} else if (cmd == '6') {
//							bank.exit();
//						}
//					}
//				} else if (bank.login() == 0) {
//					System.out.println("로그인 실패");
//				}
//
//			} else if (cmd == '2') {
//				bank.create();
//			}
		}
	}
}
