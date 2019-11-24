package bank;


import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.LongStream;

public class Bank{
	static Info info;
	public static int login() { //로그인
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        File file;
        String path = System.getProperty("user.dir");
        path +="\\shubbing\\cus_Info.txt";
        file = new File(path);
        try {
        	FileReader fr = new FileReader(file);
        	BufferedReader f_br = new BufferedReader(fr);
        	StringTokenizer str = new StringTokenizer(f_br.readLine(),"\t");
        	System.out.println("계좌를 입력해주세요(\'-\'제외)");
			String account = br.readLine();
			System.out.println("계좌 비밀번호를 입력해주세요");
			String password = br.readLine();
			
			info = new Info(str.nextToken(),str.nextToken(),str.nextToken(),Integer.parseInt(str.nextToken()));
			while(true) {
				System.out.println("계좌 : " + info.account+"이름 : " + info.name+"비밀번호 : " + info.password+"잔고 : " + info.money);
				if(account.equals(info.account) && password.equals(info.password)){
					System.out.println("로그인 성공!");
					return 1;
				}
				else {
					str = new StringTokenizer(f_br.readLine(),"\t");
					info = new Info(str.nextToken(),str.nextToken(),str.nextToken(),Integer.parseInt(str.nextToken()));
				}
			}
		} catch (IOException e) {
			System.out.println("예외 발생");
		}
		return 0;
	}
	
    public static void create() {	//계좌생성
        String path = System.getProperty("user.dir");
        path += "\\shubbing";
        path += "\\cus_Info.txt";
        Random ran= new Random();
        String name,account_S,password_S;
        long account=0;
        int password;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("계좌를 생성합니다.\n생성할 계좌의 이름을 입력해주세요.");
            name=br.readLine();
            ;
            for(int i=0;i<10;i++){
                account+=ran.nextInt(9)+1;
                account*=10;
            }
            account/=10;
            account_S=Long.toString(account);
            System.out.println("계좌번호는 : "+account/10000000+" - "+(account%10000000)/1000+" - "+account%1000+" 입니다.");
            System.out.println("비밀번호를 생성해주세요(숫자 4자리)");
            password_S=br.readLine();
            password=Integer.parseInt(password_S);
            FileWriter fw=new FileWriter(path);
            fw.write(account_S+"\t"+name+"\t"+password_S+"\t"+0);	//계좌정보 이름 비밀번호 잔고
            fw.close();
            br.close();
        }
        catch(Exception e){
            System.out.println("예외 발생 ㅂㅇ");
            return;
        }
    }

    public static void deposit() {		//입금
    	System.out.println("송금하실 계좌번호를 입력해주세요(\'-\'제외)");
    	//String account = 
    }

    public static void withdrawal() {	//출금
    	
    }

    public static void remittance() {	//송금
    	
    }

    public static void user() {		//계좌정보
    	
    	

    }

    public static void record() {		//입출금기록
    	

    }

    public static void exit() {		//나가기

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

    	new Initial_Setting();
    	while(true) {
    		System.out.println("1.계좌입력 2.계좌생성");
    		int cmd;
    		cmd = br.read();

    		if(cmd == '1') {
    			if(login() == 1) {
    				System.out.println("1.입금 2.출금 3.송금 4.정보 5.입출금내역 (번호만 입력)");
    				br.skip(cmd);
    				
    				cmd = br.read();
    				if(cmd == '1') {
    					deposit();
    				}else if(cmd == '2') {
    					withdrawal();
    				}else if(cmd == '3') {
    					remittance();
    				}else if(cmd == '4') {
    					user();
    				}else if(cmd == '5') {
    					record();
    				}else if(cmd == '6') {
    					exit();
    				}
    			}else if(login() == 0){
    				System.out.println("로그인 실패");
    			}
    			
    		}else if(cmd == '2') {
    			create();
    		}
    	}
    }
}
