package selenium_framework.main;

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
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class Keywords {
    static WebDriver driver;

    protected static void openBrowser(String browser) {
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("ignore-certificate-errors");
            driver = new ChromeDriver(options);
        }else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
            driver = new FirefoxDriver();
        }else if(browser.equalsIgnoreCase("safari")){
            WebDriverManager.getInstance(DriverManagerType.SAFARI).setup();
            driver = new SafariDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    protected static void gotToURL(String TC_test_data) {
        System.out.println("Open this url: " + TC_test_data);
        driver.navigate().to(TC_test_data);
    }

    protected static void closeBrowser() {
        System.out.println("Close the browser");
        driver.quit();
    }

    protected static void assertURL(String TC_test_data) {
        System.out.println("Check URL contains: " + TC_test_data);
        String url = driver.getCurrentUrl();
        if(url.trim().contains(TC_test_data.trim())){
            System.out.println("Passing: url matches");
        }else {
            System.out.println("Failing: URl does not match");
        }
    }

    protected static void disableCheckBox(String TC_selector_type, String TC_selector_value) {
        By locator;
        WebElement element;
        System.out.println("Disable a checkbox");
        locator = getLocator(TC_selector_type, TC_selector_value);
        element = driver.findElement(locator);
        if(element.isSelected()){
            element.click();
        }else {
            System.out.println("Passed: Element is already unselected");
        }
    }

    protected static void enableCheckBox(String TC_selector_type, String TC_selector_value) {
        By locator;
        WebElement element;
        System.out.println("Enable a checkbox");
        locator = getLocator(TC_selector_type, TC_selector_value);
        element = driver.findElement(locator);
        if(element.isSelected()){
            System.out.println("Passed: Element is already selected");
        }else {
            element.click();
        }
    }

    protected static void selectByIndex(String TC_selector_type, String TC_selector_value, String TC_test_data) {
        By locator;
        WebElement element;
        Select dropdown;
        System.out.println("Select from dropdown by index: " + TC_test_data);
        locator = getLocator(TC_selector_type, TC_selector_value);
        element = driver.findElement(locator);

        dropdown = new Select(element);
        dropdown.selectByIndex(Integer.parseInt(TC_test_data));
    }

    protected static void selectByVisibleText(String TC_selector_type, String TC_selector_value, String TC_test_data) {
        By locator;
        WebElement element;
        Select dropdown;
        System.out.println("Select from dropdown by visible text: " + TC_test_data);
        locator = getLocator(TC_selector_type, TC_selector_value);
        element = driver.findElement(locator);

        dropdown = new Select(element);
        dropdown.selectByVisibleText(TC_test_data);
    }

    protected static void checkNotVisible(String TC_selector_type, String TC_selector_value) {
        By locator;
        WebElement element;
        System.out.println("Check invisibility of an element with " + TC_selector_type + " and " + TC_selector_value);
        locator = getLocator(TC_selector_type, TC_selector_value);
        element = driver.findElement(locator);
        if(element.isDisplayed()){
            System.out.println("Failing: Element is displayed");
        }else {
            System.out.println("Passing: Element is NOT displayed");
        }
    }

    protected static void checkVisibility(String TC_selector_type, String TC_selector_value) {
        By locator;
        WebElement element;
        System.out.println("Check visibility of an element with " + TC_selector_type + " and " + TC_selector_value);
        locator = getLocator(TC_selector_type, TC_selector_value);
        element = driver.findElement(locator);

        if(element.isDisplayed()){
            System.out.println("Passing: Element is displayed");
        }else {
            System.out.println("Failing: Element is NOT displayed");
        }
    }

    protected static void refreshPage() {
        System.out.println("Refresh the page");
        driver.navigate().refresh();
    }

    protected static void assertTitle(String TC_test_data) {
        System.out.println("Check page title contains: " + TC_test_data);
        String title = driver.getTitle();
        if(title.trim().contains(TC_test_data.trim())){
            System.out.println("Title is matching");
        }else {
            System.out.println("Title is NOT matching");
        }
    }

    protected static void assertText(String TC_selector_type, String TC_selector_value, String TC_test_data) {
        By locator;
        System.out.println("Verify " + TC_test_data + "exist on an element with " + TC_selector_type + " and " + TC_selector_value);

        locator = getLocator(TC_selector_type, TC_selector_value);
        String text = driver.findElement(locator).getText();
        System.out.println(text);
        System.out.println(TC_test_data);

        if(text.trim().contains(TC_test_data.trim())){
            System.out.println("Text is matching");
        }else {
            System.out.println("Text is not matching");
        }
    }

    protected static void click(String TC_selector_type, String TC_selector_value) {
        By locator;
        System.out.println("Click on an element with " + TC_selector_type + " and " + TC_selector_value);

        locator = getLocator(TC_selector_type, TC_selector_value);
        driver.findElement(locator).click();
    }

    protected static void type(String selectorType, String selectorValue, String testData, int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet) {
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            driver.findElement(locator).sendKeys(testData);
            //Send pass result to Result column
            tcSheet.getRow(row).createCell(tcResultColumnIndex).setCellValue("Passed");
            tcSheet.getRow(row).createCell(tcCommentColumnIndex).setCellValue("");

        }catch (Exception e){
            Main.tc_failed = true;

            System.out.println("I found an error: " + e.getMessage());
            //Send fail result to Result column
            tcSheet.getRow(row).createCell(tcResultColumnIndex).setCellValue("Failed");
            //Send error message to Comment column
            tcSheet.getRow(row).createCell(tcCommentColumnIndex).setCellValue(e.getMessage());
            closeBrowser();


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
