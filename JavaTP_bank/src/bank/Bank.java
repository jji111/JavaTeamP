package bank;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Bank extends JFrame implements ActionListener{
	static ArrayList<Info> info = new ArrayList<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int myindex = 0;
	
	Container container;
	JButton btn_login,btn_create,btn_exit,btn_confirm,btn_cancel;
	Bank(){
		container = this.getContentPane();
		btn_login = new JButton("로그인");
		btn_create = new JButton("계좌 생성");
		btn_exit = new JButton("나가기");
		btn_confirm = new JButton("확인");
		btn_cancel = new JButton("취소");
		
		btn_login.setBounds(360,280,500,500);
		btn_create.setBounds(1060,280,500,500);
		btn_exit.setBounds(870,960,200,100);
		
		container.add(btn_login);
		container.add(btn_create);
		container.add(btn_exit);
		
		btn_login.addActionListener(this);
		btn_create.addActionListener(this);
		btn_exit.addActionListener(this);
		
		this.setLayout(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btn_login) {
				login();
			}else if(e.getSource() == btn_create) {
				create();
			}else if(e.getSource() == btn_exit) {
				exit();
			}else if(((JButton)e.getSource()).getText().equals("로그인")) {
				
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
				info.add(new Info(str.nextToken(), str.nextToken(), str.nextToken(), Integer.parseInt(str.nextToken())));
				tmp = f_br.readLine();
			}
		} catch (IOException e) {
			System.out.println("예외 발생");
		}
	}

	public void login() { // 로그인
		JFrame F_login = new JFrame("로그인");
		JPanel P_login = new JPanel();
		JLabel L_account = new JLabel("계좌 번호");
		L_account.setFont(new Font("굴림", Font.CENTER_BASELINE, 15));
		L_account.setHorizontalAlignment(JLabel.CENTER);
		JLabel L_password = new JLabel("비밀 번호");
		L_password.setFont(new Font("굴림",Font.CENTER_BASELINE,15));
		
		JTextField F_account = new JTextField("",10);
		JPasswordField F_password = new JPasswordField("",10);
		
		JButton confirm = new JButton("로그인");
		confirm.addActionListener(this);
		F_login.add(P_login);
		P_login.add(L_account);
		P_login.add(F_account);
		P_login.add(L_password);
		P_login.add(F_password);
		P_login.add(btn_confirm);
		P_login.setLayout(new FlowLayout());
		F_login.setSize(200,125);
		F_login.setVisible(true);
//		try {
//			System.out.println("계좌를 입력해주세요(\'-\'제외)");
//			String account = br.readLine();
//			System.out.println(account);
//			System.out.println("계좌 비밀번호를 입력해주세요");
//			String password = br.readLine();
//			int cnt = 0;
//			while (true) {
//				System.out.println("계좌 : " + info.get(cnt).account + "이름 : " + info.get(cnt).name + "비밀번호 : "
//						+ info.get(cnt).password + "잔고 : " + info.get(cnt).money);
//				if (account.equals(info.get(cnt).account) && password.equals(info.get(cnt).password)) {
//					System.out.println("로그인 성공!");
//					myindex = cnt;
					//return 1;
//				} else {
//					cnt++;
//				}
//			}
//		} catch (IOException e) {
//			System.out.println("예외 발생");
//		}
		//return 0;
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
		try {
			System.out.print("입금하실 금액을 입력하세요 : ");
			int money = br.read();
			info.get(myindex).money += money;
		}catch(IOException e) {
			System.out.println("예외 발생");
		}
	}

	public void withdrawal() { // 출금
		try {
		System.out.print("출금하실 금액을 입력하세요 : ");
		int money = br.read();
		while(money > info.get(myindex).money) {
			System.out.println("잔액이 부족합니다.");
			System.out.println("잔액 : " + info.get(myindex).money);
			money = br.read();
		}
		info.get(myindex).money -=money;
		System.out.println("남은 잔액 : " + info.get(myindex).money);
		}catch(IOException e) {
			System.out.println("예외 발생");
		}
	}

	public void remittance() { // 송금
		try {
		System.out.print("송금하실 계좌 번호를 입력하세요(\'-\'제외) : ");
		String account = br.readLine();
		System.out.println("현재 잔액 : "+ info.get(myindex).money);
		System.out.println("송금하실 금액을 입력하세요(숫자만 입력) : ");
		int money = br.read();
		while(money>info.get(myindex).money) {
			System.out.println("잔액이 부족합니다.");
			System.out.println("잔액 : " + info.get(myindex).money);
			money = br.read();
		}
		for(int i = 0; i < info.size();i++) {
			if(account.equals(info.get(i).account)) {
				System.out.println("예금주 명 : " + info.get(i).name);
				info.get(myindex).money -= money;
				info.get(i).money+=money;
				System.out.println("현재 잔액 : " + info.get(myindex).money);
			}
		}
		}catch(IOException e) {
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

	public  static void main(String[] args) throws IOException {
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
