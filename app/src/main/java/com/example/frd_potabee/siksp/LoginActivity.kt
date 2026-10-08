package com.example.frd_potabee.siksp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.frd_potabee.databinding.ActivityLoginBinding

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

            // Jika username dan password sudah diisi,
            // untuk sementara tetap berada di halaman Login.
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
}