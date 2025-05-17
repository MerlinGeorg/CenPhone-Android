package com.example.team11_mapd711_project_milestone2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_landing)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    //event listener for the Register button
    fun registerButtonClick(view: View){

        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }

    //event listener for the Login button
    fun loginButtonClick(view: View){

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}