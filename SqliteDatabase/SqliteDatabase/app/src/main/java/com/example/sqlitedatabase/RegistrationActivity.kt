package com.example.sqlitedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast

class RegistrationActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        var ed_email = findViewById<EditText>(R.id.edt_email)
        var ed_password = findViewById<EditText>(R.id.edt_password)
        var ed_mobile = findViewById<EditText>(R.id.edt_phone)

//        var rg = findViewById<RadioGroup>(R.id.rggroup)

        var btnregister = findViewById<Button>(R.id.btn_register)
        var id:Int
        var dbhelper = DatabaseHelper(this)
        btnregister.setOnClickListener {

                var successid = dbhelper.insertData(User(it.id!!,ed_email.text.toString(),ed_password.text.toString(),ed_mobile.text.toString()))
            if(successid>1)
            {
                Log.d("MYDATA","--> id "+successid)
                Toast.makeText(RegistrationActivity@this, "Successfully registered id = "+successid, Toast.LENGTH_SHORT).show()
            }
        }
    }
}