package com.example.frd_potabee.siksp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.frd_potabee.databinding.ActivityEmailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnLanjut.setOnClickListener {
            val email = binding.inputEmail.text.toString().trim()

            if (email.isEmpty()) {
                tampilkanError("Email tidak boleh kosong")
                return@setOnClickListener
            }

            // "@gmail.com" = 10 karakter, jadi harus ada nama sebelum domain
            if (!email.endsWith("@gmail.com") || email.length <= 10) {
                tampilkanError("Email harus menggunakan domain @gmail.com")
                return@setOnClickListener
            }

            // Email valid -> ke halaman Registrasi, email ikut dibawa
            val intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }
    }

    private fun tampilkanError(pesan: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Email Tidak Valid")
            .setMessage(pesan)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}