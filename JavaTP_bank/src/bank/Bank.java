package bank;


import java.io.*;
import java.util.Random;

import java.util.stream.LongStream;

public class Bank{
    public static void create() {
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
            fw.write(name+"\t"+account_S+"\t"+password_S+"\n");
            fw.close();
            br.close();
        }
        catch(Exception e){
            System.out.println("예외 발생 ㅂㅇ");
            return;
        }
    }

    public void deposit() {

    }

    public void withdrawal() {

    }

    public void remittance() {

    }

    public void user() {

    }

    public void record() {

    }

    public void exit() {

    }

    public static void main(String[] args) throws IOException {
        create();
        new Initial_Setting();
    }
}
