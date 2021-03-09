package selenium_framework.main;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.*;
import java.util.Properties;

public class Main extends Base {
    public static boolean tc_failed = false;
    private static String testCaseFilePath;

    public static void main(String[] args) throws InterruptedException, IOException {
        JSONArray suite = getSuite();
        for (Object browser : getBrowsers()) {
            Keywords.openBrowser(browser.toString());
            runTestSuite(suite);
            Keywords.closeBrowser();
        }
    }

    private static void runTestSuite(JSONArray suite) {
        for (Object o : suite) {
            JSONObject testCaseObj = (JSONObject) o;
            testCaseFilePath = (String) testCaseObj.get("path");
            Boolean testCaseSkip = (Boolean) testCaseObj.get("skip");
            if(!testCaseSkip){
                runTestScenarios(testCaseFilePath.trim());
            }
        }
    }

    private static void runTestScenarios(String testCaseFilePath) {
        Properties prop = getProperties();

        String tsSheetName = prop.getProperty("tsSheetName").trim();
        int tsIdColumnIndex = Integer.parseInt(prop.getProperty("tsIdColumnIndex").trim());
        int tsSkipColumnIndex = Integer.parseInt(prop.getProperty("tsSkipColumnIndex").trim());

        // Read Excel file
        FileInputStream excelFileStream = null;
        try {
            excelFileStream = new FileInputStream(testCaseFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Reading Workbook
        XSSFWorkbook workbook = null;
        try {
            assert excelFileStream != null;
            workbook = new XSSFWorkbook(excelFileStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Getting Scenarios Sheet
        assert workbook != null;
        XSSFSheet tsSheet = workbook.getSheet(tsSheetName);

        // Reading info from Test Scenario sheet
        for (int i = 1; i < tsSheet.getLastRowNum() + 1; i++){
            tc_failed = false;

            Cell tsIdCell = tsSheet.getRow(i).getCell(tsIdColumnIndex);
            Cell tsSkipCell = tsSheet.getRow(i).getCell(tsSkipColumnIndex);

            if(tsIdCell == null || tsIdCell.toString().equalsIgnoreCase("")){
                break;
            }

            DataFormatter formatter = new DataFormatter();
            int tsId = Integer.parseInt(formatter.formatCellValue(tsIdCell));
            boolean tsSkip = Boolean.parseBoolean(formatter.formatCellValue(tsSkipCell));

            // !TS_skip = TS_skip is not true or TS_skip is false
            if(!tsSkip){
                runTestCases(tsId, workbook, prop);
            }
        }
    }

    private static void runTestCases(int tsId, XSSFWorkbook workbook, Properties prop) {
        String tcSheetNamePrefix = prop.getProperty("tcSheetNamePrefix").trim();

        // Getting TC sheet
        XSSFSheet tcSheet = workbook.getSheet(tcSheetNamePrefix + tsId);

        // Getting properties for Test Case sheet
        int tcKeywordColumnIndex = Integer.parseInt(prop.getProperty("tcKeywordColumnIndex").trim());
        int tcSelectorTypeColumnIndex = Integer.parseInt(prop.getProperty("tcSelectorTypeColumnIndex").trim());
        int tcSelectorValueColumnIndex = Integer.parseInt(prop.getProperty("tcSelectorValueColumnIndex").trim());
        int tcTestDataColumnIndex = Integer.parseInt(prop.getProperty("tcTestDataColumnIndex").trim());
        int tcResultColumnIndex = Integer.parseInt(prop.getProperty("tcResultColumnIndex").trim());
        int tcCommentColumnIndex = Integer.parseInt(prop.getProperty("tcCommentColumnIndex").trim());
        int tcSkipIndex = Integer.parseInt(prop.getProperty("tcSkipIndex").trim());

        DataFormatter formatter = new DataFormatter();
        for(int j = 1; j < tcSheet.getLastRowNum() + 1; j++){
            Cell tcKeyword = tcSheet.getRow(j).getCell(tcKeywordColumnIndex);

            if(tcKeyword == null || tcKeyword.toString().equalsIgnoreCase("") || tc_failed){
                break;
            }
            // Convert all cell values to string
            String keyword = formatter.formatCellValue(tcKeyword);
            String selectorType = formatter.formatCellValue(tcSheet.getRow(j).getCell(tcSelectorTypeColumnIndex));
            String selectorValue = formatter.formatCellValue(tcSheet.getRow(j).getCell(tcSelectorValueColumnIndex));
            String testData = formatter.formatCellValue(tcSheet.getRow(j).getCell(tcTestDataColumnIndex));
            boolean skip = Boolean.parseBoolean(formatter.formatCellValue(tcSheet.getRow(j).getCell(tcSkipIndex)));

            if(!skip){
                runTestSteps(keyword, selectorType, selectorValue, testData, j, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
            }
        }
        saveResultFile(workbook, testCaseFilePath);
    }

    private static void runTestSteps(String keyword, String selectorType, String selectorValue, String testData, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        switch (keyword.toLowerCase()){
            case "gotourl":
                Keywords.gotToURL(testData);
                break;
            case "type":
                Keywords.type(selectorType, selectorValue, testData, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "click":
                Keywords.click(selectorType, selectorValue);
                break;
            case "asserttext":
                Keywords.assertText(selectorType, selectorValue, testData);
                break;
            case "asserttitle":
                Keywords.assertTitle(testData);
                break;
            case "refreshpage":
                Keywords.refreshPage();
                break;
            case "checkvisibility":
                Keywords.checkVisibility(selectorType, selectorValue);
                break;
            case "checknotvisible":
                Keywords.checkNotVisible(selectorType, selectorValue);
                break;
            case "selectbyvisibletext":
                Keywords.selectByVisibleText(selectorType, selectorValue, testData);
                break;
            case "selectbyindex":
                Keywords.selectByIndex(selectorType, selectorValue, testData);
                break;
            case "enablecheckbox":
                Keywords.enableCheckBox(selectorType, selectorValue);
                break;
            case "disablecheckbox":
                Keywords.disableCheckBox(selectorType, selectorValue);
                break;
            case "asserturl":
                Keywords.assertURL(testData);
                break;
            case "closebrowser":
                Keywords.closeBrowser();
                break;
            default:
                System.out.println("Keyword named " + keyword + " is not recognized!");
                break;
        }
    }


}
