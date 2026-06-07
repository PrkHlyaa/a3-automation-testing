package com.polban.jtk.stepdefinitions;

import com.polban.jtk.pages.DashboardPage;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class LogoutSteps {

    private DashboardPage dashboardPage;

    @When("pengguna mengklik menu profil")
    public void penggunaMengklikMenuProfil() {
        // Inisialisasi page menggunakan driver dari Hooks
        dashboardPage = new DashboardPage(Hooks.driver);
        dashboardPage.klikProfil();
    }

    @And("pengguna mengklik tombol Keluar")
    public void penggunaMengklikTombolKeluar() {
        dashboardPage.klikLogout();
    }

    @Then("pengguna diarahkan kembali ke halaman login utama")
    public void verifikasiHalamanLogin() {
        Assertions.assertTrue(
                dashboardPage.sudahDiHalamanLogin(),
                "Seharusnya diarahkan kembali ke halaman login utama setelah logout!"
        );
    }
}