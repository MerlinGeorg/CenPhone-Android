package com.example.team11_mapd711_project_milestone2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


//Student ID: 301475560
//Student Name: Merlin George
//Date: 07/11/2024

class MainActivity : AppCompatActivity() {

    private var userEmail:String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve userEmail from the Intent
        userEmail = intent.getStringExtra("EMAIL")

        //event listener for profile icon click
        val profileIcon = findViewById<FrameLayout>(R.id.profileIcon)
        profileIcon.setOnClickListener{
            openProfileActivity()

        }
    }

    //event listener for the Order Now button
    fun orderButtonClick(view: View){

        val intent = Intent(this, BrandsActivity::class.java)
        startActivity(intent)
    }

    private fun openProfileActivity() {
        val i = Intent(this@MainActivity, ProfileActivity::class.java)
        i.putExtra("EMAIL", userEmail)
        startActivity(i)
    }

}