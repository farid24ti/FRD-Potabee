package com.example.frd_potabee.siksp

import android.content.Intent
import android.os.Bundle
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

        val email = intent.getStringExtra("email") ?: ""
        binding.inputEmail.setText(email)

        // Tombol Daftar
        binding.btnDaftar.setOnClickListener {

            val nama = binding.inputNama.text.toString().trim()
            val username = binding.inputUsername.text.toString()
            val password = binding.inputPassword.text.toString()


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

            if (password.isEmpty()) {
                binding.inputPassword.error = "Password harus diisi"
                binding.inputPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.inputPassword.error = "Password minimal 6 karakter"
                binding.inputPassword.requestFocus()
                return@setOnClickListener
            }

            if (username.contains(" ")) {
                binding.inputUsername.error = "Username tidak boleh mengandung spasi"
                binding.inputUsername.requestFocus()
                return@setOnClickListener
            }



            // ===== SIMPAN DATA REGISTRASI (SharedPreferences) =====
            val akunPref = getSharedPreferences("akun_pref", MODE_PRIVATE)
            val editor = akunPref.edit()
            editor.putString("nama", nama)
            editor.putString("email", email)
            editor.putString("username", username)
            editor.putString("password", password)
            editor.apply()

            startActivity(Intent(this, RegistrasiBerhasilActivity::class.java))
            finish()
        }

        // Kembali ke Login
        binding.txtLogin.setOnClickListener {
            finish()
        }
    }
}