package com.polban.jtk.pages;

import com.polban.jtk.locators.LoginPageLocators;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Locators di-init via PageFactory di constructor
    private final LoginPageLocators loc = new LoginPageLocators();

    // ── Constructor ──
    public LoginPage(WebDriver driver) {
        super(driver);
        initLocators(loc);  // menghidupkan @FindBy pada LoginPageLocators
    }

    // ── Metode aksi ──

    public void masukkanEmail(String email) {
        loc.fieldEmail.clear();
        loc.fieldEmail.sendKeys(email);

        System.out.println(
                "Email field = " +
                        loc.fieldEmail.getAttribute("value")
        );
    }

    public void masukkanPassword(String password) {
        loc.fieldPassword.clear();
        loc.fieldPassword.sendKeys(password);

        System.out.println(
                "Password length = " +
                        loc.fieldPassword.getAttribute("value").length()
        );
    }

    public void klikLogin() {
        System.out.println("Sebelum klik: " + driver.getCurrentUrl());

        loc.tombolLogin.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("5 detik setelah klik: " + driver.getCurrentUrl());
    }

    // TC 1.1.5 — Cek apakah SweetAlert2 error popup muncul
    public boolean adaPesanError() {
        try {
            // Tunggu maksimal 5 detik supaya popup sempat muncul
            org.openqa.selenium.support.ui.WebDriverWait wait =
                new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(loc.swalPopup));
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