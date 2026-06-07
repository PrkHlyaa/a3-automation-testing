package com.polban.jtk.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Semua web locator untuk halaman Course Detail JTK Learn.
 * Kelas ini HANYA berisi definisi selektor — tidak ada logika aksi.
 * Menggunakan @FindBy (Page Factory pattern) untuk deklarasi elemen statis,
 * dan metode By untuk locator dinamis.
 */
public class CourseDetailLocators {

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
}
