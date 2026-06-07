package com.polban.jtk.actions;

import com.polban.jtk.locators.QuizPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Action methods untuk halaman Quiz JTK Learn.
 * Semua selektor elemen direferensikan dari {@link QuizPageLocators}.
 */
public class QuizPage extends BasePage {

    public QuizPage(WebDriver driver) {
        super(driver);
    }

    // ── ACTIONS ────────────────────────────────────────────────────

    /** Klik menu sidebar berdasarkan nama kuis yang dilempar dari Gherkin */
    public void klikMenuKuisDiSidebar(String namaKuis) {
        WebElement menuKuis = wait.until(
                ExpectedConditions.elementToBeClickable(QuizPageLocators.menuKuis(namaKuis))
        );
        klik(menuKuis);
    }

    /** Verifikasi apakah form "Mulai Kuis" sudah muncul di layar utama */
    public boolean sudahMasukHalamanPersiapanKuis() {
        try {
            WebElement quizBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(QuizPageLocators.QUIZ_BOX)
            );
            return quizBox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /** Mengambil teks judul kuis untuk dicocokkan dengan ekspektasi */
    public String ambilJudulKuis() {
        WebElement elemenJudul = wait.until(
                ExpectedConditions.visibilityOfElementLocated(QuizPageLocators.QUIZ_TITLE)
        );
        return elemenJudul.getText().trim();
    }
}