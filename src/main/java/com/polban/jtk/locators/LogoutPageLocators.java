package com.polban.jtk.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPageLocators {

    /** Menu profil pengguna di navbar */
    @FindBy(css = "#navbarNav > ul > li.nav-name.dropdown > a")
    public WebElement menuProfil;

    /** Ikon atau tombol logout */
    @FindBy(css = ".logout-icon, img[alt='Logout Icon']")
    public WebElement tombolKeluar;

    /** Field email di halaman Login — digunakan untuk verifikasi sudah kembali ke Login */
    @FindBy(css = "input[placeholder='Masukkan email']")
    public WebElement fieldEmail;
}
