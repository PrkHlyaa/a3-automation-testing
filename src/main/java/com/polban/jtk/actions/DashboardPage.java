package com.polban.jtk.actions;

import com.polban.jtk.locators.DashboardPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Action methods untuk halaman Dashboard JTK Learn.
 * Semua selektor elemen direferensikan dari {@link DashboardPageLocators}.
 */
public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // ── ACTIONS ────────────────────────────────────────────────────

    public void klikProfil() {
        klik(wait.until(ExpectedConditions.elementToBeClickable(DashboardPageLocators.MENU_PROFIL)));
    }

    public void klikLogout() {
        klik(wait.until(ExpectedConditions.elementToBeClickable(DashboardPageLocators.TOMBOL_KELUAR)));
    }

    public boolean sudahDiHalamanLogin() {
        try {
            // Tunggu maksimal 10 detik sampai field "Masukkan email" terlihat lagi
            wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardPageLocators.FIELD_EMAIL));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}