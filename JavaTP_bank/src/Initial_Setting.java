
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Initial_Setting {
    Initial_Setting() throws IOException {
       String path = System.getProperty("user.dir");
        path += "\\shubbing";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        path += "\\cus_Info.txt";
        File user = new File(path);
        if (!user.exists()) {
            System.out.println("cus_Info.txt 파일이 생성되었습니다.");
            user.createNewFile();
        }
    }
}
