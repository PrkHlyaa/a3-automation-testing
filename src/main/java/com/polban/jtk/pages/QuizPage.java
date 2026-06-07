package com.polban.jtk.pages;

import com.polban.jtk.locators.QuizPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class QuizPage extends BasePage {

    // Locators di-init via PageFactory di constructor
    private final QuizPageLocators loc = new QuizPageLocators();

    public QuizPage(WebDriver driver) {
        super(driver);
        initLocators(loc);  // menghidupkan @FindBy pada QuizPageLocators
    }

    // 1. Klik menu sidebar berdasarkan nama kuis yang dilempar dari Gherkin
    public void klikMenuKuisDiSidebar(String namaKuis) {
        // Locator dinamis — tetap menggunakan By karena @FindBy tidak mendukung parameter runtime
        WebElement menuKuis = wait.until(
                ExpectedConditions.elementToBeClickable(QuizPageLocators.menuKuis(namaKuis))
        );
        klik(menuKuis);
    }

    // 2. Verifikasi apakah form "Mulai Kuis" sudah muncul di layar utama
    public boolean sudahMasukHalamanPersiapanKuis() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loc.quizBox));
            return loc.quizBox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // 3. Mengambil teks judul (yang dulu nge-bug jadi ID angka) untuk dicocokkan
    public String ambilJudulKuis() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.visibilityOf(loc.quizTitle));
        return loc.quizTitle.getText().trim();
    }
}