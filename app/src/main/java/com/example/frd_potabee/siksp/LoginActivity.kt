package com.example.frd_potabee.siksp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

            // ===== AUTENTIKASI: cocokkan dengan akun yang didaftarkan =====
            val akunPref = getSharedPreferences("akun_pref", MODE_PRIVATE)
            val savedUsername = akunPref.getString("username", null)
            val savedPassword = akunPref.getString("password", null)

            if (savedUsername == null || savedPassword == null) {
                tampilkanDialog(
                    "Login Gagal",
                    "Akun belum terdaftar. Silakan daftar terlebih dahulu."
                )
                return@setOnClickListener
            }

            if (username == savedUsername && password == savedPassword) {

                // ===== AUTENTIKASI BERHASIL: simpan sesi login =====
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.putString("nama", akunPref.getString("nama", username))
                editor.apply()

                startActivity(Intent(this, BaseActivity::class.java))
                finish()

            } else {
                tampilkanDialog(
                    "Login Gagal",
                    "Username atau password salah."
                )
            }
        }

        // Informasi Koperasi
        binding.btnInfo.setOnClickListener {
            val intent = Intent(this, WebActivity::class.java)
            startActivity(intent)
        }

        // Daftar akun
        binding.txtRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun tampilkanDialog(judul: String, pesan: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(judul)
            .setMessage(pesan)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}