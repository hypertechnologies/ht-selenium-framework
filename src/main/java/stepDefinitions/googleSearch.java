package stepDefinitions;


import framework.Keywords;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class googleSearch {
    WebDriver driver;
    @Given("Open the {string}")
    public void openThe(String arg0) {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
    }

    @And("Launch the url {string}")
    public void launchTheUrl(String arg0) {
        Keywords.gotToURL(arg0);
    }

    @And("Search for {string}")
    public void searchFor(String arg0) {

    }
}
