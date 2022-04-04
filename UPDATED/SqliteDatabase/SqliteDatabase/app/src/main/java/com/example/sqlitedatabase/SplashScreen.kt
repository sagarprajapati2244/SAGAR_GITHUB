package com.example.sqlitedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var imageview = findViewById<ImageView>(R.id.splash_imageview)

        Handler().postDelayed({
           var intent = Intent(this , MainActivity::class.java)
           startActivity(intent)
           finish()

        },3000)
    }
}