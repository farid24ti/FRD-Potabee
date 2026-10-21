package com.example.frd_potabee.siksp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.frd_potabee.databinding.FragmentInformasiBinding

class InformasiFragment : Fragment() {

    private var _binding: FragmentInformasiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInformasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.webViewClient = WebViewClient()

        val html = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>

                    body {
                        margin: 0;
                        padding: 20px;
                        font-family: sans-serif;
                        background: #F5F9FF;
                        color: #172B4D;
                    }

                    .header {
                        background: #0D6EFD;
                        color: white;
                        padding: 22px;
                        border-radius: 18px;
                        margin-bottom: 18px;
                    }

                    .header h1 {
                        margin: 0 0 8px 0;
                        font-size: 23px;
                    }

                    .header p {
                        margin: 0;
                        font-size: 13px;
                    }

                    .card {
                        background: white;
                        padding: 18px;
                        margin-bottom: 14px;
                        border-radius: 16px;
                    }

                    .card h2 {
                        margin-top: 0;
                        color: #0D6EFD;
                        font-size: 18px;
                    }

                    .card p {
                        line-height: 1.6;
                        font-size: 14px;
                        color: #53657D;
                    }

                    .card ul {
                        padding-left: 20px;
                        color: #53657D;
                        line-height: 1.7;
                        font-size: 14px;
                    }

                    .footer {
                        text-align: center;
                        color: #7B8CA3;
                        font-size: 12px;
                        margin-top: 20px;
                        margin-bottom: 20px;
                    }

                </style>
            </head>

            <body>

                <div class="header">
                    <h1>Informasi Koperasi</h1>
                    <p>SI-KSP Mobile</p>
                </div>

                <div class="card">
                    <h2>Apa itu Koperasi Simpan Pinjam?</h2>

                    <p>
                        Koperasi Simpan Pinjam merupakan koperasi yang menjalankan
                        kegiatan usaha simpan pinjam untuk membantu memenuhi
                        kebutuhan keuangan para anggotanya.
                    </p>
                </div>

                <div class="card">
                    <h2>Layanan Simpanan</h2>

                    <p>
                        Anggota dapat menyimpan dana melalui layanan simpanan
                        koperasi sesuai dengan ketentuan yang berlaku.
                    </p>

                    <ul>
                        <li>Simpanan anggota</li>
                        <li>Pencatatan transaksi simpanan</li>
                        <li>Informasi saldo simpanan</li>
                    </ul>
                </div>

                <div class="card">
                    <h2>Layanan Pinjaman</h2>

                    <p>
                        Koperasi dapat menyediakan fasilitas pinjaman kepada
                        anggota berdasarkan ketentuan, persyaratan, dan
                        kemampuan pembayaran anggota.
                    </p>

                    <ul>
                        <li>Pengajuan pinjaman</li>
                        <li>Informasi jumlah pinjaman</li>
                        <li>Informasi angsuran</li>
                        <li>Riwayat pinjaman</li>
                    </ul>
                </div>

                <div class="card">
                    <h2>Keamanan dan Kepercayaan</h2>

                    <p>
                        Pengelolaan koperasi harus dilakukan secara transparan,
                        bertanggung jawab, dan sesuai dengan ketentuan
                        peraturan yang berlaku.
                    </p>
                </div>

                <div class="card">
                    <h2>Tentang OJK</h2>

                    <p>
                        Otoritas Jasa Keuangan (OJK) merupakan lembaga yang
                        mengatur dan mengawasi sektor jasa keuangan sesuai
                        dengan kewenangannya.
                    </p>

                    <p>
                        Informasi mengenai koperasi yang menjalankan kegiatan
                        di sektor jasa keuangan dapat mengacu pada ketentuan
                        OJK yang berlaku.
                    </p>
                </div>

                <div class="footer">
                    SI-KSP Mobile<br>
                    Sistem Informasi Koperasi Simpan Pinjam
                </div>

            </body>
            </html>
        """.trimIndent()

        binding.webView.loadDataWithBaseURL(
            null,
            html,
            "text/html",
            "UTF-8",
            null
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}