package com.polban.jtk.stepdefinitions;

import com.polban.jtk.pages.LoginPage;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {

    private LoginPage loginPage;

    // "Given pengguna membuka halaman login JTK Learn"
    @Given("pengguna membuka halaman login JTK Learn")
    public void bukaHalamanLogin() {
        Hooks.driver.get(
                "https://polban-space.cloudias79.com/jtk-learn/"
        );
        loginPage = new LoginPage(Hooks.driver);
    }

    // "When pengguna memasukkan username "admin""
    @When("pengguna memasukkan email {string}")
    public void masukkanEmail(String email) {
        loginPage.masukkanEmail(email);
    }

    // "And pengguna memasukkan password "Admin@123""
    @And("pengguna memasukkan password {string}")
    public void masukkanPassword(String password) {
        loginPage.masukkanPassword(password);
    }

    // "And pengguna mengklik tombol Login"
    @And("pengguna mengklik tombol Login")
    public void klikTombolLogin() {
        loginPage.klikLogin();
    }

    // "Then pengguna berhasil masuk ke halaman dashboard"
    @Then("pengguna berhasil masuk ke halaman dashboard")
    public void verifikasiDashboard() {
        System.out.println("URL sekarang: " + Hooks.driver.getCurrentUrl());

        if (loginPage.adaPesanError()) {
            System.out.println("Pesan error ditemukan!");
        }

        Assertions.assertTrue(
                loginPage.sudahDiDashboard(),
                "Seharusnya sudah di halaman dashboard!"
        );
    }

    // "Then muncul pesan error login"
    @Then("muncul pesan error login")
    public void verifikasiPesanError() {
        Assertions.assertTrue(
                loginPage.adaPesanError(),
                "Seharusnya ada pesan error!"
        );
    }
}