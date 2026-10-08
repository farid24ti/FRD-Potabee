package com.example.frd_potabee.siksp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.frd_potabee.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol kembali pada Toolbar
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // Tombol Daftar
        binding.btnDaftar.setOnClickListener {

            val nama = binding.inputNama.text.toString().trim()
            val username = binding.inputUsername.text.toString().trim()
            val noHp = binding.inputNoHp.text.toString().trim()
            val password = binding.inputPassword.text.toString()
            val konfirmasiPassword =
                binding.inputKonfirmasiPassword.text.toString()

            if (nama.isEmpty()) {
                binding.inputNama.error = "Nama lengkap harus diisi"
                binding.inputNama.requestFocus()
                return@setOnClickListener
            }

            if (username.isEmpty()) {
                binding.inputUsername.error = "Username harus diisi"
                binding.inputUsername.requestFocus()
                return@setOnClickListener
            }

            if (noHp.isEmpty()) {
                binding.inputNoHp.error = "Nomor HP harus diisi"
                binding.inputNoHp.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.inputPassword.error = "Password harus diisi"
                binding.inputPassword.requestFocus()
                return@setOnClickListener
            }

            if (konfirmasiPassword.isEmpty()) {
                binding.inputKonfirmasiPassword.error =
                    "Konfirmasi password harus diisi"
                binding.inputKonfirmasiPassword.requestFocus()
                return@setOnClickListener
            }

            if (password != konfirmasiPassword) {
                binding.inputKonfirmasiPassword.error =
                    "Password tidak sama"
                binding.inputKonfirmasiPassword.requestFocus()
                return@setOnClickListener
            }

            Toast.makeText(
                this,
                "Pendaftaran berhasil",
                Toast.LENGTH_SHORT
            ).show()

            startActivity(
                Intent(this, LoginActivity::class.java)
            )

            finish()
        }

        // Kembali ke Login
        binding.txtLogin.setOnClickListener {
            finish()
        }
    }
}