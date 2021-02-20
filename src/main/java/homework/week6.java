package homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class week6 {
    public static void main(String[] args) throws InterruptedException {
        // Write a test case (in excel) and automate the following scenario -
        // User should be able to signup and logout from phptravels application.
        // https://www.phptravels.net/register
        // Use implicit wait and Explicit wait as necessary



        WebDriver driver;
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String email = Math.random()*100 + "@gmail.com";

        driver.navigate().to("https://www.phptravels.net/register");
        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("DDC");
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Test");
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys("1111111111");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("111111");
        driver.findElement(By.cssSelector("input[name='confirmpassword']")).sendKeys("111111");
        driver.findElement(By.cssSelector(".signupbtn")).click();

        WebDriverWait wait = new WebDriverWait(driver, 3);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'DDC Test')]")));
        driver.findElement(By.cssSelector(".dropdown-login #dropdownCurrency .bx-user")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'logout')]")));
        driver.findElement(By.xpath("//a[contains(@href,'logout')]")).click();

        Thread.sleep(1000);
        driver.quit();


    }

}
