package com.polban.jtk.pages;

import com.polban.jtk.locators.DashboardPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

    // Locators di-init via PageFactory di constructor
    private final DashboardPageLocators loc = new DashboardPageLocators();

    public DashboardPage(WebDriver driver) {
        super(driver);
        initLocators(loc);  // menghidupkan @FindBy pada DashboardPageLocators
    }

    public void klikProfil() {
        klik(loc.menuProfil);
    }

    public void klikLogout() {
        klik(loc.tombolKeluar);
    }

    public boolean sudahDiHalamanLogin() {
        try {
            // Tunggu maksimal 10 detik sampai field "Masukkan email" terlihat lagi
            wait.until(ExpectedConditions.visibilityOf(loc.fieldEmail));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}