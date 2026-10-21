package com.example.frd_potabee.siksp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.frd_potabee.R
import com.example.frd_potabee.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fragment default: Beranda (hanya saat pertama kali dibuat)
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // ===== BOTTOM NAVIGATION =====
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_info -> {
                    replaceFragment(InformasiFragment())
                    true
                }
                R.id.nav_profil -> {
                    replaceFragment(ProfilFragment())
                    true
                }
                else -> false
            }
        }
    }

    // Mengganti fragment yang tampil di fragment_container
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            // tanpa addToBackStack agar tombol Back langsung keluar aplikasi
            .commit()
    }

    // Dipanggil dari fragment untuk berpindah menu bottom bar
    fun pilihMenu(itemId: Int) {
        binding.bottomNav.selectedItemId = itemId
    }
}