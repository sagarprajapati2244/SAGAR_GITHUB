package com.example.sqlitedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        var username = findViewById<EditText>(R.id.edt_username)
        var email = findViewById<EditText>(R.id.edt_email)
        var pass = findViewById<EditText>(R.id.edt_password)
        var phone = findViewById<EditText>(R.id.edt_phone)

        var btn_register = findViewById<Button>(R.id.btn_register)
        var tv_login = findViewById<TextView>(R.id.tv_login)


        tv_login.setOnClickListener {
             var intent = Intent(RegistrationActivity@this , MainActivity::class.java)
             startActivity(intent)
             finish()
        }

        var id : Int
        var dbhelper = DatabaseHelper(this)


        btn_register.setOnClickListener {

            var success_id = dbhelper.insertData(UserModal(it.id!! , username.text.toString() , email.text.toString() , pass.text.toString() , phone.text.toString()))
            Log.d("mydata","---> success_id"+success_id)
            if (success_id >= 1)
            {
                Log.d("mydata" , "Id Created $success_id")
                Toast.makeText(RegistrationActivity@this, "Successfully Insert Data"+success_id, Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Something went wrong ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}