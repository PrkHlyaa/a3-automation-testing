package com.polban.jtk.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Semua web locator untuk halaman Quiz JTK Learn.
 * Kelas ini HANYA berisi definisi selektor — tidak ada logika aksi.
 * Menggunakan @FindBy (Page Factory pattern) untuk deklarasi elemen statis,
 * dan metode By untuk locator dinamis.
 */
public class QuizPageLocators {

    /** Kotak panduan kuis (memuat durasi, deskripsi, dan tombol mulai) */
    @FindBy(css = ".quiz-guide-box")
    public WebElement quizBox;

    /** Elemen judul kuis di dalam heading */
    @FindBy(css = ".quiz-title h3 b")
    public WebElement quizTitle;

    /**
     * Locator item menu sidebar kuis berdasarkan nama (dinamis).
     * Mencari {@code <li>} yang teksnya mengandung namaKuis di sidebar navigasi.
     * <p>Tidak bisa menggunakan @FindBy karena parameternya dinamis.</p>
     *
     * @param namaKuis nama kuis yang ditampilkan di sidebar
     */
    public static By menuKuis(String namaKuis) {
        return By.xpath(
                "//li[contains(., '" + namaKuis + "')]"
        );
    }
}
