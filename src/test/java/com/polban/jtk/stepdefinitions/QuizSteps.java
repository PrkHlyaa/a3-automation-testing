package com.polban.jtk.stepdefinitions;

import com.polban.jtk.actions.QuizPage;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class QuizSteps {

    private QuizPage quizPage;

    @When("pengguna mengklik menu kuis {string} pada sidebar navigasi")
    public void penggunaMengklikMenuKuis(String namaKuis) {
        quizPage = new QuizPage(Hooks.driver);
        quizPage.klikMenuKuisDiSidebar(namaKuis);
    }

    @Then("pengguna berhasil masuk ke halaman persiapan kuis")
    public void verifikasiHalamanPersiapan() {
        Assertions.assertTrue(
                quizPage.sudahMasukHalamanPersiapanKuis(),
                "Halaman persiapan kuis (quiz-guide-box) tidak ditemukan!"
        );
    }

    @And("judul kuis yang tampil di layar adalah {string}")
    public void verifikasiJudulKuis(String expectedJudul) {
        String actualJudul = quizPage.ambilJudulKuis();

        // Memastikan teks yang tampil ("Kuis 1") sama dengan ekspektasi
        // Kalau webnya kambuh nampilin ID (misal "103"), maka test case otomatis GAGAL di sini.
        Assertions.assertEquals(
                expectedJudul,
                actualJudul,
                "Judul kuis tidak sesuai! Expected: " + expectedJudul + ", Actual: " + actualJudul
        );
    }
}