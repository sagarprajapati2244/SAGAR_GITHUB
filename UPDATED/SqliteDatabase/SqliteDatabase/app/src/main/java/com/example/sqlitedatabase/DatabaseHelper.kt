package com.example.sqlitedatabase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(var context: Context):SQLiteOpenHelper(context , DATABASE_NAME , null , DATABASE_VERSION)
{
    companion object
    {
        private final var DATABASE_NAME = "user"
        private final var DATABASE_VERSION = 1

        private const val TABLE_NAME = "student"
        private const val KEY_ID = "id"
        private const val KEY_USERNAME = "username"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
        private const val KEY_MOBILE = "phone"

    }

    override fun onCreate(p0: SQLiteDatabase?) {

        //table creation
        //create table tablename (id integer primary key autoincrement , email varchar (20)....)


        val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME+"( "+ KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_USERNAME + " TEXT ," +
                KEY_EMAIL + " TEXT ," + KEY_PASSWORD + " TEXT ," + KEY_MOBILE + " TEXT )" )

        p0?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertData(userModal: UserModal) : Long{

        var db = this.writableDatabase
        var contentValues = ContentValues()

        contentValues.put(KEY_USERNAME , userModal.user_name)
        contentValues.put(KEY_EMAIL , userModal.user_email)
        contentValues.put(KEY_PASSWORD , userModal.user_pass)
        contentValues.put(KEY_MOBILE , userModal.user_phone)

        var insertData = db.insert(TABLE_NAME , null , contentValues)
        return insertData

    }

    @SuppressLint("Range")
    fun getall_data():MutableList<UserModal>
    {
        var userList : MutableList<UserModal> = ArrayList()

        var sel_que = "SELECT * FROM $TABLE_NAME"

        var cursor :Cursor?
        var db = this.readableDatabase

        try
        {
            cursor = db.rawQuery(sel_que,null)
        }
        catch (Exception:SQLiteException)
        {
            db.execSQL(sel_que)
            return ArrayList()
        }

        var user_id :Int
        var username :String
        var email : String
        var mobile :String
        var password :String

        if(cursor.count > 0)
        {
            if(cursor.moveToFirst())
            {
                do {
                     user_id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                     username = cursor.getString(cursor.getColumnIndex(KEY_USERNAME))
                     email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL))
                     password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD))
                     mobile = cursor.getString(cursor.getColumnIndex(KEY_MOBILE))

                    var userData = UserModal(user_id = user_id,user_name = username, user_email =  email, user_pass = password, user_phone = mobile)
                    userList.add(userData)
                }while (cursor.moveToNext());
            }
        }
        return userList
    }

    fun updateData(userData:UserModal):Int
    {
        var db = this.writableDatabase
        var contentValues = ContentValues()

        contentValues.put(KEY_USERNAME,userData.user_name)
        contentValues.put(KEY_EMAIL,userData.user_email)
        contentValues.put(KEY_PASSWORD,userData.user_pass)
        contentValues.put(KEY_MOBILE,userData.user_phone)
        Log.d("mydata","---------> for update "+userData.user_id)
        var id = db.update(TABLE_NAME,contentValues, KEY_ID +"="+userData.user_id,null)
        db.close()
        return id
    }
}