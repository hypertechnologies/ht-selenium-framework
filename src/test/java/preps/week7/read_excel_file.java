package preps.week7;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class read_excel_file {
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

        FileInputStream excelInput = null;
        try {
            excelInput = new FileInputStream(prop.getProperty("test_case_file_path"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //FileOutputStream fout= new FileOutputStream(ResultFile);

        XSSFWorkbook workbook= null;
        try {
            workbook = new XSSFWorkbook(excelInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet TS_sheet= workbook.getSheet("TS-1");
        XSSFSheet TC_sheet= workbook.getSheet("TC-1");


        int TS_Rowcount = TS_sheet.getLastRowNum() - TS_sheet.getFirstRowNum();
        int TC_Rowcount = TC_sheet.getLastRowNum() - TC_sheet.getFirstRowNum();

        System.out.println(TS_Rowcount);
        System.out.println(TC_Rowcount);
        System.out.println(TS_sheet.getLastRowNum());



    }
}
