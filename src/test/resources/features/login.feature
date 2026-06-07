Feature: Login JTK Learn
  Sebagai mahasiswa JTK Polban
  Saya ingin bisa login ke platform JTK Learn
  Agar saya bisa mengakses materi perkuliahan

  Scenario: Login berhasil dengan akun valid
    Given pengguna membuka halaman login JTK Learn
    When  pengguna memasukkan email "Far@example.com"
    And   pengguna memasukkan password "Far"
    And   pengguna mengklik tombol Login
    Then  pengguna berhasil masuk ke halaman dashboard