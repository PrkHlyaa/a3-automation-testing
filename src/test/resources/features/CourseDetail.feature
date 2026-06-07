Feature: Course Detail JTK Learn

  Background:
    Given pengguna sudah login ke JTK Learn

  @Farida
  Scenario: Membuka course CyberSecurity
    When pengguna memilih course "CyberSecurity"
    And pengguna mengklik tombol "Lanjutkan Kursus"
    Then pengguna berhasil masuk ke halaman detail course

  @Nobby
  # TC ID: 2.3.2 / TC-11 - Positif - Nobby
  # Klik Kursus Saya — menampilkan daftar kursus yang diikuti pelajar
  # Test Data: ID_Pelajar=26, Akun Pelajar=Far, Nama Kursus=FR007
  Scenario: Klik Kursus Saya menampilkan daftar kursus yang diikuti pelajar
    When pengguna mengklik tombol "Kursus Saya"
    Then sistem menampilkan daftar kursus yang diikuti pelajar

  @Zahra
  Scenario: Validasi navigasi dan judul halaman kuis
    When pengguna memilih course "CyberSecurity"
    And pengguna mengklik tombol "Lanjutkan Kursus"
    And pengguna mengklik menu kuis "Kuis 1" pada sidebar navigasi
    Then pengguna berhasil masuk ke halaman persiapan kuis
    And judul kuis yang tampil di layar adalah "Kuis 1"