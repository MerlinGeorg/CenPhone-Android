package com.example.team11_mapd711_project_milestone2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CustomerInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_customer_info)

        //Defining reference objects
        val name = findViewById<EditText>(R.id.name)
        val street = findViewById<EditText>(R.id.street)
        val city = findViewById<EditText>(R.id.city)
        val postal_code = findViewById<EditText>(R.id.postal_code)
        val phone = findViewById<EditText>(R.id.phone)
        val email = findViewById<EditText>(R.id.email)
        val saveButton = findViewById<Button>(R.id.saveCustomerInfoButton)

        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)

        saveButton.setOnClickListener {

            // Save the selected color in SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("CUSTOMER_NAME", name.text.toString())
            editor.putString("STREET", street.text.toString())
            editor.putString("CITY", city.text.toString())
            editor.putString("POSTAL_CODE", postal_code.text.toString())
            editor.putString("EMAIL", email.text.toString())
            editor.putString("PHONE", phone.text.toString())
            editor.apply()

            val intent = Intent(this@CustomerInfoActivity, PaymentActivity::class.java)
            startActivity(intent)
        }
    }
}