package com.polban.jtk.locators;

import org.openqa.selenium.By;

/**
 * Semua web locator untuk halaman Login JTK Learn.
 * Kelas ini HANYA berisi definisi selektor — tidak ada logika aksi.
 */
public class LoginPageLocators {

    /** Field input email */
    public static final By FIELD_EMAIL    = By.cssSelector("input[placeholder='Masukkan email']");

    /** Field input kata sandi */
    public static final By FIELD_PASSWORD = By.cssSelector("input[placeholder='Masukan kata sandi']");

    /** Tombol submit login */
    public static final By TOMBOL_LOGIN   = By.cssSelector("button[type='submit']");

    /** Popup SweetAlert2 yang muncul saat login gagal */
    public static final By SWAL_POPUP     = By.cssSelector(".swal2-popup");
}
