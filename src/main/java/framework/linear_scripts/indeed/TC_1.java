package framework.linear_scripts.indeed;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC_1 {
    public static void main(String[] args) {
        // Open a browser
        WebDriver driver;
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();

        // Navigate to https://www.indeed.com
        driver.navigate().to("https://www.indeed.com");

        // Enter "Software Engineer" into the What field
        driver.findElement(By.name("q")).sendKeys("Software Engineer");

        // Enter "New York" into Where field
        driver.findElement(By.name("l")).sendKeys("New York");

        // Click on Find Jobs button
        driver.findElement(By.cssSelector(".icl-WhatWhere-buttonWrapper button")).click();

        // Check if it takes you to jobs page
        String url = driver.getCurrentUrl();
        System.out.println(url);

        if(url.contains("/jobs")){
            System.out.println("We found the jobs page");
        }else {
            System.out.println("Did not find the jobs page");
        }

        // Close the browser
        driver.quit();


    }

}
