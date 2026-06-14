Feature: Course Detail JTK Learn

  Background:
    Given pengguna sudah login ke JTK Learn

@Zahra
  # TC ID    : TC-FR07-07 - Positive - Zahra
  # Module   : Course Detail Navigation
  # Objektif : Memverifikasi fungsionalitas menu navigasi sidebar untuk mengakses Kuis.
  # Expected : Ketika item kuis diklik, sistem memuat halaman overview/persiapan kuis
  #            yang sesuai dengan judul kuis yang diklik.
  Scenario: Validasi navigasi menuju halaman Kerjakan Kuis
    When pengguna memilih course "CyberSecurity"
    And pengguna mengklik tombol "Lanjutkan Kursus"
    And pengguna mengklik menu kuis "Kuis 1" pada sidebar navigasi
    Then pengguna berhasil masuk ke halaman persiapan kuis
    And judul kuis yang tampil di layar adalah "Kuis 1"

  @Nobby
  # TC ID    : TC-FR07-6 - Positive - Nobby
  # Module   : Course Detail Functionality
  # Scenario : Validasi navigasi menuju halaman Akses Materi
  # Given    : Pelajar telah login, sudah enroll kursus "Testing 2", dan berada di halaman course detail
  # When     : Pelajar menekan navigasi bar pada materi "Apa itu Blackbox testing?"
  # Test Data: NAMA_COURSE=Testing 2, ID_COURSE=169
  # Expected : Sistem menampilkan halaman Akses Materi sesuai dengan materi yang dipilih
  Scenario: Validasi navigasi menuju halaman Akses Materi
    Given pelajar sudah login dan sudah enroll kursus "Testing 2"
    And   pelajar sudah berada di halaman course detail kursus dengan ID "169"
    When  pelajar menekan tombol "Lanjutkan Kursus"
    And   pelajar menekan navigasi materi "Apa itu Blackbox testing?" pada sidebar
    Then  sistem menampilkan halaman akses materi yang sesuai

  @Farida
  # TC ID    : TC-FR07-02 - Positive - Farida
  # Module   : Course Detail Functionality
  # Objektif : Memastikan pelajar dapat mengakses halaman detail kursus dari dashboard.
  # Expected : Halaman dimuat dan menampilkan keadaan awal (default) sesuai FR07
  Scenario: Membuka course CyberSecurity
    When pengguna memilih course "CyberSecurity"
    And pengguna mengklik tombol "Lanjutkan Kursus"
    Then pengguna berhasil masuk ke halaman detail course
    And sidebar menampilkan minimal 1 materi atau 1 kuis