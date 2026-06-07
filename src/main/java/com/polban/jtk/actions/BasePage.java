package com.polban.jtk.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * Base class untuk semua Page Object.
 * Menyediakan driver, wait, dan utility methods yang digunakan bersama.
 * Locator masing-masing halaman ada di package {@code com.polban.jtk.locators}.
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // Constructor: dipanggil saat membuat object LoginPage, dll
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Klik elemen — tunggu sampai bisa diklik dulu
    protected void klik(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    // Ketik teks ke dalam field
    protected void ketik(WebElement element, String teks) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(teks);
    }

    // Ambil teks dari elemen
    protected String ambilTeks(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText().trim();
    }

    // Cek apakah elemen terlihat
    protected boolean terlihat(WebElement element) {
        try { return element.isDisplayed(); }
        catch (Exception e) { return false; }
    }
}