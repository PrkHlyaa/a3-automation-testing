package com.polban.jtk.stepdefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    // Variabel driver dibagikan antar kelas lewat ThreadLocal
    public static WebDriver driver;

    @Before  // Dijalankan SEBELUM setiap Scenario
    public void bukaaBrowser() {
        WebDriverManager.chromedriver().setup(); // auto-unduh driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After   // Dijalankan SETELAH setiap Scenario
    public void tutupBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
