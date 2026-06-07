package com.polban.jtk.actions;

import java.time.Duration;

import com.polban.jtk.locators.CourseDetailLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Action methods untuk halaman Course Detail & Quiz JTK Learn.
 * Semua selektor elemen direferensikan dari {@link CourseDetailLocators}.
 */
public class CourseDetail extends BasePage {

    private final CourseDetailLocators locators = new CourseDetailLocators();

    public CourseDetail(WebDriver driver) {
        super(driver);
        initLocators(locators);  // menghidupkan @FindBy pada CourseDetailLocators
    }

    // ── COURSE DETAIL ACTIONS ─────────────────────────────────────

    /** Klik card kursus berdasarkan nama yang ditampilkan */
    public void pilihCourse(String namaCourse) {
        // Locator dinamis — tetap menggunakan By karena @FindBy tidak mendukung parameter runtime
        WebElement card = driver.findElement(CourseDetailLocators.cardCourse(namaCourse));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", card);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", card);

        // Tunggu halaman course detail selesai dimuat sebelum lanjut ke langkah berikutnya
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("[pilihCourse] URL setelah klik card: " + driver.getCurrentUrl());
    }

    /** Klik tombol atau link berdasarkan teks, menangani SweetAlert2 jika masih terbuka */
    public void klikTombol(String namaTombol) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Tutup SweetAlert2 popup jika masih muncul (agar tidak menghalangi klik)
        try {
            WebElement swalOverlay = driver.findElement(By.cssSelector(".swal2-container"));
            if (swalOverlay.isDisplayed()) {
                System.out.println("[klikTombol] SweetAlert2 popup terdeteksi, menutupnya dulu...");
                // Klik tombol OK/Confirm di dalam popup jika ada
                try {
                    driver.findElement(By.cssSelector(".swal2-confirm")).click();
                } catch (Exception e) {
                    // Jika tidak ada tombol confirm, tekan Escape untuk menutup
                    driver.findElement(By.tagName("body"))
                            .sendKeys(org.openqa.selenium.Keys.ESCAPE);
                }
                // Tunggu popup benar-benar hilang
                wait.until(ExpectedConditions.invisibilityOfElementLocated(
                        By.cssSelector(".swal2-container")));
                System.out.println("[klikTombol] Popup sudah ditutup.");
            }
        } catch (Exception ignored) {
            // Tidak ada popup, lanjut
        }

        // Cari button atau link (a) yang mengandung teks namaTombol.
        // Locator dinamis — tetap menggunakan By karena @FindBy tidak mendukung parameter runtime
        WebElement tombol = wait.until(
                ExpectedConditions.presenceOfElementLocated(CourseDetailLocators.tombol(namaTombol))
        );

        System.out.println("[klikTombol] Ditemukan: <" + tombol.getTagName()
                + " class='" + tombol.getAttribute("class") + "'> teks: " + tombol.getText().trim());

        // Scroll ke elemen, lalu klik via JavaScript (menembus overlay jika masih ada)
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", tombol);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", tombol);

        // Tunggu sebentar untuk halaman bereaksi
        try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("[klikTombol] URL setelah klik: " + driver.getCurrentUrl());
    }

    /** Cek apakah browser sudah berada di halaman detail course */
    public boolean sudahMasukCourseDetail() {
        String url = driver.getCurrentUrl();
        return url.contains("learn-course") || url.contains("course");
    }

    // TC 2.3.2 / TC-11 — Navigasi langsung ke halaman kursus berdasarkan ID
    /** Buka halaman course berdasarkan ID, lalu tunggu sampai URL mengandung "learn-course" */
    public void bukaKursusDenganId(String idKursus) {
        String url = "https://polban-space.cloudias79.com/jtk-learn/learn-course/" + idKursus;
        System.out.println("[TC-11] Membuka URL kursus: " + url);
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("learn-course"));
        System.out.println("[TC-11] Halaman kursus terbuka: " + driver.getCurrentUrl());
    }

    // TC 2.3.2 / TC-11 — Verifikasi daftar kursus yang diikuti pelajar ditampilkan
    /** Verifikasi bahwa daftar kursus yang diikuti pelajar muncul di halaman */
    public boolean daftarKursusDitampilkan() {
        String url = driver.getCurrentUrl();
        System.out.println("[TC-11] Verifikasi URL setelah klik Kursus Saya: " + url);

        // Cek URL mengarah ke halaman kursus atau dashboard pelajar
        boolean urlSesuai = url.contains("kursus-saya")
                || url.contains("my-course")
                || url.contains("dashboard-pelajar")
                || url.contains("learn-course");

        // Cek ada elemen card kursus di halaman
        boolean adaKartuKursus = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement daftar = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//*[contains(@class,'custom-card') or contains(@class,'course-card') or contains(@class,'card')]")
                    )
            );
            adaKartuKursus = daftar.isDisplayed();
            System.out.println("[TC-11] Elemen daftar kursus ditemukan.");
        } catch (Exception e) {
            System.out.println("[TC-11] Elemen daftar kursus tidak ditemukan: " + e.getMessage());
        }

        return urlSesuai || adaKartuKursus;
    }

    // TC 07 Course Detail / Quiz + Validasi Akses Halaman Kuis dan Tampilan Judul

    /** Klik menu sidebar berdasarkan nama kuis yang dilempar dari Gherkin */
    public void klikMenuKuisDiSidebar(String namaKuis) {
        // XPath: Mencari tag <li> yang punya class 'learn-list-item' dan teksnya mengandung namaKuis
        WebElement menuKuis = wait.until(
                ExpectedConditions.elementToBeClickable(CourseDetailLocators.menuKuis(namaKuis))
        );
        klik(menuKuis);
    }

    /** Verifikasi apakah form "Mulai Kuis" sudah muncul di layar utama */
    public boolean sudahMasukHalamanPersiapanKuis() {
        try {
            // Mencari kotak utama kuis (yang memuat durasi, deskripsi, dll)
            wait.until(ExpectedConditions.visibilityOf(locators.quizBox));
            return locators.quizBox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /** Mengambil teks judul kuis untuk dicocokkan dengan ekspektasi */
    public String ambilJudulKuis() {
        // Mencari tag <b> di dalam <h3> di dalam .quiz-title
        wait.until(ExpectedConditions.visibilityOf(locators.quizTitle));
        return locators.quizTitle.getText().trim();
    }
}