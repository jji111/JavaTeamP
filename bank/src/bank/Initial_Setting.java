package bank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Initial_Setting {
	Initial_Setting() throws IOException {
		String path = System.getProperty("user.dir");
		path += "\\슈삥";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		path += "\\고객정보.txt";
		File user = new File(path);
		if (!user.exists()) {
			System.out.println("고객정보.txt 파일이 생성되었습니다.");
			user.createNewFile();
		}
		BufferedReader br = new BufferedReader(new FileReader(user));
		while (br.readLine() != null) {
			// 동적 객체
			//ArrayList<Info> list =  new ArrayList<>();			
			//list.add(new Info(br.readLine(), br.readLine(), br.readLine(), br.readLine(), br.readLine(), br.readLine(), Integer.parseInt(br.readLine())));		
		
		}
		br.readLine();
		System.out.println("파일을 찾지 못했습니다.");
	}
}
