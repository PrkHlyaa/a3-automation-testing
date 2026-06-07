package com.polban.jtk.stepdefinitions;

import com.polban.jtk.actions.CourseDetail;
import com.polban.jtk.actions.LoginPage;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class CourseSteps {

    private CourseDetail coursePage;

    @Given("pengguna sudah login ke JTK Learn")
    public void penggunaSudahLogin() {

        Hooks.driver.get(
                "https://polban-space.cloudias79.com/jtk-learn/"
        );

        LoginPage loginPage = new LoginPage(Hooks.driver);

        loginPage.masukkanEmail("Far@example.com");
        loginPage.masukkanPassword("Far");
        loginPage.klikLogin();

        coursePage = new CourseDetail(Hooks.driver);
    }

    @When("pengguna memilih course {string}")
    public void penggunaMemilihCourse(String namaCourse) {

        coursePage.pilihCourse(namaCourse);
    }

    @And("pengguna mengklik tombol {string}")
    public void penggunaMengklikTombol(String namaTombol) {
        coursePage.klikTombol(namaTombol);
    }

    @Then("pengguna berhasil masuk ke halaman detail course")
    public void penggunaBerhasilMasukKeCourseDetail() {

        Assertions.assertTrue(
                coursePage.sudahMasukCourseDetail(),
                "Seharusnya masuk ke halaman detail course"
        );
    }

    // TC 2.3.2 / TC-11 — Membuka kursus langsung berdasarkan ID (ID_Kursus=33)
    @When("pengguna membuka kursus dengan ID {string}")
    public void penggunaMembukKursusDenganId(String idKursus) {
        System.out.println("[TC-11] Membuka kursus dengan ID: " + idKursus);
        coursePage.bukaKursusDenganId(idKursus);
    }

    // TC 2.3.2 / TC-11 — Verifikasi daftar kursus yang diikuti pelajar muncul
    @Then("sistem menampilkan daftar kursus yang diikuti pelajar")
    public void sistemMenunjukkanDaftarKursus() {
        Assertions.assertTrue(
                coursePage.daftarKursusDitampilkan(),
                "[TC-11] GAGAL: Sistem seharusnya menampilkan daftar kursus yang diikuti pelajar!"
        );
        System.out.println("[TC-11] LULUS: Daftar kursus yang diikuti pelajar ditampilkan.");
    }

    // ── QUIZ STEPS ────────────────────────────────────────────────

    @When("pengguna mengklik menu kuis {string} pada sidebar navigasi")
    public void penggunaMengklikMenuKuis(String namaKuis) {
        coursePage.klikMenuKuisDiSidebar(namaKuis);
    }

    @Then("pengguna berhasil masuk ke halaman persiapan kuis")
    public void verifikasiHalamanPersiapan() {
        Assertions.assertTrue(
                coursePage.sudahMasukHalamanPersiapanKuis(),
                "Halaman persiapan kuis (quiz-guide-box) tidak ditemukan!"
        );
    }

    @And("judul kuis yang tampil di layar adalah {string}")
    public void verifikasiJudulKuis(String expectedJudul) {
        String actualJudul = coursePage.ambilJudulKuis();

        // Memastikan teks yang tampil ("Kuis 1") sama dengan ekspektasi
        // Kalau webnya kambuh nampilin ID (misal "103"), maka test case otomatis GAGAL di sini.
        Assertions.assertEquals(
                expectedJudul,
                actualJudul,
                "Judul kuis tidak sesuai! Expected: " + expectedJudul + ", Actual: " + actualJudul
        );
    }
}