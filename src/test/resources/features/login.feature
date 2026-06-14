Feature: Login JTK Learn
  Sebagai mahasiswa JTK Polban
  Saya ingin bisa login ke platform JTK Learn
  Agar saya bisa mengakses materi perkuliahan

  @Farida
  # TC: Login Pelajar valid
  Scenario: Login berhasil dengan akun valid
    Given pengguna membuka halaman login JTK Learn
    When  pengguna memasukkan email "Far@example.com"
    And   pengguna memasukkan password "Far"
    And   pengguna mengklik tombol Login
    Then  pengguna berhasil masuk ke halaman dashboard

  @Nobby
  # TC ID: 1.1.5 / 1.5 - Negative - Nobby
  # Memverifikasi sistem menolak login dengan username yang tidak terdaftar
  # Test Data: Username=tidakterdaftar@example.com, Password=apapun123
  Scenario: Memverifikasi sistem menolak login dengan username yang tidak terdaftar
    Given pengguna membuka halaman login JTK Learn
    When  pengguna memasukkan email "tidakterdaftar@example.com"
    And   pengguna memasukkan password "apapun123"
    And   pengguna mengklik tombol Login
    Then  muncul pesan error login
    And   pengguna menutup popup error
    And   pengguna tetap berada di halaman Login
