package com.example.sqlitedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewUserDetails : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_user_details)

        var rev = findViewById<RecyclerView>(R.id.recyclerview)
        rev.layoutManager = LinearLayoutManager(ViewUserDetails@this)

        var db_helper = DatabaseHelper(this)

        var userList:MutableList<UserModal> = ArrayList()
        userList = db_helper.getall_data()

        var myAdapter = MyAdapter(ViewUserDetails@this,userList)
        rev.adapter = myAdapter
    }
}