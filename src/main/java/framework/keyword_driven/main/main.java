package framework.keyword_driven.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class main {

    public static void main(String[] args) {

        // Read properties file
        File file = new File("src/main/java/framework/keyword_driven/config/configs.properties");

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

        System.out.println(prop.getProperty("key"));
        System.out.println(prop.getProperty("test_cases_path"));


    }

}
