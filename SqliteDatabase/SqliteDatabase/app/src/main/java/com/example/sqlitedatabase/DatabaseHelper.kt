package com.example.sqlitedatabase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(var context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION)
{
    companion object
    {
        private final var DATABASE_NAME="my_db"
        private final var DATABASE_VERSION = 1

        private final var TABLE_NAME = "User"
        private final var USER_ID = "id"
        private final var USER_EMAIL ="email"
        private final var USER_PASSWORD = "password"
        private final var USER_MOBILE = "contact"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        // table create
        // create table tablename (id integer primary key autoinrcement,email varchar(20),password varchar(20),mobile varchar(20))

        var table_create = ("create table "+ TABLE_NAME+" ("+ USER_ID+" integer primary key autoincrement , "+ USER_EMAIL+" text , "+ USER_PASSWORD+" text , "+ USER_MOBILE+" text )")

        p0?.execSQL(table_create)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(user: User) :Long
    {
        var db=this.writableDatabase

        // insert into tablename (fieldname value,fieldname value..)

        var contentValues = ContentValues()

        contentValues.put(USER_EMAIL,user.email)
        contentValues.put(USER_PASSWORD,user.password)
        contentValues.put(USER_MOBILE,user.contact)

        var sid = db.insert(TABLE_NAME,null,contentValues)
        return sid
    }
}