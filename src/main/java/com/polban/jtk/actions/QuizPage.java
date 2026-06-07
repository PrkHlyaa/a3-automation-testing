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

    private final QuizPageLocators locators = new QuizPageLocators();

    public QuizPage(WebDriver driver) {
        super(driver);
        initLocators(locators);  // menghidupkan @FindBy pada QuizPageLocators
    }

    // ── ACTIONS ────────────────────────────────────────────────────

    /** Klik menu sidebar berdasarkan nama kuis yang dilempar dari Gherkin */
    public void klikMenuKuisDiSidebar(String namaKuis) {
        // Locator dinamis — tetap menggunakan By karena @FindBy tidak mendukung parameter runtime
        WebElement menuKuis = wait.until(
                ExpectedConditions.elementToBeClickable(QuizPageLocators.menuKuis(namaKuis))
        );
        klik(menuKuis);
    }

    /** Verifikasi apakah form "Mulai Kuis" sudah muncul di layar utama */
    public boolean sudahMasukHalamanPersiapanKuis() {
        try {
            wait.until(ExpectedConditions.visibilityOf(locators.quizBox));
            return locators.quizBox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /** Mengambil teks judul kuis untuk dicocokkan dengan ekspektasi */
    public String ambilJudulKuis() {
        wait.until(ExpectedConditions.visibilityOf(locators.quizTitle));
        return locators.quizTitle.getText().trim();
    }
}