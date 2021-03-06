package framework.keyword_driven.main;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class main extends base{
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        keywords kw = new keywords();

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

                    if(TC_keyword.equals("")){
                        break;
                    }

                    By locator;
                    WebElement element;
                    Select dropdown;
                    //todo make TC_keyword lower case
                    switch (TC_keyword.toLowerCase()){
                        case "openbrowser":
                            kw.openBrowser(driver, TC_test_data);
                            break;
                        case "gotourl":
                            System.out.println("Open this url: " + TC_test_data);
                            driver.navigate().to(TC_test_data);
                            break;
                        case "type":
                            System.out.println("Enter " + TC_test_data + " into an element with " + TC_selector_type + " and " + TC_selector_value);

                            locator = getLocator(TC_selector_type, TC_selector_value);
                            driver.findElement(locator).sendKeys(TC_test_data);

                            break;
                        case "click":
                            System.out.println("Click on an element with " +  TC_selector_type + " and " + TC_selector_value);

                            locator = getLocator(TC_selector_type, TC_selector_value);
                            driver.findElement(locator).click();
                            break;
                        case "asserttext":
                            System.out.println("Verify " + TC_test_data + "exist on an element with " +  TC_selector_type + " and " + TC_selector_value);

                            locator = getLocator(TC_selector_type, TC_selector_value);
                            String text = driver.findElement(locator).getText();
                            System.out.println(text);
                            System.out.println(TC_test_data);

                            if(text.trim().contains(TC_test_data.trim())){
                                System.out.println("Text is matching");
                            }else {
                                System.out.println("Text is not matching");
                            }
                            break;

                        case "asserttitle":
                            System.out.println("Check page title contains: " + TC_test_data);
                            String title = driver.getTitle();
                            if(title.trim().contains(TC_test_data.trim())){
                                System.out.println("Title is matching");
                            }else {
                                System.out.println("Title is NOT matching");
                            }
                            break;
                        case "refreshpage":
                            System.out.println("Refresh the page");
                            driver.navigate().refresh();
                            break;
                        case "checkvisibility":
                            System.out.println("Check visibility of an element with " + TC_selector_type + " and " + TC_selector_value);
                            locator = getLocator(TC_selector_type, TC_selector_value);
                            element = driver.findElement(locator);

                            if(element.isDisplayed()){
                                System.out.println("Passing: Element is displayed");
                            }else {
                                System.out.println("Failing: Element is NOT displayed");
                            }
                            break;
                        case "checknotvisible":
                            System.out.println("Check invisibility of an element with " + TC_selector_type + " and " + TC_selector_value);
                            locator = getLocator(TC_selector_type, TC_selector_value);
                            element = driver.findElement(locator);
                            if(element.isDisplayed()){
                                System.out.println("Failing: Element is displayed");
                            }else {
                                System.out.println("Passing: Element is NOT displayed");
                            }
                            break;
                        case "selectbyvisibletext":
                            System.out.println("Select from dropdown by visible text: " + TC_test_data);
                            locator = getLocator(TC_selector_type, TC_selector_value);
                            element = driver.findElement(locator);

                            dropdown = new Select(element);
                            dropdown.selectByVisibleText(TC_test_data);
                            break;
                        case "selectbyindex":
                            System.out.println("Select from dropdown by index: " + TC_test_data);
                            locator = getLocator(TC_selector_type, TC_selector_value);
                            element = driver.findElement(locator);

                            dropdown = new Select(element);
                            dropdown.selectByIndex(Integer.parseInt(TC_test_data));
                            break;
                        case "enablecheckbox":
                            System.out.println("Enable a checkbox");
                            locator = getLocator(TC_selector_type, TC_selector_value);
                            element = driver.findElement(locator);
                            if(element.isSelected()){
                                System.out.println("Passed: Element is already selected");
                            }else {
                                element.click();
                            }
                            break;
                        case "disablecheckbox":
                            System.out.println("Disable a checkbox");
                            locator = getLocator(TC_selector_type, TC_selector_value);
                            element = driver.findElement(locator);
                            if(element.isSelected()){
                                element.click();
                            }else {
                                System.out.println("Passed: Element is already unselected");
                            }
                            break;
                        case "asserturl":
                            System.out.println("Check URL contains: " + TC_test_data);
                            String url = driver.getCurrentUrl();
                            if(url.trim().contains(TC_test_data.trim())){
                                System.out.println("Passing: url matches");
                            }else {
                                System.out.println("Failing: URl does not match");
                            }
                            break;
                        case "closebrowser":
                            System.out.println("Close the browser");
                            driver.quit();
                            break;
                        default:
                            System.out.println("Keyword named " + TC_keyword + " is not recognized!");
                            break;
                    }

                }

            }

        }

    }




    public static By getLocator (String selector_type, String selector_value){
        By by;
        switch (selector_type){
            case "xpath":
                by = By.xpath(selector_value);
                break;
            case "cssSelector":
                by = By.cssSelector(selector_value);
                break;
            case "id":
                by = By.id(selector_value);
                break;
            case "name":
                by = By.name(selector_value);
                break;
            case "linkText":
                by = By.linkText(selector_value);
                break;
            case "partialLinkText":
                by = By.partialLinkText(selector_value);
                break;
            default:
                by = null;
                System.out.println("Invalid selector type: " + selector_type);
                break;
        }
        return by;
    }

}
