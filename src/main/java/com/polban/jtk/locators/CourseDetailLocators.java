package com.polban.jtk.locators;

import org.openqa.selenium.By;

/**
 * Semua web locator untuk halaman Course Detail JTK Learn.
 * Kelas ini HANYA berisi definisi selektor — tidak ada logika aksi.
 */
public class CourseDetailLocators {

    /** Overlay SweetAlert2 yang muncul setelah aksi tertentu */
    public static final By SWAL_CONTAINER = By.cssSelector(".swal2-container");

    /** Tombol konfirmasi (OK/Confirm) di dalam popup SweetAlert2 */
    public static final By SWAL_CONFIRM   = By.cssSelector(".swal2-confirm");

    /** Tag body — digunakan untuk mengirim tombol Escape menutup popup */
    public static final By TAG_BODY       = By.tagName("body");

    /** Elemen card kursus (berbagai kemungkinan class) */
    public static final By CARD_KURSUS    = By.xpath(
            "//*[contains(@class,'custom-card')" +
            " or contains(@class,'course-card')" +
            " or contains(@class,'card')]"
    );

    /** Teks yang muncul saat tidak ada kursus (fallback verifikasi halaman my-courses sudah terbuka) */
    public static final By HALAMAN_MY_COURSES = By.cssSelector(".tab-content, .course-list, [class*='course']");

    /**
     * Locator card kursus berdasarkan nama yang ditampilkan (dinamis).
     * Mencari ancestor div.custom-card dari heading {@code <h6>} yang teksnya tepat sama.
     *
     * @param namaCourse nama kursus yang ditampilkan di kartu
     */
    public static By cardCourse(String namaCourse) {
        return By.xpath(
                "//h6[text()='" + namaCourse + "']" +
                "/ancestor::div[contains(@class,'custom-card')]"
        );
    }

    /**
     * Locator tombol atau link berdasarkan teks yang ditampilkan (dinamis).
     * Mengambil elemen pertama yang cocok dari gabungan {@code <button>} dan {@code <a>}.
     *
     * @param namaTombol teks yang ditampilkan pada tombol atau link
     */
    public static By tombol(String namaTombol) {
        return By.xpath(
                "(//button[contains(normalize-space(.), '" + namaTombol + "')]" +
                " | //a[contains(normalize-space(.), '" + namaTombol + "')])[1]"
        );
    }
}
