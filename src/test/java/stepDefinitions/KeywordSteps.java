package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Pattern;

public class KeywordSteps extends Base {
    static WebDriver driver;

    @Before()
    public static void openBrowser() {
        Boolean headless = Boolean.valueOf(System.getProperty("headless"));
        // Set default browser to chrome
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : "chrome";

        switch (browserName.trim().toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if(headless){
                    chromeOptions.addArguments("--headless");
                }
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if(headless){
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case "ieexplorer":
                WebDriverManager.iedriver().setup();
                WebDriverManager.operadriver().setup();
                driver = new InternetExplorerDriver();
                break;

            case "unit":
                driver = new HtmlUnitDriver();
                break;

            default:
                System.out.println("Browser name \"" + browserName + "\" is not recognized!");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(implicitWaitTimeout));
        driver.manage().window().maximize();
    }

    @After
    public static void closeBrowser() {
        if(driver != null){
            driver.quit();
        }
    }

    @And("I navigate to the url {string}")
    public static void gotToURL(String url) {
        try {
            driver.navigate().to(url);
        }catch (Exception e){
            throw e;
        }
    }

    @And("I check URL contains {string}")
    public static void assertURL(String testData) {
        try {
            waitForUrlToContainText(testData);
        }catch (Exception e){
            throw e;
        }
    }

    @And("I disable a checkbox with an element with selector type {string} and selector value {string}")
    public static void disableCheckBox(String selectorType, String selectorValue) throws Exception {
        try {
            By locator;
            WebElement element;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            element = driver.findElement(locator);
            if(element.isSelected()){
                element.click();
            }

            //Double check and fail if it was not disabled
            if(element.isSelected()){
                throw new Exception("Element is expected to be disabled but it is found enabled!");
            }
        }catch (Exception e){
            throw e;
        }
    }

    @And("I enable a checkbox with an element with selector type {string} and selector value {string}")
    public static void enableCheckBox(String selectorType, String selectorValue) throws Exception {
        try {
            By locator;
            WebElement element;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            element = driver.findElement(locator);
            if(!element.isSelected()){
                element.click();
            }

            //Double check and fail if it was not enabled
            if(!element.isSelected()){
                throw new Exception("Element is expected to be enabled but it is found disabled!");
            }
        }catch (Exception e){
            throw e;
        }
    }

    @And("I select index {string} item from a dropdown with an element with selector type {string} and selector value {string}")
    public static void selectByIndex(String testData, String selectorType, String selectorValue) {
        try {
            By locator;
            WebElement element;
            Select dropdown;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            element = driver.findElement(locator);

            dropdown = new Select(element);
            dropdown.selectByIndex(Integer.parseInt(testData));
        }catch (Exception e){
            throw e;
        }
    }

    @And("I select {string} from dropdown with an element with selector type {string} and selector value {string}")
    public static void selectByVisibleTextString (String testData, String selectorType, String selectorValue) {
        try {
            By locator;
            WebElement element;
            Select dropdown;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            element = driver.findElement(locator);

            dropdown = new Select(element);
            dropdown.selectByVisibleText(testData);
        }catch (Exception e){
            throw e;
        }
    }

    @And("I check presence of an element with selector type {string} and selector value {string}")
    public static void checkPresence(String selectorType, String selectorValue) {
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForPresence(locator);
        }catch (Exception e){
            throw e;
        }
    }

    @And("I check invisibility of an element with selector type {string} and selector value {string}")
    public static void checkNotVisible(String selectorType, String selectorValue) {
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForNotVisible(locator);
        }catch (Exception e){
            throw e;
        }
    }

    @And("I check visibility of an element with selector type {string} and selector value {string}")
    public static void checkVisibility(String selectorType, String selectorValue) {
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
        }catch (Exception e){
            throw e;
        }
    }

    @And("I refresh the page")
    public static void refreshPage() {
        try {
            driver.navigate().refresh();
        }catch (Exception e){
            throw e;
        }
    }

    @And("I check page title contains {string}")
    public static void assertTitle(String testData) {
        try {
            waitForTitleToContainText(testData);
        }catch (Exception e){
            throw e;
        }
    }

    @And("I verify text of on element with selector type {string} and selector value {string} is {string}")
    public static void assertText(String selectorType, String selectorValue, String testData) {
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            waitForTextToMatch(locator, testData);
        }catch (Exception e){
            throw e;
        }
    }

    @And("I verify attribute of on element with selector type {string} and selector value {string} is {string}")
    public static void assertAttribute(String selectorType, String selectorValue, String testData) {
        String attrName = testData.split("==")[0].trim();
        String attrValue = testData.split("==")[1].trim();

        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            waitForAttributeToMatch(locator, attrName, attrValue);
        }catch (Exception e){
            throw e;
        }
    }

    @And("I verify css of on element with selector type {string} and selector value {string} is {string}")
    public static void assertCssValue(String selectorType, String selectorValue, String testData) throws Exception {
        String cssName = testData.split("==")[0].trim();
        String expectedCssValue = testData.split("==")[1].trim();

        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);

            String actualCssValue = driver.findElement(locator).getCssValue(cssName);
            if(actualCssValue.contains(expectedCssValue)){
                // pass, nothing to do
            }else {
                throw new Exception("Expected Css value of \""+cssName +"\" to be : \"" + expectedCssValue + "\" but found: \"" + actualCssValue + "\"");
            }
        }catch (Exception e){
            throw e;
        }
    }

    @And("I click on an element with selector type {string} and selector value {string}")
    public static void click(String selectorType, String selectorValue) {
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            waitForClickable(locator);
            driver.findElement(locator).click();
        }catch (Exception e){
            throw e;
        }
    }

    @Then("I type {string} into an element with selector type {string} and selector value {string}")
    public static void type(String testData, String selectorType, String selectorValue) {
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            driver.findElement(locator).sendKeys(testData);
        }catch (Exception e){
            throw e;
        }
    }

    @Then("I upload a file located in {string} into an element with selector type {string} and selector value {string}")
    public static void uploadFile(String testData, String selectorType, String selectorValue) {
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForPresence(locator);
            driver.findElement(locator).sendKeys(System.getProperty("user.dir") + testData);
        }catch (Exception e){
            throw e;
        }
    }

    @Then("I mouse hover on an element with selector type {string} and selector value {string}")
    public static void mouseHover(String selectorType, String selectorValue) {
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            WebElement element = driver.findElement(locator);
            Actions action = new Actions(driver);
            (new Actions(driver)).moveToElement(element).build().perform();
        }catch (Exception e){
            throw e;
        }
    }

    @Then("i switch to an iFrame with ID {string}")
    public static void switchToiFrame( String testData) {
        try {
            waitForiFrameToBeAvailableAndSwitchToIt(testData);
        }catch (Exception e){
            throw e;
        }
    }

    @Then("I switch to default frame")
    public static void switchToDefaultFrame() {
        try {
            driver.switchTo().defaultContent();
        }catch (Exception e){
            throw e;
        }
    }

    @Then("I switch browser tab with index {string}")
    public static void switchTab( String testData) {
        try {
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(Integer.parseInt(testData)));
        }catch (Exception e){
            throw e;
        }
    }

    @Then("I hit browser forward button")
    public static void browserForward() {
        try {
            driver.navigate().forward();
        }catch (Exception e){
            throw e;
        }
    }

    @Then("I hit browser backward button")
    public static void browserBackward() {
        try {
            driver.navigate().back();
        }catch (Exception e){
            throw e;
        }
    }

    @Then("I drag and drop two elements with selector type {string} and selector value {string}")
    public static void dragAndDrop(String selectorType, String selectorValue) throws Exception {
        String errMsg = "To dragAndDrop there should be two comma separated selector_type and two comma separated selector_value";
        if(selectorType.split(",").length < 2 || selectorValue.split(",").length < 2){
            throw new Exception(errMsg);
        }else {
            String sourceSelectorType = selectorType.split(",")[0].trim();
            String sourceSelectorValue = selectorValue.split(",")[0].trim();

            String targetSelectorType = selectorType.split(",")[1].trim();
            String targetSelectorValue = selectorValue.split(",")[1].trim();
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
            }catch (Exception e){
                throw e;
            }
        }
    }

    @And("I wait for {string} milliseconds")
    public static void wait( String testData) throws InterruptedException {
        try {
            Thread.sleep(Long.parseLong(testData));
        }catch (Exception e){
            throw e;
        }
    }

    @Then("i get value from an element with selector type {string} and selector value {string} and save under {string} variable")
    public static void saveValue(String selectorType, String selectorValue, String testData) throws IOException {
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            WebElement element = driver.findElement(locator);
            String value = element.getText();

            String path = "src/main/resources/tmp/data.properties";
//            File f = new File(path);
//            f.createNewFile();

            Properties props = new Properties();
            //Populating the properties file
            props.put(testData, value);
            FileOutputStream outputStrem = new FileOutputStream(path);
            //Storing the properties file
            props.store(outputStrem, "Temporary data");
        }catch (Exception e){
            throw e;
        }
    }

    @Then("I verify the value of an element with selector type {string} and selector value {string} should contain {string}")
    public static void compareValueContains(String selectorType, String selectorValue, String testData) throws Exception {
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            WebElement element = driver.findElement(locator);
            String newValue = element.getText();

            Properties prop = getProperties("src/test/resources/tmp/data.properties");
            String oldValue = prop.getProperty(testData);

            if (newValue.contains(oldValue)) {
                // pass, nothing to do
            } else {
                throw new Exception("Expected \"" + newValue + "\" to contain \"" + oldValue + "\"");
            }
        }catch (Exception e){
            throw e;
        }
    }

    @Then("I verify the value of an element with selector type {string} and selector value {string} should EQUAL the value from {string}")
    public static void compareValueEquals(String selectorType, String selectorValue, String testData) throws Exception {
        try {
            By locator;
            locator = getLocator(selectorType, selectorValue);
            waitForVisible(locator);
            WebElement element = driver.findElement(locator);
            String newValue = element.getText();

            Properties prop = getProperties("src/test/resources/tmp/data.properties");
            String oldValue = prop.getProperty(testData);

            if (oldValue.equals(newValue)) {
                // pass, nothing to do
            } else {
                throw new Exception("Expected \"" + newValue + "\" to equal to \"" + oldValue + "\"");
            }
        }catch (Exception e){
            throw e;
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

    private static void waitForPresence(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(explicitWaitTimeout));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private static void waitForNotVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(explicitWaitTimeout));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private static void waitForVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(explicitWaitTimeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private static void waitForClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(explicitWaitTimeout));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private static void waitForUrlToContainText(String testData) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(explicitWaitTimeout));
        wait.until(ExpectedConditions.urlContains(testData));
    }

    private static void waitForTitleToContainText(String testData) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(explicitWaitTimeout));
        wait.until(ExpectedConditions.titleContains(testData));
    }

    private static void waitForTextToMatch(By locator, String testData) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(explicitWaitTimeout));
        wait.until(ExpectedConditions.textMatches(locator, Pattern.compile(testData.trim())));
    }

    private static void waitForAttributeToMatch(By locator, String attrName, String attrValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(explicitWaitTimeout));
        wait.until(ExpectedConditions.attributeContains(locator, attrName, attrValue));
    }

    private static void waitForiFrameToBeAvailableAndSwitchToIt(String testData) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(explicitWaitTimeout));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(testData));
    }

//    private static void sendFailedResult(int row, int tcResultColumnIndex, int tcCommentColumnIndex, XSSFSheet tcSheet, String errorMsg) {
//        System.out.println("\n\n >>>>> Below error found <<<<< \n\n" + errorMsg +"\n\n");
//
//        // Mark test case as failed
//        Main.tc_failed = true;
//
//        // Close browser
//        closeBrowser();
//
//        //Send fail result to Result column
//        tcSheet.getRow(row).createCell(tcResultColumnIndex).setCellValue("Failed");
//        //Send error message to Comment column
//        tcSheet.getRow(row).createCell(tcCommentColumnIndex).setCellValue(errorMsg);
//    }
//
//    private static void sendPassedResult(int row, int tcResultColumnIndex, XSSFSheet tcSheet) {
//        tcSheet.getRow(row).createCell(tcResultColumnIndex).setCellValue("Passed");
//    }
}