package com.polban.jtk.locators;

import org.openqa.selenium.By;

/**
 * Semua web locator untuk halaman Quiz JTK Learn.
 * Kelas ini HANYA berisi definisi selektor — tidak ada logika aksi.
 */
public class QuizPageLocators {

    /** Kotak panduan kuis (memuat durasi, deskripsi, dan tombol mulai) */
    public static final By QUIZ_BOX   = By.cssSelector(".quiz-guide-box");

    /** Elemen judul kuis di dalam heading */
    public static final By QUIZ_TITLE = By.cssSelector(".quiz-title h3 b");

    /**
     * Locator item menu sidebar kuis berdasarkan nama (dinamis).
     * Mencari {@code <li>} dengan class 'learn-list-item' yang teksnya mengandung namaKuis.
     *
     * @param namaKuis nama kuis yang ditampilkan di sidebar
     */
    public static By menuKuis(String namaKuis) {
        return By.xpath(
                "//li[contains(@class, 'learn-list-item')" +
                " and contains(., '" + namaKuis + "')]"
        );
    }
}
