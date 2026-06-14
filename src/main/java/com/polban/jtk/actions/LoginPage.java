package com.polban.jtk.actions;

import java.time.Duration;

import com.polban.jtk.locators.LoginPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Action methods untuk halaman Login JTK Learn.
 * Semua selektor elemen direferensikan dari {@link LoginPageLocators}.
 */
public class LoginPage extends BasePage {

    private final LoginPageLocators locators = new LoginPageLocators();

    public LoginPage(WebDriver driver) {
        super(driver);
        initLocators(locators);  // menghidupkan @FindBy pada LoginPageLocators
    }

    // ── ACTIONS ────────────────────────────────────────────────────

    public void masukkanEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(locators.fieldEmail));
        locators.fieldEmail.clear();
        locators.fieldEmail.sendKeys(email);
        System.out.println("Email field = " + locators.fieldEmail.getAttribute("value"));
    }

    public void masukkanPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(locators.fieldPassword));
        locators.fieldPassword.clear();
        locators.fieldPassword.sendKeys(password);
        System.out.println("Password length = " + locators.fieldPassword.getAttribute("value").length());
    }

    public void klikLogin() {
        System.out.println("Sebelum klik: " + driver.getCurrentUrl());

        wait.until(ExpectedConditions.elementToBeClickable(locators.tombolLogin));
        locators.tombolLogin.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("5 detik setelah klik: " + driver.getCurrentUrl());
    }

    // TC 1.1.5 — Cek apakah SweetAlert2 error popup muncul
    public boolean adaPesanError() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(locators.swalPopup));
            System.out.println("[TC 1.1.5] SweetAlert2 popup ditemukan!");
            return true;
        } catch (Exception e) {
            System.out.println("[TC 1.1.5] Popup tidak ditemukan: " + e.getMessage());
            return false;
        }
    }

    // TC 1.1.5 — Klik tombol OK untuk menutup SweetAlert2 popup error
    public void tutupPopupError() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(locators.swalTombolOk));
            locators.swalTombolOk.click();
            System.out.println("[TC 1.1.5] Tombol tutup popup diklik.");
            // Tunggu popup benar-benar hilang
            wait.until(ExpectedConditions.invisibilityOf(locators.swalPopup));
            System.out.println("[TC 1.1.5] Popup sudah tertutup, halaman Login terlihat kembali.");
        } catch (Exception e) {
            System.out.println("[TC 1.1.5] Gagal menutup popup: " + e.getMessage());
        }
    }

    public boolean sudahDiDashboard() {
        return driver.getCurrentUrl().equals(
                "https://polban-space.cloudias79.com/jtk-learn/dashboard-pelajar"
        );
    }

    // TC 1.1.5 / 1.5 — Verifikasi pengguna masih di halaman Login (gagal login)
    public boolean masihDiHalamanLogin() {
        String url = driver.getCurrentUrl();
        System.out.println("[TC 1.1.5] URL sekarang: " + url);
        return url.contains("jtk-learn/") && !url.contains("dashboard");
    }
}