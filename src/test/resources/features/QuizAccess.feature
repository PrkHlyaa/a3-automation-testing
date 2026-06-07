Feature: Akses Kuis dari Course Detail
  Sebagai mahasiswa JTK Polban
  Saya ingin bisa membuka halaman kuis dari detail kursus
  Agar saya bisa mengerjakan kuis yang sesuai

  Background:
    Given pengguna sudah login ke JTK Learn
    And pengguna memilih course "CyberSecurity"
    And pengguna mengklik tombol "Lanjutkan Kursus"

  Scenario: Validasi navigasi dan judul halaman kuis
    When pengguna mengklik menu kuis "Kuis 1" pada sidebar navigasi
    Then pengguna berhasil masuk ke halaman persiapan kuis
    And judul kuis yang tampil di layar adalah "Kuis 1"