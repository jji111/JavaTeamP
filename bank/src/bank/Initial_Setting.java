package bank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Initial_Setting {
	Initial_Setting() throws IOException {
		String path = System.getProperty("user.dir");
		path += "\\����";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		path += "\\������.txt";
		File user = new File(path);
		if (!user.exists()) {
			System.out.println("������.txt ������ �����Ǿ����ϴ�.");
			user.createNewFile();
		}
		BufferedReader br = new BufferedReader(new FileReader(user));
		while (br.readLine() != null) {
			// ���� ��ü
			//ArrayList<Info> list =  new ArrayList<>();			
			//list.add(new Info(br.readLine(), br.readLine(), br.readLine(), br.readLine(), br.readLine(), br.readLine(), Integer.parseInt(br.readLine())));		
		
		}
		br.readLine();
		System.out.println("������ ã�� ���߽��ϴ�.");
	}
}
