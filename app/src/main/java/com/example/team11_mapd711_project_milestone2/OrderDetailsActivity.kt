package com.example.team11_mapd711_project_milestone2

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order_details)

        //Defining reference objects
        val phoneNameTextView = findViewById<TextView>(R.id.phone_name)
        val storageTextView = findViewById<TextView>(R.id.storage)
        val colorTextView = findViewById<TextView>(R.id.color)
        val priceTextView = findViewById<TextView>(R.id.price)
        val customerNameTextView = findViewById<TextView>(R.id.customer_name)
        val addressTextView = findViewById<TextView>(R.id.address)
        val deliveryModeTextView = findViewById<TextView>(R.id.deliveryMode)
        val pickupLocationTextView = findViewById<TextView>(R.id.pickupLocation)
        val pickupLocationLabel = findViewById<TextView>(R.id.pickupLocationLabel)

        //get data from shared preferences & assign to variables
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val modelName = sharedPreferences.getString("SELECTED_PHONE", "Unknown")
        val modelPrice = sharedPreferences.getString("SELECTED_PHONE_PRICE", "Unknown")
        val storage = sharedPreferences.getString("SELECTED_PHONE_STORAGE", "Unknown")
        val color = sharedPreferences.getString("SELECTED_COLOR", "Unknown")
        val customerName = sharedPreferences.getString("CUSTOMER_NAME", "Unknown")
        val street = sharedPreferences.getString("STREET", "")
        val city = sharedPreferences.getString("CITY", "")
        val postalCode = sharedPreferences.getString("POSTAL_CODE", "")
        val deliveryMode = sharedPreferences.getString("DELIVERY_MODE", "")
        val pickupLocation = sharedPreferences.getString("PICKUP_LOCATION", "")

        val address = buildString {
            if (!street.isNullOrEmpty()) append(street)
            if (!city.isNullOrEmpty()) append(", $city")
            if (!postalCode.isNullOrEmpty()) append(", $postalCode")
        }

        phoneNameTextView.text = modelName
        priceTextView.text = modelPrice
        storageTextView.text = storage
        colorTextView.text = color
        customerNameTextView.text = customerName
        addressTextView.text = address
        deliveryModeTextView.text = deliveryMode
        if(deliveryMode == "Pick-Up Location") {
            pickupLocationTextView.text = pickupLocation
            pickupLocationLabel.visibility = View.VISIBLE
        } else {
            pickupLocationLabel.visibility = View.GONE
        }

    }
}