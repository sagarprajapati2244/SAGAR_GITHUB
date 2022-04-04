package com.example.sqlitedatabase

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var context: Context,var userList:MutableList<UserModal>):RecyclerView.Adapter<MyAdapter.MyClass>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyClass {
        var view = LayoutInflater.from(context).inflate(R.layout.item_view_user_data , parent , false)
        return MyClass(view)
    }

    override fun onBindViewHolder(holder: MyClass, position: Int)
    {
        var mydata = userList[position]

        holder.userName.text = mydata.user_name
        holder.userEmail.text = mydata.user_email
        holder.userPhone.text = mydata.user_phone

        holder.btn_edit.setOnClickListener {

            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)

            dialog.setContentView(R.layout.update_dialog)
            var ed_username = dialog.findViewById<EditText>(R.id.edusername)
            var ed_email = dialog.findViewById<EditText>(R.id.edemail)
            var ed_password = dialog.findViewById<EditText>(R.id.edpassword)
            var ed_mobile = dialog.findViewById<EditText>(R.id.edmobile)

            var btn_update = dialog.findViewById<Button>(R.id.btnupdate_data)

            ed_username.setText(mydata.user_name)
            ed_email.setText(mydata.user_email)
            ed_password.setText(mydata.user_pass)
            ed_mobile.setText(mydata.user_phone)

            btn_update.setOnClickListener {
                var db = DatabaseHelper(context)
                var userModal = UserModal(mydata.user_id,ed_username.text.toString(),ed_email.text.toString(),ed_password.text.toString(),ed_mobile.text.toString())

                var id = db.updateData(userModal)
                Toast.makeText(context, "Successfully data updated "+id, Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            dialog.show()
            Toast.makeText(context, "Edit "+mydata.user_name, Toast.LENGTH_SHORT).show()
        }
        holder.btn_delete.setOnClickListener {
            Toast.makeText(context, "Delete"+mydata.user_name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return  userList.size
    }

    class MyClass(view:View) : RecyclerView.ViewHolder(view)
    {
        var userName = view.findViewById<TextView>(R.id.tv_username)
        var userEmail = view.findViewById<TextView>(R.id.tv_email)
        var userPhone = view.findViewById<TextView>(R.id.tv_phone)

        var btn_edit = view.findViewById<Button>(R.id.btn_edit)
        var btn_delete = view.findViewById<Button>(R.id.btn_delete)
    }
}