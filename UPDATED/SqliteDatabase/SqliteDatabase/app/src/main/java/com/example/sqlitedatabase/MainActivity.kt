package com.example.sqlitedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnviewall_user = findViewById<TextView>(R.id.btnviewall_user)
        var tv_register = findViewById<TextView>(R.id.tv_register)

        tv_register.setOnClickListener {
            var intent = Intent(MainActivity@this , RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnviewall_user.setOnClickListener {
            var intent = Intent(MainActivity@this,ViewUserDetails::class.java)
            startActivity(intent)
            finish()
        }




    }
}