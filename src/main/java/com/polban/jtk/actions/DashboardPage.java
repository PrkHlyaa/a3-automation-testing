package com.polban.jtk.actions;

import com.polban.jtk.locators.DashboardPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Action methods untuk halaman Dashboard JTK Learn.
 * Semua selektor elemen direferensikan dari {@link DashboardPageLocators}.
 */
public class DashboardPage extends BasePage {

    private final DashboardPageLocators locators = new DashboardPageLocators();

    public DashboardPage(WebDriver driver) {
        super(driver);
        initLocators(locators);  // menghidupkan @FindBy pada DashboardPageLocators
    }

    // ── ACTIONS ────────────────────────────────────────────────────

    public void klikProfil() {
        klik(wait.until(ExpectedConditions.elementToBeClickable(locators.menuProfil)));
    }

    public void klikLogout() {
        klik(wait.until(ExpectedConditions.elementToBeClickable(locators.tombolKeluar)));
    }

    public boolean sudahDiHalamanLogin() {
        try {
            // Tunggu maksimal 10 detik sampai field "Masukkan email" terlihat lagi
            wait.until(ExpectedConditions.visibilityOf(locators.fieldEmail));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}