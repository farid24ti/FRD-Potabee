package com.example.frd_potabee.siksp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.frd_potabee.databinding.ActivitySikspMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySikspMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySikspMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            startActivity(
                Intent(this, Splash2Activity::class.java)
            )
        }

        binding.txtSkip.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java)
            )

            finish()
        }
    }
}