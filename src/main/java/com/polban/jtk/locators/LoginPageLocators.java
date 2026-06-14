package com.polban.jtk.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    /** Tombol OK/Tutup pada SweetAlert2 popup */
    @FindBy(css = ".swal2-confirm")
    public WebElement swalTombolOk;
}
