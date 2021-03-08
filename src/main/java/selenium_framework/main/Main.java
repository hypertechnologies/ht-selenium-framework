package selenium_framework.main;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.*;
import java.util.Properties;

public class Main extends Base {
    public static boolean tc_failed = false;

    public static void main(String[] args) throws InterruptedException, IOException {
        Properties prop = getProperties();

        String test_cases_path = prop.getProperty("test_cases_path").trim();
        String ts_sheet_name_prefix = prop.getProperty("ts_sheet_name_prefix").trim();
        String tc_sheet_name_prefix = prop.getProperty("tc_sheet_name_prefix").trim();
        int ts_id_column_index = Integer.parseInt(prop.getProperty("ts_id_column_index").trim());
        int ts_name_column_index = Integer.parseInt(prop.getProperty("ts_name_column_index").trim());
        int ts_result_column_index = Integer.parseInt(prop.getProperty("ts_result_column_index").trim());
        int ts_comment_column_index = Integer.parseInt(prop.getProperty("ts_comment_column_index").trim());
        int ts_skip_column_index = Integer.parseInt(prop.getProperty("ts_skip_column_index").trim());

        // Read Excel file
        FileInputStream excelFileStream = null;
        try {
            excelFileStream = new FileInputStream(test_cases_path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Reading Workbook
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(excelFileStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading Sheets
        XSSFSheet TS_sheet = workbook.getSheet(ts_sheet_name_prefix + "1");

        int TS_row_num = TS_sheet.getLastRowNum() + 1;

        System.out.println(TS_row_num);
        // Get Cell
        String cell = TS_sheet.getRow(1).getCell(1).getStringCellValue();

        System.out.println(cell);

        // Reading info from Test Scenario sheet
        for (int i = 1; i < TS_row_num; i++){
            tc_failed = false;

            System.out.println(i + " hello "+TS_row_num);
            int TSID = (int) TS_sheet.getRow(i).getCell(ts_id_column_index).getNumericCellValue();
            String TS_name = TS_sheet.getRow(i).getCell(ts_name_column_index).getStringCellValue();
            boolean TS_skip = TS_sheet.getRow(i).getCell(ts_skip_column_index).getBooleanCellValue();

            System.out.println("TSID is: " + TSID);
            System.out.println("TS_name is: " + TS_name);
            System.out.println("TS_skip is: " + TS_skip);

            if(!TS_skip){ // !TS_skip = TS_skip is not true or TS_skip is false
                System.out.println("Going to read test case sheet");

                // Getting TC sheet
                XSSFSheet TC_sheet = workbook.getSheet(tc_sheet_name_prefix + TSID);

                // Getting properties for Test Case sheet
                int tc_keyword_column_index = Integer.parseInt(prop.getProperty("tc_keyword_column_index").trim());
                int tc_selector_type_column_index = Integer.parseInt(prop.getProperty("tc_selector_type_column_index").trim());
                int tc_selector_value_column_index = Integer.parseInt(prop.getProperty("tc_selector_value_column_index").trim());
                int tc_test_data_column_index = Integer.parseInt(prop.getProperty("tc_test_data_column_index").trim());
                int tc_result_column_index = Integer.parseInt(prop.getProperty("tc_result_column_index").trim());
                int tc_comment_column_index = Integer.parseInt(prop.getProperty("tc_comment_column_index").trim());


                int TC_row_num = TC_sheet.getLastRowNum() + 1;

                DataFormatter formatter = new DataFormatter();
                for(int j = 1; j < TC_row_num; j++){

                    // convert the cell value to string

                    String TC_keyword = formatter.formatCellValue(TC_sheet.getRow(j).getCell(tc_keyword_column_index));
                    String TC_selector_type = formatter.formatCellValue(TC_sheet.getRow(j).getCell(tc_selector_type_column_index));
                    String TC_selector_value = formatter.formatCellValue(TC_sheet.getRow(j).getCell(tc_selector_value_column_index));
                    String TC_test_data = formatter.formatCellValue(TC_sheet.getRow(j).getCell(tc_test_data_column_index));

//                    System.out.println("Keyword is: " + TC_keyword);
//                    System.out.println("TC_test_data is: " + TC_test_data);

                    if(TC_keyword.equals("") || tc_failed){
                        break;
                    }

                    By locator;
                    WebElement element;
                    Select dropdown;
                    switch (TC_keyword.toLowerCase()){
                        case "openbrowser":
                            Keywords.openBrowser(TC_test_data);
                            break;
                        case "gotourl":
                            Keywords.gotToURL(TC_test_data);
                            break;
                        case "type":
                            Keywords.type(TC_selector_type, TC_selector_value, TC_test_data, tc_result_column_index, tc_comment_column_index, j, TC_sheet);
                            break;
                        case "click":
                            Keywords.click(TC_selector_type, TC_selector_value);
                            break;
                        case "asserttext":
                            Keywords.assertText(TC_selector_type, TC_selector_value, TC_test_data);
                            break;
                        case "asserttitle":
                            Keywords.assertTitle(TC_test_data);
                            break;
                        case "refreshpage":
                            Keywords.refreshPage();
                            break;
                        case "checkvisibility":
                            Keywords.checkVisibility(TC_selector_type, TC_selector_value);
                            break;
                        case "checknotvisible":
                            Keywords.checkNotVisible(TC_selector_type, TC_selector_value);
                            break;
                        case "selectbyvisibletext":
                            Keywords.selectByVisibleText(TC_selector_type, TC_selector_value, TC_test_data);
                            break;
                        case "selectbyindex":
                            Keywords.selectByIndex(TC_selector_type, TC_selector_value, TC_test_data);
                            break;
                        case "enablecheckbox":
                            Keywords.enableCheckBox(TC_selector_type, TC_selector_value);
                            break;
                        case "disablecheckbox":
                            Keywords.disableCheckBox(TC_selector_type, TC_selector_value);
                            break;
                        case "asserturl":
                            Keywords.assertURL(TC_test_data);
                            break;
                        case "closebrowser":
                            Keywords.closeBrowser();
                            break;
                        default:
                            System.out.println("Keyword named " + TC_keyword + " is not recognized!");
                            break;
                    }

                }

                File file = new File(test_cases_path);
                FileOutputStream fout= new FileOutputStream(file);
                workbook.write(fout);
                workbook.close();

            }

        }

    }





}
