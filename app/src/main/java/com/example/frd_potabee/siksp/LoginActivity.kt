package com.example.frd_potabee.siksp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.frd_potabee.databinding.ActivityLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // Tombol Login
        binding.btnLogin.setOnClickListener {

            val username = binding.inputUsername.text.toString().trim()
            val password = binding.inputPassword.text.toString()

            if (username.isEmpty() && password.isEmpty()) {

                binding.inputUsername.error = "Username harus diisi"
                binding.inputPassword.error = "Password harus diisi"

                binding.inputUsername.requestFocus()

                return@setOnClickListener
            }

            if (username.isEmpty()) {

                binding.inputUsername.error = "Username harus diisi"
                binding.inputUsername.requestFocus()

                return@setOnClickListener
            }

            if (password.isEmpty()) {

                binding.inputPassword.error = "Password harus diisi"
                binding.inputPassword.requestFocus()

                return@setOnClickListener
            }

            // ===== AUTENTIKASI =====
            val akunPref = getSharedPreferences("akun_pref", MODE_PRIVATE)
            val savedUsername = akunPref.getString("username", null)
            val savedPassword = akunPref.getString("password", null)

            // Kondisi 1: username == password | Kondisi 2: cocok dengan SharedPreferences
            val loginValid = (username == password) ||
                    (username == savedUsername && password == savedPassword)

            if (loginValid) {
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.putString(
                    "nama",
                    if (username == savedUsername) akunPref.getString("nama", username) else username
                )
                editor.apply()

                // Login berhasil -> Home
                startActivity(Intent(this, BaseActivity::class.java))
                finish()
            } else {
                // Login gagal -> MaterialAlertDialog
                tampilkanDialog("Login Gagal", "Username atau password salah.")
            }
        }

        // Informasi Koperasi
        binding.btnInfo.setOnClickListener {
            val intent = Intent(this, WebActivity::class.java)
            startActivity(intent)
        }

        // Register With Gmail
        binding.btnRegisterGmail.setOnClickListener {
            startActivity(Intent(this, EmailActivity::class.java))
        }

        // ===== FITUR BARU: LOGIN SIDIK JARI =====
        binding.btnBiometrik.setOnClickListener {
            loginDenganBiometrik()
        }
    }

    // ===== FITUR BARU: LOGIN SIDIK JARI =====
    private fun loginDenganBiometrik() {

        // 1. Harus sudah ada akun terdaftar
        val akunPref = getSharedPreferences("akun_pref", MODE_PRIVATE)
        val savedUsername = akunPref.getString("username", null)

        if (savedUsername == null) {
            tampilkanDialog(
                "Akun Belum Terdaftar",
                "Silakan registrasi terlebih dahulu sebelum memakai sidik jari."
            )
            return
        }

        // 2. Cek apakah perangkat mendukung dan sudah punya sidik jari terdaftar
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate(BIOMETRIC_WEAK)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                // lanjut ke langkah 3
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                tampilkanDialog("Tidak Didukung", "Perangkat ini tidak memiliki sensor biometrik.")
                return
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                tampilkanDialog("Sensor Tidak Tersedia", "Sensor biometrik sedang tidak dapat dipakai.")
                return
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                tampilkanDialog(
                    "Sidik Jari Belum Terdaftar",
                    "Daftarkan sidik jari di Pengaturan perangkat terlebih dahulu."
                )
                return
            }
            else -> {
                tampilkanDialog("Tidak Tersedia", "Autentikasi biometrik tidak dapat digunakan saat ini.")
                return
            }
        }

        // 3. Siapkan BiometricPrompt beserta callback hasilnya
        val executor = ContextCompat.getMainExecutor(this)

        val biometricPrompt = BiometricPrompt(
            this, executor,
            object : BiometricPrompt.AuthenticationCallback() {

                // Sidik jari cocok -> simpan sesi login (isLogin = true)
                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)

                    val nama = akunPref.getString("nama", savedUsername) ?: savedUsername

                    val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.putBoolean("isLogin", true)
                    editor.putString("username", savedUsername)
                    editor.putString("nama", nama)
                    editor.apply()

                    startActivity(Intent(this@LoginActivity, BaseActivity::class.java))
                    finish()
                }

                // Error (dibatalkan, terkunci, dll.)
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)

                    // Dibatalkan pengguna tidak perlu ditampilkan sebagai error
                    if (errorCode != BiometricPrompt.ERROR_USER_CANCELED &&
                        errorCode != BiometricPrompt.ERROR_NEGATIVE_BUTTON
                    ) {
                        tampilkanDialog("Autentikasi Gagal", errString.toString())
                    }
                }

                // Sidik jari tidak cocok (dialog tetap terbuka, boleh coba lagi)
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(
                        applicationContext,
                        "Sidik jari tidak dikenali, coba lagi",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )

        // 4. Tampilan dialog sidik jari bawaan sistem
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Masuk ke SI-KSP Mobile")
            .setSubtitle("Tempelkan sidik jari Anda untuk masuk")
            .setNegativeButtonText("Batal")
            .setAllowedAuthenticators(BIOMETRIC_WEAK)
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    private fun tampilkanDialog(judul: String, pesan: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(judul)
            .setMessage(pesan)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}