package com.polban.jtk.actions;

import java.time.Duration;

import com.polban.jtk.locators.LoginPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Action methods untuk halaman Login JTK Learn.
 * Semua selektor elemen direferensikan dari {@link LoginPageLocators}.
 */
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ── ACTIONS ────────────────────────────────────────────────────

    public void masukkanEmail(String email) {
        WebElement fieldEmail = wait.until(
                ExpectedConditions.visibilityOfElementLocated(LoginPageLocators.FIELD_EMAIL)
        );
        fieldEmail.clear();
        fieldEmail.sendKeys(email);
        System.out.println("Email field = " + fieldEmail.getAttribute("value"));
    }

    public void masukkanPassword(String password) {
        WebElement fieldPassword = wait.until(
                ExpectedConditions.visibilityOfElementLocated(LoginPageLocators.FIELD_PASSWORD)
        );
        fieldPassword.clear();
        fieldPassword.sendKeys(password);
        System.out.println("Password length = " + fieldPassword.getAttribute("value").length());
    }

    public void klikLogin() {
        System.out.println("Sebelum klik: " + driver.getCurrentUrl());

        WebElement tombolLogin = wait.until(
                ExpectedConditions.elementToBeClickable(LoginPageLocators.TOMBOL_LOGIN)
        );
        tombolLogin.click();

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
            wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageLocators.SWAL_POPUP));
            System.out.println("[TC 1.1.5] SweetAlert2 popup ditemukan!");
            return true;
        } catch (Exception e) {
            System.out.println("[TC 1.1.5] Popup tidak ditemukan: " + e.getMessage());
            return false;
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