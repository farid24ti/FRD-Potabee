package com.example.frd_potabee.siksp

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.frd_potabee.databinding.ActivityKartuAnggotaBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel

class KartuAnggotaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKartuAnggotaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKartuAnggotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol kembali pada Toolbar
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // 1. Ambil data anggota dari SharedPreferences
        val userPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val akunPref = getSharedPreferences("akun_pref", MODE_PRIVATE)

        val username = userPref.getString("username", "-") ?: "-"
        val nama = userPref.getString("nama", username) ?: username

        // Email hanya ada jika login memakai akun yang didaftarkan
        val email = if (akunPref.getString("username", null) == username) {
            akunPref.getString("email", "-") ?: "-"
        } else {
            "-"
        }

        // 2. Tampilkan data teks pada kartu
        binding.txtNama.text = nama
        binding.txtUsername.text = username
        binding.txtEmail.text = email

        // 3. Isi QR code (password sengaja TIDAK disertakan)
        val isiQr = "SI-KSP MOBILE\n" +
                "Nama: $nama\n" +
                "Username: $username\n" +
                "Email: $email"

        binding.imgQr.setImageBitmap(buatQrCode(isiQr, 600))
    }

    // Mengubah teks menjadi gambar QR code (ZXing)
    private fun buatQrCode(isi: String, ukuran: Int): Bitmap {

        val hints = mapOf(
            EncodeHintType.CHARACTER_SET to "UTF-8",
            EncodeHintType.ERROR_CORRECTION to ErrorCorrectionLevel.M,
            EncodeHintType.MARGIN to 1
        )

        // Teks -> matriks kotak hitam/putih
        val matrix = QRCodeWriter().encode(isi, BarcodeFormat.QR_CODE, ukuran, ukuran, hints)

        val lebar = matrix.width
        val tinggi = matrix.height
        val pixels = IntArray(lebar * tinggi)

        // Matriks -> warna piksel (hitam = kotak, putih = kosong)
        for (y in 0 until tinggi) {
            for (x in 0 until lebar) {
                pixels[y * lebar + x] = if (matrix[x, y]) Color.BLACK else Color.WHITE
            }
        }

        // Piksel -> Bitmap
        val bitmap = Bitmap.createBitmap(lebar, tinggi, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, lebar, 0, 0, lebar, tinggi)
        return bitmap
    }
}