package homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class week5 {
    public static void main(String[] args) throws InterruptedException {
        // Open a browser
        WebDriver driver;
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.navigate().to("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.cssSelector(".btn_inventory")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.cssSelector(".checkout_button")).click();
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Smith");
        driver.findElement(By.id("postal-code")).sendKeys("10500");
        driver.findElement(By.cssSelector(".cart_button")).click();
        driver.findElement(By.cssSelector(".cart_button")).click();
        driver.findElement(By.cssSelector(".bm-burger-button")).click();

//        Thread.sleep(1000);

        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));

        driver.findElement(By.id("logout_sidebar_link")).click();

        // I want to click on an invalid element
        driver.findElement(By.id("invalid")).click();


        String url = driver.getCurrentUrl();
        if (url.contains("/index.html")) {
            System.out.println("Successfully logged out");
        } else {
            System.out.println("Could not log out");
        }

        // Close the browser
        Thread.sleep(3000);
        driver.quit();


    }

}
