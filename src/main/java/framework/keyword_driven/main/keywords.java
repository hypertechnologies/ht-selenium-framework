package framework.keyword_driven.main;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class keywords {
    protected static void openBrowser(WebDriver driver, String TC_test_data) {
        System.out.println("Open a browser: " + TC_test_data);

        if(TC_test_data.equalsIgnoreCase("chrome")){
            WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("ignore-certificate-errors");
            driver = new ChromeDriver(options);
        }else if(TC_test_data.equalsIgnoreCase("firefox")){
            WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
            driver = new FirefoxDriver();
        }else if(TC_test_data.equalsIgnoreCase("safari")){
            WebDriverManager.getInstance(DriverManagerType.SAFARI).setup();
            driver = new SafariDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }




}
