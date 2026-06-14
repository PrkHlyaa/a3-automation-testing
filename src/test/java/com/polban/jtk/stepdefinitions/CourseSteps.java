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

    // ── TC-FR07-6 (Nobby) — Validasi Navigasi Akses Materi ─────────


    /**
     * Given: Pelajar sudah login dan sudah enroll kursus (TC-FR07-6 Nobby).
     * Menggunakan session login dari Background (Far@example.com).
     * Step ini navigasi ke Kursus Saya dan memilih card kursus yang dimaksud.
     */
    @Given("pelajar sudah login dan sudah enroll kursus {string}")
    public void pelajarSudahLoginDanSudahEnrollKursus(String namaCourse) {
        System.out.println("[TC-FR07-6] Verifikasi enroll kursus: " + namaCourse);
        // Gunakan session login dari Background (Far@example.com)
        if (coursePage == null) {
            coursePage = new CourseDetail(Hooks.driver);
        }
        coursePage.loginDanVerifikasiEnrollKursus(namaCourse);
    }

    /**
     * And: Pelajar sudah berada di halaman course detail kursus dengan ID tertentu (TC-FR07-6).
     * Navigasi langsung ke /course/169 untuk membuka halaman overview kursus.
     */
    @Given("pelajar sudah berada di halaman course detail kursus dengan ID {string}")
    public void pelajarSudahBeradaDiHalamanCourseDetailDenganId(String courseId) {
        System.out.println("[TC-FR07-6] Step: pelajar sudah berada di halaman course detail ID=" + courseId);
        coursePage.navigasiKeHalamanCourseDetailById(courseId);
    }

    /**
     * When: Pelajar menekan tombol tertentu (TC-FR07-6).
     */
    @When("pelajar menekan tombol {string}")
    public void pelajarMenekanTombol(String namaTombol) {
        System.out.println("[TC-FR07-6] Pelajar menekan tombol: \"" + namaTombol + "\"");
        coursePage.klikTombol(namaTombol);
    }

    /**
     * And: Pelajar menekan navigasi materi di sidebar (TC-FR07-6 Nobby).
     * Mengklik item materi "Apa itu Blackbox testing?" pada sidebar navigation bar
     * di halaman learn-course/169.
     */
    @And("pelajar menekan navigasi materi {string} pada sidebar")
    public void pelajarMenekanNavigasiMateriPadaSidebar(String namaMateri) {
        System.out.println("[TC-FR07-6] Pelajar menekan navigasi materi: \"" + namaMateri + "\"");
        coursePage.klikNavigasiMateriDiSidebar(namaMateri);
    }

    /**
     * Then: Sistem menampilkan halaman Akses Materi yang sesuai (TC-FR07-6 Nobby).
     * Verifikasi URL masih di learn-course dan konten materi sudah dimuat.
     */
    @Then("sistem menampilkan halaman akses materi yang sesuai")
    public void sistemMenunjukkanHalamanAksesMateri() {
        System.out.println("[TC-FR07-6] Step: sistem menampilkan halaman Akses Materi");
        Assertions.assertTrue(
                coursePage.sudahMasukHalamanAksesMateri(),
                "[TC-FR07-6] GAGAL: Halaman Akses Materi tidak ditemukan!"
        );
        System.out.println("[TC-FR07-6] LULUS: Halaman Akses Materi berhasil ditampilkan.");
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