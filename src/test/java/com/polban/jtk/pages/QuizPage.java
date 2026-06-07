package com.polban.jtk.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class QuizPage extends BasePage {

    public QuizPage(WebDriver driver) {
        super(driver);
    }

    // 1. Klik menu sidebar berdasarkan nama kuis yang dilempar dari Gherkin
    public void klikMenuKuisDiSidebar(String namaKuis) {
        // XPath: Mencari tag <li> yang punya class 'learn-list-item' dan teksnya mengandung namaKuis
        String xpath = "//li[contains(@class, 'learn-list-item') and contains(., '" + namaKuis + "')]";

        WebElement menuKuis = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        klik(menuKuis);
    }

    // 2. Verifikasi apakah form "Mulai Kuis" sudah muncul di layar utama
    public boolean sudahMasukHalamanPersiapanKuis() {
        try {
            // Mencari kotak utama kuis (yang memuat durasi, deskripsi, dll)
            WebElement quizBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".quiz-guide-box")));
            return quizBox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // 3. Mengambil teks judul (yang dulu nge-bug jadi ID angka) untuk dicocokkan
    public String ambilJudulKuis() {
        // Mencari tag <b> di dalam <h3> di dalam .quiz-title
        WebElement elemenJudul = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".quiz-title h3 b")));
        return elemenJudul.getText().trim();
    }
}