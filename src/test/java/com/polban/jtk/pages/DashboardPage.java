package com.polban.jtk.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

    @FindBy(css = "#navbarNav > ul > li.nav-name.dropdown > a")
    private WebElement menuProfil;

    @FindBy(css = ".logout-icon, img[alt='Logout Icon']")
    private WebElement tombolKeluar;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void klikProfil() {
        klik(menuProfil);
    }

    public void klikLogout() {
        klik(tombolKeluar);
    }

    public boolean sudahDiHalamanLogin() {
        try {
            // Tunggu maksimal 10 detik sampai field "Masukkan email" terlihat lagi
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Masukkan email']")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}