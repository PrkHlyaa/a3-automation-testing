package com.polban.jtk.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    // ── Deklarasi elemen dengan @FindBy (Page Factory) ──
    // Sesuaikan selector ini dengan HTML halaman JTK Learn!

    @FindBy(css = "input[placeholder='Masukkan email']")
    private WebElement fieldEmail;

    @FindBy(css = "input[placeholder='Masukan kata sandi']")
    private WebElement fieldPassword;

    @FindBy(css = "button[type='submit']") // tombol submit
    private WebElement tombolLogin;

    // Error ditampilkan sebagai SweetAlert2 popup (class: swal2-popup)
    @FindBy(css = ".swal2-popup")
    private WebElement pesanErrorPopup;

    // ── Constructor ──
    public LoginPage(WebDriver driver) {
        super(driver);  // memanggil BasePage → PageFactory aktif!
    }

    // ── Metode aksi ──

    public void masukkanEmail(String email) {
        fieldEmail.clear();
        fieldEmail.sendKeys(email);

        System.out.println(
                "Email field = " +
                        fieldEmail.getAttribute("value")
        );
    }

    public void masukkanPassword(String password) {
        fieldPassword.clear();
        fieldPassword.sendKeys(password);

        System.out.println(
                "Password length = " +
                        fieldPassword.getAttribute("value").length()
        );
    }

    public void klikLogin() {
        System.out.println("Sebelum klik: " + driver.getCurrentUrl());

        tombolLogin.click();

        try {
            Thread.sleep(5000);
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
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(pesanErrorPopup));
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