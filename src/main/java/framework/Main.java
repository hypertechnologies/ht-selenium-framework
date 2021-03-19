package framework;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.*;
import java.util.List;

public class Main extends Base {
    public static void main(String[] args) {
        setUp();
        JSONArray suite = getSuite();
        for (Object browser : getBrowsers()) {
            runTestSuite(suite,  browser.toString());
        }
    }

    private static void runTestSuite(JSONArray suite, String browser) {
        for (Object o : suite) {
            JSONObject testCaseObj = (JSONObject) o;
            String testCaseDir = (String) testCaseObj.get("directory");
            JSONArray ignoreTestCases = (JSONArray) testCaseObj.get("ignoreTestCases");
            Boolean testCaseDirSkip = (Boolean) testCaseObj.get("skip");
            List<File> testCasePaths = getAllTestCaseFilePaths(testCaseDir);

            if(!testCaseDirSkip){
                for (File path : testCasePaths) {
                    testCaseFilePath = path.toString();
                    String testCaseFileName = new File(testCaseFilePath).getName();
                    if(!ignoreTestCases.contains(testCaseFileName)){
                        System.out.println("\n>>>>> Executing Test Case File: " + testCaseFileName);
                        runTestScenarios(browser);
                    }
                }
            }
        }
    }

    private static void runTestScenarios(String browser) {
        // Getting indexes for test scenario sheet
        String tsSheetName = (String) getExcelIndexes().get("tsSheetName");
        int tsIdColumnIndex = (int) (long) getExcelIndexes().get("tsIdColumnIndex");
        int tsNameColumnIndex = (int) (long) getExcelIndexes().get("tsNameColumnIndex");
        int tsResultColumnIndex = (int) (long) getExcelIndexes().get("tsResultColumnIndex");
        int tsCommentColumnIndex = (int) (long) getExcelIndexes().get("tsCommentColumnIndex");
        int tsSkipColumnIndex = (int) (long) getExcelIndexes().get("tsSkipColumnIndex");

        XSSFWorkbook workbook = getWorkbook(testCaseFilePath);

        // Getting Scenarios Sheet
        assert workbook != null;
        XSSFSheet tsSheet = workbook.getSheet(tsSheetName);

        // Reading info from Test Scenario sheet
        for (int i = 1; i < tsSheet.getLastRowNum() + 1; i++){
            // Resetting tc_failed before another scenario run
            tc_failed = false;

            Cell tsIdCell = tsSheet.getRow(i).getCell(tsIdColumnIndex);
            Cell tsNameCell = tsSheet.getRow(i).getCell(tsNameColumnIndex);
            Cell tsSkipCell = tsSheet.getRow(i).getCell(tsSkipColumnIndex);

            // Skipping running a scenario if no tsId is defined
            if(tsIdCell == null || tsIdCell.toString().equalsIgnoreCase("")){
                continue;
            }

            DataFormatter formatter = new DataFormatter();
            int tsId = Integer.parseInt(formatter.formatCellValue(tsIdCell));
            String tsName = formatter.formatCellValue(tsNameCell);
            boolean tsSkip = Boolean.parseBoolean(formatter.formatCellValue(tsSkipCell));

            // !TS_skip = TS_skip is not true or TS_skip is false
            if(!tsSkip){
                System.out.println("\nScenario #"+tsId+": " + tsName +"\n");
                Keywords.openBrowser(browser);
                runTestCases(tsId, workbook, browser);
                Keywords.closeBrowser();
            }
            // Setting scenario result
            if (tc_failed){
                tsSheet.getRow(i).createCell(tsResultColumnIndex).setCellValue("Failed");
                tsSheet.getRow(i).createCell(tsCommentColumnIndex).setCellValue("At least one of the test cases has failed!");
            }else {
                tsSheet.getRow(i).createCell(tsResultColumnIndex).setCellValue("Passed");
            }
        }
        saveAndCloseResultFile(workbook, testCaseFilePath, sessionId, browser);
    }

    private static void runTestCases(int tsId, XSSFWorkbook workbook, String browser) {
        String tcSheetNamePrefix = (String) getExcelIndexes().get("tcSheetNamePrefix");

        // Getting TC sheet
        XSSFSheet tcSheet = workbook.getSheet(tcSheetNamePrefix + tsId);

        // Getting indexes for Test Case sheet
        int tcKeywordColumnIndex = (int) (long) getExcelIndexes().get("tcKeywordColumnIndex");
        int tcSelectorTypeColumnIndex = (int) (long) getExcelIndexes().get("tcSelectorTypeColumnIndex");
        int tcSelectorValueColumnIndex = (int) (long) getExcelIndexes().get("tcSelectorValueColumnIndex");
        int tcTestDataColumnIndex = (int) (long) getExcelIndexes().get("tcTestDataColumnIndex");
        int tcResultColumnIndex = (int) (long) getExcelIndexes().get("tcResultColumnIndex");
        int tcCommentColumnIndex = (int) (long) getExcelIndexes().get("tcCommentColumnIndex");
        int tcSkipIndex = (int) (long) getExcelIndexes().get("tcSkipIndex");

        DataFormatter formatter = new DataFormatter();
        for(int j = 1; j < tcSheet.getLastRowNum() + 1; j++){
            Cell tcKeyword = tcSheet.getRow(j).getCell(tcKeywordColumnIndex);

            // If any test step fails, exit the current scenario
            if(tc_failed){
                System.out.println("\nTest failed! Exiting... \n");
                break;
            }
            // If keyword is not defined continue to next keyword
            if(tcKeyword == null || tcKeyword.toString().equalsIgnoreCase("")){
                continue;
            }
            // Convert all cell values to string
            String keyword = formatter.formatCellValue(tcKeyword).trim();
            String selectorType = formatter.formatCellValue(tcSheet.getRow(j).getCell(tcSelectorTypeColumnIndex)).trim();
            String selectorValue = formatter.formatCellValue(tcSheet.getRow(j).getCell(tcSelectorValueColumnIndex)).trim();
            String testData = formatter.formatCellValue(tcSheet.getRow(j).getCell(tcTestDataColumnIndex)).trim();
            boolean skip = Boolean.parseBoolean(formatter.formatCellValue(tcSheet.getRow(j).getCell(tcSkipIndex)).trim());

            if(!skip){
                runTestSteps(keyword, selectorType, selectorValue, testData, j, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
            }
        }
    }

    private static void runTestSteps(String keyword, String selectorType, String selectorValue, String testData, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        switch (keyword.toLowerCase()){
            case "gotourl":
                Keywords.gotToURL(testData, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "type":
                Keywords.type(selectorType, selectorValue, testData, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "click":
                Keywords.click(selectorType, selectorValue, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "asserttext":
                Keywords.assertText(selectorType, selectorValue, testData, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "assertattribute":
                Keywords.assertAttribute(selectorType, selectorValue, testData, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "assertcssvalue":
                Keywords.assertCssValue(selectorType, selectorValue, testData, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "asserttitle":
                Keywords.assertTitle(testData, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "refreshpage":
                Keywords.refreshPage(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "checkvisibility":
                Keywords.checkVisibility(selectorType, selectorValue, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "checknotvisible":
                Keywords.checkNotVisible(selectorType, selectorValue, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "selectbyvisibletext":
                Keywords.selectByVisibleText(selectorType, selectorValue, testData, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "selectbyindex":
                Keywords.selectByIndex(selectorType, selectorValue, testData, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "enablecheckbox":
                Keywords.enableCheckBox(selectorType, selectorValue, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "disablecheckbox":
                Keywords.disableCheckBox(selectorType, selectorValue, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "asserturl":
                Keywords.assertURL(testData, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "switchtoiframe":
                Keywords.switchToiFrame(testData, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "switchtodefaultframe":
                Keywords.switchToDefaultFrame(testData, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "switchtab":
                Keywords.switchTab(testData, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "browserforward":
                Keywords.browserForward(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "browserbackward":
                Keywords.browserBackward(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "draganddrop":
                Keywords.dragAndDrop(selectorType, selectorValue, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "wait":
                Keywords.wait(testData, row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet);
                break;
            case "closebrowser":
                Keywords.closeBrowser();
                break;
            default:
                System.out.println("Keyword named \"" + keyword + "\" is not recognized!");
                break;
        }
    }


}
