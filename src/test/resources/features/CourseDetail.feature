Feature: Course Detail JTK Learn

  Background:
    Given pengguna sudah login ke JTK Learn

  @Farida
  Scenario: Membuka course CyberSecurity
    When pengguna memilih course "CyberSecurity"
    And pengguna mengklik tombol "Lanjutkan Kursus"
    Then pengguna berhasil masuk ke halaman detail course

  @Nobby
  # TC ID   : TC-FR07-1 - Negative - Nobby
  # Module  : Course Detail Functionality
  # Scenario: Validasi halaman Detail Kursus
  # Given   : Pelajar telah login, enroll kursus, dan berada di halaman course detail
  # When    : Pelajar menekan tombol "Lanjutkan Kursus"
  # Test Data: NAMA_COURSE=Testing, ID_COURSE=84
  # Expected: Jika data detail kursus (materi dan kuis) tidak ada,
  #           sistem menampilkan pesan "Belum ada materi atau kuis yang dibuat."
  # Catatan : Pengecekan DB dilakukan secara statis berdasarkan ID_COURSE=84
  #           karena koneksi database sedang error.
  Scenario: Validasi halaman Detail Kursus menampilkan pesan ketika tidak ada konten
    Given pelajar sudah login dan sudah enroll kursus "Testing"
    And   pelajar sudah berada di halaman course detail kursus dengan ID "84"
    When  pelajar menekan tombol "Lanjutkan Kursus"
    Then  sistem menampilkan pesan "There are no materials or quizzes for this course yet"

  @Zahra
  Scenario: Validasi navigasi dan judul halaman kuis
    When pengguna memilih course "CyberSecurity"
    And pengguna mengklik tombol "Lanjutkan Kursus"
    And pengguna mengklik menu kuis "Kuis 1" pada sidebar navigasi
    Then pengguna berhasil masuk ke halaman persiapan kuis
    And judul kuis yang tampil di layar adalah "Kuis 1"