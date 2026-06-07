package com.polban.jtk.stepdefinitions;

import com.polban.jtk.actions.LogoutPage;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class LogoutSteps {

    private LogoutPage logoutPage;

    @When("pengguna mengklik menu profil")
    public void penggunaMengklikMenuProfil() {
        // Inisialisasi page menggunakan driver dari Hooks
        logoutPage = new LogoutPage(Hooks.driver);
        logoutPage.klikProfil();
    }

    @And("pengguna mengklik tombol Keluar")
    public void penggunaMengklikTombolKeluar() {
        logoutPage.klikLogout();
    }

    @Then("pengguna diarahkan kembali ke halaman login utama")
    public void verifikasiHalamanLogin() {
        Assertions.assertTrue(
                logoutPage.sudahDiHalamanLogin(),
                "Seharusnya diarahkan kembali ke halaman login utama setelah logout!"
        );
    }
}