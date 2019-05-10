package com.singpaulee.talkinghuman

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //TODO 3 Tambahkan jeda untuk pindah ke aktivitas utama
        Handler().postDelayed({
            finish()
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        }, 2000)

    }
}
