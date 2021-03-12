package framework.main;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class Keywords extends Base{
    static WebDriver driver;

    protected static void openBrowser(String browser) {
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
            ChromeOptions options = new ChromeOptions();
            boolean ignoreCertificateError = (boolean) suiteConfigs.get("ignoreCertificateError");
            if(ignoreCertificateError){
                options.addArguments("ignore-certificate-errors");
            }
            driver = new ChromeDriver(options);
        }else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
            driver = new FirefoxDriver();
        }else if(browser.equalsIgnoreCase("safari")){
            WebDriverManager.getInstance(DriverManagerType.SAFARI).setup();
            driver = new SafariDriver();
        }
        int implicitWaitTimeout = (int) (long) suiteConfigs.get("implicitWaitTimeout");
        driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);
    }

    protected static void closeBrowser() {
        System.out.println("Close the browser");
        if(driver != null){
            driver.quit();
        }
    }

    protected static void gotToURL(String testData, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Navigate to: " + testData);
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
        System.out.println("Check URL contains: " + testData);
        try {
            String url = driver.getCurrentUrl();
            if(url.trim().contains(testData.trim())){
                sendPassedResult(row, tcResultColumnIndex, tcSheet);
            }else {
                sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, "Expected url to contain: " + testData + "\nBut found: " + url);
            }
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void disableCheckBox(String selectorType, String selectorValue, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Disable a checkbox with an element with " + selectorType + " and " + selectorValue);
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
        System.out.println("Enable a checkbox with an element with " + selectorType + " and " + selectorValue);
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
        System.out.println("Select from dropdown by index: " + testData);
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
        System.out.println("Select from dropdown by visible text: " + testData);
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
        System.out.println("Check invisibility of an element with " + selectorType + " and " + selectorValue);
        try {
            By locator;
            WebElement element;
            locator = getLocator(selectorType, selectorValue);
            waitForNotVisible(locator);
            element = driver.findElement(locator);
            if(element.isDisplayed()){
                sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, "Expected element to be NOT visible on the page but it's still visible!");
            }else {
                sendPassedResult(row, tcResultColumnIndex, tcSheet);
            }
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void checkVisibility(String selectorType, String selectorValue, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Check visibility of an element with " + selectorType + " and " + selectorValue);
        try {
            By locator;
            WebElement element;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            element = driver.findElement(locator);

            if(element.isDisplayed()){
                sendPassedResult(row, tcResultColumnIndex, tcSheet);
            }else {
                sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, "Expected element to be visible on the page but it's is not visible!");
            }
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
        System.out.println("Check page title contains: " + testData);
        try {
            String title = driver.getTitle();
            if(title.trim().contains(testData.trim())){
                sendPassedResult(row, tcResultColumnIndex, tcSheet);
            }else {
                sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, "Expected title: " + testData + "\nBut found: " + title);
            }
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void assertText(String selectorType, String selectorValue, String testData, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Verify " + testData + "exist on an element with " + selectorType + " and " + selectorValue);
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            String text = driver.findElement(locator).getText();
            if(text.trim().contains(testData.trim())){
                sendPassedResult(row, tcResultColumnIndex, tcSheet);
            }else {
                sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, "Expected text: " + testData + "\nBut found: " + text);
            }
        }catch (Exception e){
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    protected static void click(String selectorType, String selectorValue, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        System.out.println("Click on an element with " + selectorType + " and " + selectorValue);
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
        System.out.println("Enter " + testData + " into an element with " + selectorType + " and " + selectorValue);
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            driver.findElement(locator).sendKeys(testData);

            //Send pass result to Result column
            sendPassedResult(row, tcResultColumnIndex, tcSheet);
        }catch (Exception e){
            //Send fail result to Result column
            sendFailedResult(row, tcResultColumnIndex, tcCommentColumnIndex, tcSheet, e.getMessage());
        }
    }

    private static By getLocator (String selector_type, String selector_value){
        By by;
        switch (selector_type.toLowerCase()){
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
                System.out.println("Invalid selector type provided: " + selector_type +
                        ". Selector type must be one of xpath, CssSelector, id, name, ClassName, TagName, LinkText or PartialLinkText");
                break;
        }
        return by;
    }

    private static void waitForNotVisible(By locator) {
        int explicitWaitTimeout = (int) (long) suiteConfigs.get("explicitWaitTimeout");
        WebDriverWait wait = new WebDriverWait(driver,explicitWaitTimeout/1000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private static void waitForVisible(By locator) {
        int explicitWaitTimeout = (int) (long) suiteConfigs.get("explicitWaitTimeout");
        WebDriverWait wait = new WebDriverWait(driver,explicitWaitTimeout/1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private static void waitForClickable(By locator) {
        int explicitWaitTimeout = (int) (long) suiteConfigs.get("explicitWaitTimeout");
        WebDriverWait wait = new WebDriverWait(driver,explicitWaitTimeout/1000);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    private static void sendFailedResult(int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet, String errorMsg) {
        System.out.println("Error found: \n" + errorMsg);
        
        // Mark test case as failed
        Main.tc_failed = true;
        
        //Send fail result to Result column
        tcSheet.getRow(row).createCell(tcResultColumnIndex).setCellValue("Failed");
        //Send error message to Comment column
        tcSheet.getRow(row).createCell(tcCommentColumnIndex).setCellValue(errorMsg);
    }

    private static void sendPassedResult(int row, int tcResultColumnIndex, XSSFSheet tcSheet) {
        tcSheet.getRow(row).createCell(tcResultColumnIndex).setCellValue("Passed");
    }


}
