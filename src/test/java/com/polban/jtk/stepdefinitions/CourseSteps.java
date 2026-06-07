package com.polban.jtk.stepdefinitions;

import com.polban.jtk.pages.CourseDetail;
import com.polban.jtk.pages.LoginPage;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class CourseSteps {

    private CourseDetail coursePage;

    @Given("pengguna sudah login ke JTK Learn")
    public void penggunaSudahLogin() {

        Hooks.driver.get(
                "https://polban-space.cloudias79.com/jtk-learn/"
        );

        LoginPage loginPage = new LoginPage(Hooks.driver);

        loginPage.masukkanEmail("Far@example.com");
        loginPage.masukkanPassword("Far");
        loginPage.klikLogin();

        coursePage = new CourseDetail(Hooks.driver);
    }

    @When("pengguna memilih course {string}")
    public void penggunaMemilihCourse(String namaCourse) {

        coursePage.pilihCourse(namaCourse);
    }

    @And("pengguna mengklik tombol {string}")
    public void penggunaMengklikTombol(String namaTombol) {
        coursePage.klikTombol(namaTombol);
    }

    @Then("pengguna berhasil masuk ke halaman detail course")
    public void penggunaBerhasilMasukKeCourseDetail() {

        Assertions.assertTrue(
                coursePage.sudahMasukCourseDetail(),
                "Seharusnya masuk ke halaman detail course"
        );
    }
}