Feature: Logout JTK Learn
  Sebagai mahasiswa JTK Polban
  Saya ingin bisa logout dari platform JTK Learn
  Agar akun saya aman setelah selesai digunakan

  Background:
    Given pengguna sudah login ke JTK Learn

  Scenario: Logout berhasil melalui menu profil
    When pengguna mengklik menu profil
    And pengguna mengklik tombol Keluar
    Then pengguna diarahkan kembali ke halaman login utama