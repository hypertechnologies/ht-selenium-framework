import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello HyperTech!");

        WebDriver driver;
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();

        driver.get("https://google.com");

        Thread.sleep(2000);

        driver.quit();
    }
}
