package preps.week7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class read_prop_file {
    public static void main(String[] args) {
        File file = new File("src/test/java/preps/week7/config.properties");
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Properties prop = new Properties();

        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(prop.getProperty("test_case_file_path"));

    }
}
