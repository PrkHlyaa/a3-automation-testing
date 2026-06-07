package com.polban.jtk.locators;

import org.openqa.selenium.By;

/**
 * Semua web locator untuk halaman Dashboard JTK Learn.
 * Kelas ini HANYA berisi definisi selektor — tidak ada logika aksi.
 */
public class DashboardPageLocators {

    /** Menu profil pengguna di navbar */
    public static final By MENU_PROFIL   = By.cssSelector("#navbarNav > ul > li.nav-name.dropdown > a");

    /** Ikon atau tombol logout */
    public static final By TOMBOL_KELUAR = By.cssSelector(".logout-icon, img[alt='Logout Icon']");

    /** Field email di halaman Login — digunakan untuk verifikasi sudah kembali ke Login */
    public static final By FIELD_EMAIL   = By.cssSelector("input[placeholder='Masukkan email']");
}
