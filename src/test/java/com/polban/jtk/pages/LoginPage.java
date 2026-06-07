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

    @FindBy(css = ".alert, .error, #loginerrormessage")
    private WebElement pesanError;

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

    public boolean adaPesanError() {
        return terlihat(pesanError);
    }

    public boolean sudahDiDashboard() {
        return driver.getCurrentUrl().equals(
                "https://polban-space.cloudias79.com/jtk-learn/dashboard-pelajar"
        );
    }
}