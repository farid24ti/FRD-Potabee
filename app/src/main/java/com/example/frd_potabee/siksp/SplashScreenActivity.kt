package com.example.frd_potabee.siksp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.frd_potabee.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(2000) // tampil 2 detik

            // Cek sesi login yang tersimpan di SharedPreferences
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            val isLogin = sharedPref.getBoolean("isLogin", false)

            val target = if (isLogin) {
                BaseActivity::class.java    // sudah login  -> halaman utama
            } else {
                LoginActivity::class.java   // belum login  -> halaman Login
            }

            startActivity(Intent(this@SplashScreenActivity, target))
            finish()
        }
    }
}