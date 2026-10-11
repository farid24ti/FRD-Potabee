package com.example.frd_potabee.siksp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.frd_potabee.databinding.ActivityProfilDeveloperBinding

class ProfilDeveloperActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfilDeveloperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfilDeveloperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol kembali pada Toolbar
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}