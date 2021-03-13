package selenium_basic.webdriver_wait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class explicit_wait {
    public static void main(String[] args) throws InterruptedException {
        // Open a browser
        WebDriver driver;
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();

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

        // Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));

        driver.findElement(By.id("logout_sidebar_link")).click();

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
