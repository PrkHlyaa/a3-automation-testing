package com.polban.jtk.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Semua web locator untuk halaman Login JTK Learn.
 * Kelas ini HANYA berisi definisi selektor — tidak ada logika aksi.
 * Menggunakan @FindBy (Page Factory pattern) untuk deklarasi elemen.
 */
public class LoginPageLocators {

    /** Field input email */
    @FindBy(css = "input[placeholder='Masukkan email']")
    public WebElement fieldEmail;

    /** Field input kata sandi */
    @FindBy(css = "input[placeholder='Masukan kata sandi']")
    public WebElement fieldPassword;

    /** Tombol submit login */
    @FindBy(css = "button[type='submit']")
    public WebElement tombolLogin;

    /** Popup SweetAlert2 yang muncul saat login gagal */
    @FindBy(css = ".swal2-popup")
    public WebElement swalPopup;
}
