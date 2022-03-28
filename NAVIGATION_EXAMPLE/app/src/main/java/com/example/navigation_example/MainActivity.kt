package com.example.navigation_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity()
{
    public lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var toolbar:Toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        var navigation_view:NavigationView = findViewById(R.id.my_nav_view)
        var drawerLayout:DrawerLayout = findViewById(R.id.mydrawer_layout)
        var nav_controll  = findNavController(R.id.fragmentContainerView2)



        appBarConfiguration = AppBarConfiguration(setOf(R.id.firstFragment,R.id.secondFragment,R.id.thirdFragment),drawerLayout)

        setupActionBarWithNavController(nav_controll,appBarConfiguration)
        navigation_view.setupWithNavController(nav_controll)

    }
    override fun onSupportNavigateUp(): Boolean
    {
        var nav_controll  = findNavController(R.id.fragmentContainerView2)
        return nav_controll.navigateUp(appBarConfiguration) ||  super.onSupportNavigateUp()
    }
}