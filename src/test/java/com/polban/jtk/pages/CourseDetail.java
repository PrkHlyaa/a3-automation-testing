package com.polban.jtk.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class CourseDetail extends BasePage {

    public CourseDetail(WebDriver driver) {
        super(driver);
    }

    public void pilihCourse(String namaCourse) {

        WebElement card = driver.findElement(
                By.xpath(
                        "//h6[text()='" + namaCourse + "']/ancestor::div[contains(@class,'custom-card')]"
                )
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", card);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", card);
    }

    public void klikTombol(String namaTombol) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement tombol = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'" + namaTombol + "')]")
                )
        );

        tombol.click();
    }

    public boolean sudahMasukCourseDetail() {

        String url = driver.getCurrentUrl();

        return url.contains("learn-course")
                || url.contains("course");
    }
}