package com.example.team11_mapd711_project_milestone2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PhoneDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_phone_details)


        // Retrieve the selected phone model name, image and its price from SharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val modelName = sharedPreferences.getString("SELECTED_PHONE", "Unknown")
        val modelPrice = sharedPreferences.getString("SELECTED_PHONE_PRICE", "Unknown")
        val modelImage = sharedPreferences.getInt("SELECTED_PHONE_IMAGE", 0)

        val modelImageView = findViewById<ImageView>(R.id.selectedPhoneImageView);
        val modelNameTextView = findViewById<TextView>(R.id.selectedPhoneTextView)
        val modelPriceTextView = findViewById<TextView>(R.id.selectedPriceTextView)

        modelNameTextView.text = modelName
        modelPriceTextView.text = modelPrice
        modelImageView.setImageResource(modelImage)

        //Defining reference objects
        val colorSpinner = findViewById<Spinner>(R.id.colorSpinner)
        val storage = findViewById<RadioGroup>(R.id.storageOptionsGroup)
        val checkoutButtonClick = findViewById<Button>(R.id.checkoutButton)

        var selectedStorage = ""
        // get the selected value from radio button
        storage.setOnCheckedChangeListener { group, checkedId ->
            // Get the selected RadioButton based on the checked ID
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            selectedStorage = selectedRadioButton.text.toString()
        }

        checkoutButtonClick.setOnClickListener{

            // get the selected value from spinner
            val color = colorSpinner.selectedItem

            // Save the selected color in SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("SELECTED_COLOR", color.toString())
            editor.putString("SELECTED_PHONE_STORAGE", selectedStorage)
            editor.apply()

           // val intent = Intent(this@PhoneDetailsActivity, CustomerInfoActivity::class.java)
            val intent = Intent(this@PhoneDetailsActivity, DeliveryActivity::class.java)
            startActivity(intent)
        }
    }
}