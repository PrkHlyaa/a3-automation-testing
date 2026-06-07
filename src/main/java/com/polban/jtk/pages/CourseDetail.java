package com.polban.jtk.pages;

import java.time.Duration;

import com.polban.jtk.locators.CourseDetailLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class CourseDetail extends BasePage {

    // Locators di-init via PageFactory di constructor
    private final CourseDetailLocators loc = new CourseDetailLocators();

    public CourseDetail(WebDriver driver) {
        super(driver);
        initLocators(loc);  // menghidupkan @FindBy pada CourseDetailLocators
    }

    public void pilihCourse(String namaCourse) {
        // Locator dinamis — tetap menggunakan By karena @FindBy tidak mendukung parameter runtime
        WebElement card = driver.findElement(CourseDetailLocators.cardCourse(namaCourse));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", card);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", card);
    }

    public void klikTombol(String namaTombol) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Tutup SweetAlert2 popup jika masih muncul (agar tidak menghalangi klik)
        try {
            if (loc.swalContainer.isDisplayed()) {
                System.out.println("[klikTombol] SweetAlert2 popup terdeteksi, menutupnya dulu...");
                // Klik tombol OK/Confirm di dalam popup jika ada
                try {
                    loc.swalConfirm.click();
                } catch (Exception e) {
                    // Jika tidak ada tombol confirm, tekan Escape untuk menutup
                    loc.tagBody.sendKeys(org.openqa.selenium.Keys.ESCAPE);
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

    public boolean sudahMasukCourseDetail() {

        String url = driver.getCurrentUrl();

        return url.contains("learn-course")
                || url.contains("course");
    }

    // TC 2.3.2 / TC-11 — Navigasi langsung ke halaman kursus berdasarkan ID
    public void bukaKursusDenganId(String idKursus) {
        String url = "https://polban-space.cloudias79.com/jtk-learn/learn-course/" + idKursus;
        System.out.println("[TC-11] Membuka URL kursus: " + url);
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("learn-course"));
        System.out.println("[TC-11] Halaman kursus terbuka: " + driver.getCurrentUrl());
    }

    // TC 2.3.2 / TC-11 — Verifikasi daftar kursus yang diikuti pelajar ditampilkan
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
            wait.until(ExpectedConditions.visibilityOf(loc.cardKursus));
            adaKartuKursus = loc.cardKursus.isDisplayed();
            System.out.println("[TC-11] Elemen daftar kursus ditemukan.");
        } catch (Exception e) {
            System.out.println("[TC-11] Elemen daftar kursus tidak ditemukan: " + e.getMessage());
        }

        return urlSesuai || adaKartuKursus;
    }
}