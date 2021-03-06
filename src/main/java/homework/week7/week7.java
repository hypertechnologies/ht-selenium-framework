package homework.week7;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class week7 {
    public static void main(String[] args) throws IOException {


        File file = new File("src/main/java/homework/week7/configs.properties");
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        Properties prop = new Properties();
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String test_cases_path = prop.getProperty("test_cases_path");
        int Homework_ID_Name_index = Integer.parseInt(prop.getProperty("Homework_ID_Name_index").trim());
        int Homework_Data1_index = Integer.parseInt(prop.getProperty("Homework_Data1_index").trim());
        int Homework_Data2_index = Integer.parseInt(prop.getProperty("Homework_Data2_index").trim());


        FileInputStream excelFileStream = null;
        try {
            excelFileStream = new FileInputStream(test_cases_path);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(excelFileStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet TS_sheet = workbook.getSheet("Homework");
        int TS_row_num = TS_sheet.getLastRowNum() + 1;

        for(int i = 1; i < TS_row_num; i++ ){
            int ID = (int) TS_sheet.getRow(i).getCell(Homework_ID_Name_index).getNumericCellValue();
            String Data1 =  TS_sheet.getRow(i).getCell(Homework_Data1_index).getStringCellValue();
            String Data2 =  TS_sheet.getRow(i).getCell(Homework_Data2_index).getStringCellValue();

            System.out.println("ID:"+ ID);
            System.out.println("Data1:"+ Data1 );
            System.out.println("Data2:"+ Data2 );

        }








    }
}