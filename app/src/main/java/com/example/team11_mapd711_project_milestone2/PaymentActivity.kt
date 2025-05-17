package com.example.team11_mapd711_project_milestone2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)

        val cardNumber = findViewById<TextView>(R.id.cardNumber)

        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()


        val saveButton = findViewById<Button>(R.id.savePaymentDetails)
        saveButton.setOnClickListener {

            // Save the card number
            editor.putString("CARD_NUMBER", cardNumber.toString())
            editor.apply()

            val intent = Intent(this@PaymentActivity, OrderDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}