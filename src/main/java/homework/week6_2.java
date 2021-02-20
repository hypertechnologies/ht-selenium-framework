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

        driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
        driver.findElement(By.cssSelector("#txtUsername")).sendKeys("Admin");
        driver.findElement(By.cssSelector("#txtPassword")).sendKeys("admin123");
        driver.findElement(By.cssSelector("#btnLogin")).click();


        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#menu_pim_viewMyDetails")));


        driver.findElement(By.cssSelector("#menu_pim_viewMyDetails")).click();
        driver.findElement(By.cssSelector("#sidenav li:nth-of-type(3) a")).click();
        driver.findElement(By.cssSelector("#btnAddContact")).click();
        driver.findElement(By.cssSelector("#emgcontacts_name")).sendKeys("My Name");
        driver.findElement(By.cssSelector("#emgcontacts_relationship")).sendKeys("Spouse");
        driver.findElement(By.cssSelector("#emgcontacts_homePhone")).sendKeys("3475005000");
        driver.findElement(By.cssSelector("#btnSaveEContact")).click();

        driver.findElement(By.cssSelector("#checkAll")).click();
        driver.findElement(By.cssSelector("#delContactsBtn")).click();


        driver.quit();


    }

}
