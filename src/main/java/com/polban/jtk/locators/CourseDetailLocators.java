package com.polban.jtk.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CourseDetailLocators {

    // ── TC-FR07-6 Test Data (Nobby) ───────────────────────────────
    /**
     * ID_COURSE untuk TC-FR07-6 (Nobby).
     * Kursus: "Testing 2" → ID 169
     * Materi target navigasi: "Apa itu Blackbox testing?"
     * Referensi: https://polban-space.cloudias79.com/jtk-learn/learn-course/169
     */
    public static final String COURSE_ID_NOBBY = "169";

    // ── Course Detail ─────────────────────────────────────────────

    /** Overlay SweetAlert2 yang muncul setelah aksi tertentu */
    @FindBy(css = ".swal2-container")
    public WebElement swalContainer;

    /** Tombol konfirmasi (OK/Confirm) di dalam popup SweetAlert2 */
    @FindBy(css = ".swal2-confirm")
    public WebElement swalConfirm;

    /** Tag body — digunakan untuk mengirim tombol Escape menutup popup */
    @FindBy(tagName = "body")
    public WebElement tagBody;

    /** Elemen card kursus (berbagai kemungkinan class) */
    @FindBy(xpath = "//*[contains(@class,'custom-card')" +
            " or contains(@class,'course-card')" +
            " or contains(@class,'card')]")
    public WebElement cardKursus;

    /** Teks yang muncul saat tidak ada kursus (fallback verifikasi halaman my-courses sudah terbuka) */
    @FindBy(css = ".tab-content, .course-list, [class*='course']")
    public WebElement halamanMyCourses;

    /** Daftar item materi/kuis pada sidebar course */
    @FindBy(xpath = "//li[contains(@class,'learn-list-item')]")
    public java.util.List<WebElement> sidebarItems;

    // ── Quiz ──────────────────────────────────────────────────────

    /** Kotak panduan kuis (memuat durasi, deskripsi, dan tombol mulai) */
    @FindBy(css = ".quiz-guide-box")
    public WebElement quizBox;

    /** Elemen judul kuis (h3 dengan class quiz-title) */
    @FindBy(css = ".quiz-title")
    public WebElement quizTitle;

    // ── TC-FR07-6 (Nobby) — Halaman Akses Materi ──────────────────

    /**
     * Kontainer konten materi yang ditampilkan setelah sidebar item materi diklik.
     * Selector mencakup area konten utama halaman learn-course (video player / konten materi).
     */
    @FindBy(css = ".learn-content, .course-content, .content-area, #content-main, [class*='content']")
    public WebElement kontenMateri;

    /**
     * Pesan yang ditampilkan saat kursus tidak memiliki materi maupun kuis.
     * Dipertahankan agar tidak ada compilation error pada method yang masih mereferensikannya.
     * Expected text: "There are no materials or quizzes for this course yet"
     */
    @FindBy(xpath =
            "//*[contains(normalize-space(text()),'There are no materials') " +
            "or contains(normalize-space(text()),'quizzes for this course yet')]"
    )
    public WebElement pesanKontenKosong;

    // ── Locator Dinamis (tetap pakai By) ──────────────────────────

    /**
     * Locator card kursus berdasarkan nama yang ditampilkan (dinamis).
     * Mencari ancestor div.custom-card dari heading {@code <h6>} yang teksnya tepat sama.
     * <p>Tidak bisa menggunakan @FindBy karena parameternya dinamis.</p>
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
     * <p>Tidak bisa menggunakan @FindBy karena parameternya dinamis.</p>
     *
     * @param namaTombol teks yang ditampilkan pada tombol atau link
     */
    public static By tombol(String namaTombol) {
        return By.xpath(
                "(//button[contains(normalize-space(.), '" + namaTombol + "')]" +
                " | //a[contains(normalize-space(.), '" + namaTombol + "')])[1]"
        );
    }

    /**
     * Locator item menu sidebar kuis berdasarkan nama (dinamis).
     * Mencari {@code <li>} yang teksnya mengandung namaKuis.
     * <p>Tidak bisa menggunakan @FindBy karena parameternya dinamis.</p>
     *
     * @param namaKuis nama kuis yang ditampilkan di sidebar
     */
    public static By menuKuis(String namaKuis) {
        return By.xpath(
                "//li[contains(normalize-space(.), '" + namaKuis + "')]"
        );
    }

    /**
     * Locator item menu sidebar materi berdasarkan nama (dinamis).
     * Mencari {@code <li>} atau {@code <a>} yang teksnya mengandung namaMateri
     * di dalam navigation bar / sidebar learn-course.
     * <p>Tidak bisa menggunakan @FindBy karena parameternya dinamis.</p>
     *
     * @param namaMateri nama materi yang ditampilkan di sidebar (misal: "Apa itu Blackbox testing?")
     */
    public static By menuMateri(String namaMateri) {
        return By.xpath(
                "(//li[contains(normalize-space(.), '" + namaMateri + "')]" +
                " | //a[contains(normalize-space(.), '" + namaMateri + "')])[1]"
        );
    }
}
