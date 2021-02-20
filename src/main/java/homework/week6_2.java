package homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class week6_2 {
    public static void main(String[] args) throws InterruptedException {
//        You can try these instead -
//        1. Login and logout of OrageHRM website
//        2. Login, then click on My Info, then click on Emergency Contact and then add an Emergency Contact.
//        3. Fill out and submit this Contact Us page

        WebDriver driver;
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 3);

        driver.navigate().to("https://opensource-demo.orangehrmlive.com/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#txtUsername")));
        driver.findElement(By.cssSelector("#txtUsername")).sendKeys("Admin");

        driver.findElement(By.cssSelector("#txtPassword")).sendKeys("admin123");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btnLogin")));
        driver.findElement(By.cssSelector("#btnLogin")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#menu_pim_viewMyDetails")));
        driver.findElement(By.cssSelector("#menu_pim_viewMyDetails")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#sidenav li:nth-of-type(3) a")));
        driver.findElement(By.cssSelector("#sidenav li:nth-of-type(3) a")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#btnAddContact")));
        driver.findElement(By.cssSelector("#btnAddContact")).click();
        driver.findElement(By.cssSelector("#emgcontacts_name")).sendKeys("Test Name");
        driver.findElement(By.cssSelector("#emgcontacts_relationship")).sendKeys("Spouse");
        driver.findElement(By.cssSelector("#emgcontacts_homePhone")).sendKeys("3475005000");
        driver.findElement(By.cssSelector("#btnSaveEContact")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#checkAll")));
        driver.findElement(By.cssSelector("#checkAll")).click();
        driver.findElement(By.cssSelector("#delContactsBtn")).click();

        driver.quit();
    }

}
