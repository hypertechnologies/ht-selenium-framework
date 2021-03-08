package selenium_basic.exception_handling;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver;
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.navigate().to("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        try{
            driver.findElement(By.id("login-btn")).click();
        }catch (NoSuchElementException e){
            System.out.println("I found NoSuchElementException" + e.getMessage());
        }catch (ElementNotInteractableException eni){
            System.out.println("I found ElementNotInteractableException" + eni.getMessage());
        }catch (Exception ex){
            System.out.println("I found other kind of Exception: " + ex.getMessage());
        }finally {
            driver.quit();
        }

    }

}
