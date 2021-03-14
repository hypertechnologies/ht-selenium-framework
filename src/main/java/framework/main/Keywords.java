package framework.main;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class Keywords extends Base{
    static WebDriver driver;

    protected static void openBrowser(String browser) {
        System.out.println("Open " + browser + " browser");
        boolean ignoreCertificateError = (boolean) suiteConfigs.get("ignoreCertificateError");
        String screenSize = (String) suiteConfigs.get("screenSize");

        switch (browser.trim().toLowerCase()){
            case "chrome":
                WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if(ignoreCertificateError){
                    chromeOptions.addArguments("ignore-certificate-errors");
                }
                chromeOptions.addArguments("--window-size=" + screenSize);
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if(ignoreCertificateError){
                    firefoxOptions.addArguments("ignore-certificate-errors");
                }
                firefoxOptions.addArguments("--window-size=" + screenSize);
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "safari":
                WebDriverManager.getInstance(DriverManagerType.SAFARI).setup();
                driver = new SafariDriver();
                break;

            case "edge":
                WebDriverManager.getInstance(DriverManagerType.EDGE).setup();
                driver = new EdgeDriver();
                break;

            case "ieexplorer":
                WebDriverManager.getInstance(DriverManagerType.IEXPLORER).setup();
                driver = new InternetExplorerDriver();
                break;

            case "opera":
                WebDriverManager.getInstance(DriverManagerType.OPERA).setup();
                OperaOptions operaOptions = new OperaOptions();
                if(ignoreCertificateError){
                    operaOptions.addArguments("ignore-certificate-errors");
                }
                operaOptions.addArguments("--window-size=" + screenSize);
                driver = new OperaDriver(operaOptions);
                break;

            default:
                System.out.println("Browser name \"" + browser + "\" is not recognized!");
        }
        driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);
    }

    protected static void closeBrowser() {
        System.out.println("Close the browser");
        if(driver != null){
            driver.quit();
        }
    }

    protected static void gotToURL(String testData, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Navigate to \"" + testData + "\"");
        try {
            driver.navigate().to(testData);
            // Sending pass result to result column
            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            // Sending pass result to result column and error message to comment column
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void assertURL(String testData, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Check URL contains \"" + testData + "\"");
        try {
            waitForUrlToContainText(testData);
            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void disableCheckBox(String selectorType, String selectorValue, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Disable a checkbox with an element with  selector type \"" + selectorType + "\" and selector value \"" + selectorValue+"\"");
        try {
            By locator;
            WebElement element;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            element = driver.findElement(locator);
            if(element.isSelected()){
                element.click();
            }
            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void enableCheckBox(String selectorType, String selectorValue, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Enable a checkbox with an element with selector type \"" + selectorType + "\" and selector value \"" + selectorValue+"\"");
        try {
            By locator;
            WebElement element;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            element = driver.findElement(locator);
            if(!element.isSelected()){
                element.click();
            }
            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void selectByIndex(String selectorType, String selectorValue, String testData, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Select index \"" + testData + "\" item from a dropdown with an element with selector type \"" + selectorType + "\" and selector value \"" + selectorValue+"\"");
        try {
            By locator;
            WebElement element;
            Select dropdown;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            element = driver.findElement(locator);

            dropdown = new Select(element);
            dropdown.selectByIndex(Integer.parseInt(testData));

            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void selectByVisibleText(String selectorType, String selectorValue, String testData, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Select \"" + testData + "\" from dropdown with an element with selector type \"" + selectorType + "\" and selector value \"" + selectorValue+"\"");
        try {
            By locator;
            WebElement element;
            Select dropdown;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            element = driver.findElement(locator);

            dropdown = new Select(element);
            dropdown.selectByVisibleText(testData);

            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void checkNotVisible(String selectorType, String selectorValue, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Check invisibility of an element with selector type \"" + selectorType + "\" and selector value \"" + selectorValue+"\"");
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForNotVisible(locator);

            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void checkVisibility(String selectorType, String selectorValue, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Check visibility of an element with selector type \"" + selectorType + "\" and selector value \"" + selectorValue+"\"");
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);

            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void refreshPage(int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Refresh the page");
        try {
            driver.navigate().refresh();

            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void assertTitle(String testData, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Check page title contains \"" + testData + "\"");
        try {
            waitForTitleToContainText(testData);

            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void assertText(String selectorType, String selectorValue, String testData, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Verify \"" + testData + "\" exist on an element with selector type \"" + selectorType + "\" and selector value \"" + selectorValue+"\"");
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            waitForTextToMatch(locator, testData);

            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void click(String selectorType, String selectorValue, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Click on an element with selector type \"" + selectorType + "\" and selector value \"" + selectorValue+"\"");
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            waitForClickable(locator);
            driver.findElement(locator).click();
            
            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void type(String selectorType, String selectorValue, String testData, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Enter \"" + testData + "\" into an element with selector type \"" + selectorType + "\" and selector value \"" + selectorValue+"\"");
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            driver.findElement(locator).sendKeys(testData);

            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void switchToiFrame( String testData, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Switch to an iFrame with ID \"" + testData + "\"");
        try {
            waitForiFrameToBeAvailable(testData);
            driver.switchTo().frame(testData);
            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void switchToDefaultFrame( String testData, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Switch to default frame");
        try {
            driver.switchTo().defaultContent();
            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void switchTab( String testData, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Switch browser tab to index \"" + testData + "\"");
        try {
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(Integer.parseInt(testData)));
            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void browserForward(int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Hit browser forward button");
        try {
            driver.navigate().forward();
            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void browserBackward(int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Hit browser backward button");
        try {
            driver.navigate().back();
            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void dragAndDrop(String selectorType, String selectorValue, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Drag and drop two elements");
        String errMsg = "To dragAndDrop there should be two comma separated selector_type and two comma separated selector_value";
        if(selectorType.split(",").length < 2 || selectorValue.split(",").length < 2){
            System.out.println(errMsg);
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, errMsg);
        }else {
            String sourceSelectorType = selectorType.split(",")[0];
            String sourceSelectorValue = selectorValue.split(",")[0];

            String targetSelectorType = selectorType.split(",")[1];
            String targetSelectorValue = selectorValue.split(",")[1];
            try {
                By sourceLocator;
                By targetLocator;
                WebElement sourceElement;
                WebElement targetElement;
                sourceLocator = getLocator(sourceSelectorType, sourceSelectorValue);
                targetLocator = getLocator(targetSelectorType, targetSelectorValue);
                waitForVisible(sourceLocator);
                waitForVisible(targetLocator);
                sourceElement = driver.findElement(sourceLocator);
                targetElement = driver.findElement(targetLocator);

                (new Actions(driver)).dragAndDrop(sourceElement, targetElement).perform();

                sendPassedResult(row, tcResultColumnIndex, tcSheet);
            }catch (Exception e){
                sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
            }
        }
    }

    protected static void wait( String testData, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Hard wait \"" + testData + "\" milliseconds");
        try {
            Thread.sleep(Long.parseLong(testData));
            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    private static By getLocator (String selector_type, String selector_value){
        By by;
        switch (selector_type.trim().toLowerCase()){
            case "xpath":
                by = By.xpath(selector_value);
                break;
            case "cssselector":
                by = By.cssSelector(selector_value);
                break;
            case "id":
                by = By.id(selector_value);
                break;
            case "name":
                by = By.name(selector_value);
                break;
            case "classname":
                by = By.className(selector_value);
                break;
            case "tagname":
                by = By.tagName(selector_value);
                break;
            case "linktext":
                by = By.linkText(selector_value);
                break;
            case "partiallinktext":
                by = By.partialLinkText(selector_value);
                break;
            default:
                by = null;
                System.out.println("Invalid selector type provided: \"" + selector_type +
                        "\". Selector type must be one of xpath, CssSelector, id, name, ClassName, TagName, LinkText or PartialLinkText");
                break;
        }
        return by;
    }

    private static void waitForNotVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver,explicitWaitTimeout/1000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private static void waitForVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver,explicitWaitTimeout/1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private static void waitForClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver,explicitWaitTimeout/1000);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private static void waitForUrlToContainText(String testData) {
        WebDriverWait wait = new WebDriverWait(driver,explicitWaitTimeout/1000);
        wait.until(ExpectedConditions.urlContains(testData));
    }

    private static void waitForTitleToContainText(String testData) {
        WebDriverWait wait = new WebDriverWait(driver,explicitWaitTimeout/1000);
        wait.until(ExpectedConditions.titleContains(testData));
    }

    private static void waitForTextToMatch(By locator, String testData) {
        WebDriverWait wait = new WebDriverWait(driver,explicitWaitTimeout/1000);
        wait.until(ExpectedConditions.textMatches(locator, Pattern.compile(testData.trim())));
    }

    private static void waitForiFrameToBeAvailable(String testData) {
        WebDriverWait wait = new WebDriverWait(driver,explicitWaitTimeout/1000);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(testData));
    }
    
    private static void sendFailedResult(int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet, String errorMsg) {
        System.out.println("\n\n >>>>> Below error found <<<<< \n\n" + errorMsg +"\n\n");
        
        // Mark test case as failed
        Main.tc_failed = true;

        // Close browser
        closeBrowser();

        //Send fail result to Result column
        tcSheet.getRow(row).createCell(tcResultColumnIndex).setCellValue("Failed");
        //Send error message to Comment column
        tcSheet.getRow(row).createCell(tcCommentColumnIndex).setCellValue(errorMsg);
    }

    private static void sendPassedResult(int row, int tcResultColumnIndex, XSSFSheet tcSheet) {
        tcSheet.getRow(row).createCell(tcResultColumnIndex).setCellValue("Passed");
    }

}