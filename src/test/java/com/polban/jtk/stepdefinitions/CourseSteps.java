package com.polban.jtk.stepdefinitions;

import org.junit.jupiter.api.Assertions;

import com.polban.jtk.actions.CourseDetail;
import com.polban.jtk.actions.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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

    // ── TC-FR07-1 (Nobby) — Validasi Halaman Detail Kursus ────────


    /**
     * Given: Pelajar sudah login dan sudah enroll kursus (TC-FR07-1 Nobby).
     * Menggunakan session login dari Background (Far@example.com).
     * Step ini hanya memverifikasi enroll dengan navigasi ke Kursus Saya.
     */
    @Given("pelajar sudah login dan sudah enroll kursus {string}")
    public void pelajarSudahLoginDanSudahEnrollKursus(String namaCourse) {
        System.out.println("[TC-FR07-1] Verifikasi enroll kursus: " + namaCourse);
        // Gunakan session login dari Background (Far@example.com)
        if (coursePage == null) {
            coursePage = new CourseDetail(Hooks.driver);
        }
        coursePage.loginDanVerifikasiEnrollKursus(namaCourse);
    }

    /**
     * And: Pelajar sudah berada di halaman course detail kursus dengan ID tertentu.
     * Menggunakan ID statis sebagai pengganti DB query (DB sedang error).
     * ID_COURSE=84 merupakan data statis dari CourseDetailLocators.COURSE_ID_NOBBY.
     */
    @Given("pelajar sudah berada di halaman course detail kursus dengan ID {string}")
    public void pelajarSudahBeradaDiHalamanCourseDetailDenganId(String courseId) {
        System.out.println("[TC-FR07-1] Step: pelajar sudah berada di halaman course detail ID=" + courseId);
        // Static DB substitution: ID_COURSE=84 karena koneksi DB sedang error
        coursePage.navigasiKeHalamanCourseDetailById(courseId);
    }

    /**
     * When: Pelajar menekan tombol tertentu (TC-FR07-1).
     * Menggunakan action yang sama dengan "pengguna mengklik tombol",
     * disesuaikan dengan bahasa Gherkin pada scenario Nobby.
     */
    @When("pelajar menekan tombol {string}")
    public void pelajarMenekanTombol(String namaTombol) {
        System.out.println("[TC-FR07-1] Pelajar menekan tombol: \"" + namaTombol + "\"");
        coursePage.klikTombol(namaTombol);
    }


    /**
     * Then: Sistem menampilkan pesan tertentu di halaman.
     * Untuk TC-FR07-1: expected message = "Belum ada materi atau kuis yang dibuat."
     */
    @Then("sistem menampilkan pesan {string}")
    public void sistemMenunjukkanPesan(String expectedMessage) {
        System.out.println("[TC-FR07-1] Step: sistem menampilkan pesan \"" + expectedMessage + "\"");
        Assertions.assertTrue(
                coursePage.verifikasiPesanKontenKosong(expectedMessage),
                "[TC-FR07-1] GAGAL: Pesan \"" + expectedMessage + "\" tidak ditemukan di halaman!"
        );
        System.out.println("[TC-FR07-1] LULUS: Pesan empty-state ditemukan di halaman.");
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

    @Then("sidebar menampilkan minimal {int} materi atau {int} kuis")
    public void sidebar_menampilkan_minimal_materi_atau_kuis(Integer int1, Integer int2) {
        boolean isSidebarValid = coursePage.sidebarMenampilkanMateriAtauKuis(int1, int2);
        Assertions.assertTrue(
                isSidebarValid,
                String.format("Sidebar seharusnya menampilkan minimal %d materi atau %d kuis.", int1, int2)
        );
    }
}