package com.polban.jtk.actions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.polban.jtk.locators.CourseDetailLocators;

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Menyimpan URL sebelum klik untuk debugging
        String urlSebelumKlik = driver.getCurrentUrl();
        System.out.println("[pilihCourse] URL sebelum klik card: " + urlSebelumKlik);

        WebElement card = driver.findElement(CourseDetailLocators.cardCourse(namaCourse));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", card);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", card);

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    CourseDetailLocators.tombol("Lanjutkan Kursus")
            ));
            System.out.println("[pilihCourse] Halaman overview course siap (Tombol 'Lanjutkan Kursus' ditemukan).");
        } catch (Exception e) {
            System.out.println("[pilihCourse] URL sudah berubah, lanjut ke step berikutnya.");
        }

        System.out.println("[pilihCourse] URL setelah klik card: " + driver.getCurrentUrl());
    }

    /** Klik tombol atau link berdasarkan teks, menangani SweetAlert2 jika masih terbuka */
    public void klikTombol(String namaTombol) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        String urlSebelumKlik = driver.getCurrentUrl();
        System.out.println("[klikTombol] URL sebelum klik: " + urlSebelumKlik);

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

        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(urlSebelumKlik)));
            System.out.println("[klikTombol] URL berhasil berubah.");
        } catch (Exception e) {
            System.out.println("[klikTombol] URL tidak berubah dalam 15 detik.");
        }

        // Explicit wait: tunggu elemen halaman detail course muncul
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//li[contains(@class,'learn-list-item')]")),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector(".quiz-guide-box, .course-content"))
            ));
            System.out.println("[klikTombol] Halaman detail course sudah siap.");
        } catch (Exception e) {
            System.out.println("[klikTombol] Elemen halaman detail belum muncul: " + e.getMessage());
        }
        
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
        // XPath: Mencari tag <li> yang teksnya mengandung namaKuis
        WebElement menuKuis = wait.until(
                ExpectedConditions.elementToBeClickable(CourseDetailLocators.menuKuis(namaKuis))
        );
        // Scroll ke elemen dan klik via JavaScript untuk menembus kemungkinan overlay
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", menuKuis);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", menuKuis);

        // Tunggu konten kuis dimuat (AJAX load)
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    /** Verifikasi apakah form "Mulai Kuis" sudah muncul di layar utama */
    public boolean sudahMasukHalamanPersiapanKuis() {
        try {
            // Menggunakan By locator langsung (bukan @FindBy proxy) agar tidak stale setelah AJAX
            WebElement quizBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".quiz-guide-box"))
            );
            return quizBox.isDisplayed();
        } catch (Exception e) {
            System.out.println("[Quiz] quiz-guide-box tidak ditemukan: " + e.getMessage());
            return false;
        }
    }

    /** Mengambil teks judul kuis untuk dicocokkan dengan ekspektasi */
    public String ambilJudulKuis() {
        // Menggunakan By locator langsung (bukan @FindBy proxy) agar tidak stale setelah AJAX
        WebElement quizTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".quiz-title"))
        );
        return quizTitle.getText().trim();
    }

    // ── TC-FR07-1 (Nobby) — Validasi Halaman Detail Kursus ────────

    /**
     * Navigasi ke halaman "Kursus Saya" lalu klik card kursus berdasarkan nama.
     * Alur: klik navbar "Kursus Saya" → halaman /my-courses → klik card namaCourse.
     * Setelah ini browser berada di halaman course/ID (bukan learn-course).
     *
     * @param namaCourse nama kursus yang ditampilkan pada card (misal: "Testing")
     */
    public void loginDanVerifikasiEnrollKursus(String namaCourse) {
        System.out.println("[TC-FR07-1] Navigasi ke Kursus Saya, lalu klik kursus: " + namaCourse);

        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1. Klik link "Kursus Saya" di navbar
        String myCoursesUrl = "https://polban-space.cloudias79.com/jtk-learn/my-courses";
        driver.get(myCoursesUrl);
        localWait.until(ExpectedConditions.urlContains("my-courses"));
        System.out.println("[TC-FR07-1] Halaman Kursus Saya terbuka: " + driver.getCurrentUrl());

        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        // 2. Klik card kursus "Testing" (tab Dalam Progres)
        // Cari heading h6/h5/h4/h3 yang teksnya mengandung namaCourse, lalu klik card-nya
        By cardSelector = By.xpath(
                "//*[contains(normalize-space(text()),'" + namaCourse + "')]" +
                "/ancestor::*[contains(@class,'card') or contains(@class,'course')][1]"
        );
        try {
            WebElement card = localWait.until(
                    ExpectedConditions.elementToBeClickable(cardSelector)
            );
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", card);
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", card);
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("[TC-FR07-1] Card kursus '" + namaCourse + "' diklik. URL: " + driver.getCurrentUrl());
        } catch (Exception e) {
            System.out.println("[TC-FR07-1] Card '" + namaCourse + "' tidak ditemukan via ancestor, coba klik teks langsung: " + e.getMessage());
            // Fallback: klik elemen teks itu sendiri
            WebElement teks = localWait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[contains(normalize-space(text()),'" + namaCourse + "')]"))
            );
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", teks);
            try { Thread.sleep(2000); } catch (InterruptedException ex) { ex.printStackTrace(); }
            System.out.println("[TC-FR07-1] Fallback klik teks berhasil. URL: " + driver.getCurrentUrl());
        }
    }

    /**
     * Navigasi langsung ke halaman course detail menggunakan ID statis.
     * <p>
     * <b>Static DB Substitution (TC-FR07-1 Nobby):</b><br>
     * Normalnya ID kursus diambil dari database:
     *   {@code SELECT id FROM courses WHERE name = 'Testing'} → 84<br>
     * Karena koneksi DB sedang error, ID dikode keras menggunakan konstanta
     * {@link CourseDetailLocators#COURSE_ID_NOBBY}.<br>
     * Jika DB sudah normal kembali, ganti dengan query dinamis.
     * </p>
     *
     * @param courseId ID kursus (digunakan untuk validasi; URL dibangun dari konstanta statis)
     */
    public void navigasiKeHalamanCourseDetailById(String courseId) {
        // Static DB check: validasi ID sesuai dengan data statis yang diketahui
        if (!courseId.equals(CourseDetailLocators.COURSE_ID_NOBBY)) {
            System.out.println("[TC-FR07-1] PERINGATAN: ID kursus '" + courseId
                    + "' berbeda dari data statis ('" + CourseDetailLocators.COURSE_ID_NOBBY
                    + "'). Tetap menggunakan ID dari parameter.");
        }

        // Navigasi ke halaman course/ID (bukan learn-course/ID).
        // Halaman course/84 adalah halaman preview kursus yang memiliki tombol "Lanjutkan Kursus".
        // Setelah tombol itu diklik, browser berpindah ke learn-course/84.
        String url = "https://polban-space.cloudias79.com/jtk-learn/course/" + courseId;
        System.out.println("[TC-FR07-1] Navigasi ke halaman course detail: " + url);
        driver.get(url);

        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        localWait.until(ExpectedConditions.urlContains("/course/"));
        System.out.println("[TC-FR07-1] Halaman course/" + courseId + " terbuka: " + driver.getCurrentUrl());
    }

    /**
     * Verifikasi bahwa pesan empty-state "There are no materials or quizzes for this course yet"
     * ditampilkan di halaman learn-course setelah menekan tombol "Lanjutkan Kursus".
     * <p>
     * Pengecekan dilakukan secara statis: karena DB error, diasumsikan kursus
     * dengan {@link CourseDetailLocators#COURSE_ID_NOBBY} (ID=84) memang tidak
     * memiliki materi maupun kuis, sehingga pesan ini seharusnya muncul.
     * </p>
     *
     * @param expectedMessage pesan yang diharapkan muncul di halaman
     * @return {@code true} jika pesan tersebut ditemukan dan ditampilkan
     */
    public boolean verifikasiPesanKontenKosong(String expectedMessage) {
        System.out.println("[TC-FR07-1] Memverifikasi pesan empty-state: \"" + expectedMessage + "\"");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Coba cari via @FindBy locator terlebih dahulu
        try {
            wait.until(ExpectedConditions.visibilityOf(locators.pesanKontenKosong));
            String actualText = locators.pesanKontenKosong.getText().trim();
            System.out.println("[TC-FR07-1] Pesan ditemukan via @FindBy: \"" + actualText + "\"");
            return actualText.contains(expectedMessage) || expectedMessage.contains(actualText);
        } catch (Exception e) {
            System.out.println("[TC-FR07-1] @FindBy pesanKontenKosong tidak cocok, coba XPath alternatif...");
        }

        // Fallback: cari teks di seluruh halaman menggunakan XPath contains
        try {
            By pesanXpath = By.xpath(
                    "//*[contains(normalize-space(.), '" + expectedMessage + "')]"
            );
            WebElement pesanElement = wait.until(
                    ExpectedConditions.presenceOfElementLocated(pesanXpath)
            );
            String actualText = pesanElement.getText().trim();
            System.out.println("[TC-FR07-1] Pesan ditemukan via XPath fallback: \"" + actualText + "\"");
            return actualText.contains(expectedMessage);
        } catch (Exception e) {
            System.out.println("[TC-FR07-1] GAGAL: Pesan empty-state tidak ditemukan di halaman. " + e.getMessage());
            return false;
        }
    }

    /** Verifikasi sidebar menampilkan minimal X materi atau Y kuis */
    public boolean sidebarMenampilkanMateriAtauKuis(int minMateri, int minKuis) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//li[contains(@class,'learn-list-item')]")));
            int totalItems = locators.sidebarItems.size();
            System.out.println("[sidebar] Total item ditemukan: " + totalItems);
            return totalItems >= minMateri || totalItems >= minKuis;
        } catch (Exception e) {
            System.out.println("[sidebar] Item sidebar tidak ditemukan: " + e.getMessage());
            return false;
        }
    }
}