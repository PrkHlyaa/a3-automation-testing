package com.polban.jtk.actions;

import com.polban.jtk.locators.LogoutPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Action methods untuk halaman Dashboard JTK Learn.
 * Semua selektor elemen direferensikan dari {@link LogoutPageLocators}.
 */
public class LogoutPage extends BasePage {

    private final LogoutPageLocators locators = new LogoutPageLocators();

    public LogoutPage(WebDriver driver) {
        super(driver);
        initLocators(locators);  // menghidupkan @FindBy pada LogoutPageLocators
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