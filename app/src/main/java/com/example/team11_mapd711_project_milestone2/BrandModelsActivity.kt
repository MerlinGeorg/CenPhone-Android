package com.example.team11_mapd711_project_milestone2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BrandModelsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_brand_models)

        val selectedBrand = intent.getStringExtra("SELECTED_BRAND")

        // Retrieve the selected brand from SharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)

        //map that associates each brand with its list of phone models.
        val phoneModelsByBrand = mapOf(
            "iPhone" to listOf(
                PhoneModel("iPhone 14", "$799", R.drawable.iphone14),
                PhoneModel("iPhone 15 Pro", "$999", R.drawable.iphone15_pro),
                PhoneModel("iPhone 16", "$799", R.drawable.iphone16),
                PhoneModel("iPhone 16 Pro", "$1099", R.drawable.iphone16pro)
            ),
            "Samsung" to listOf(
                PhoneModel("Samsung Galaxy S24 FE", "$799", R.drawable.samsunggalaxys24_fe),
                PhoneModel("Samsung Galaxy Z Fold 5", "$1199", R.drawable.samsunggalaxyzfold5),
                PhoneModel("Samsung Galaxy Z Flip 6", "$1199", R.drawable.telussamsunggalaxyzflip6)
            ),
            "Google Pixel" to listOf(
                PhoneModel("Google Pixel 8", "$699", R.drawable.googlepixel8),
                PhoneModel("Google Pixel 9", "$999", R.drawable.googlepixel9),
                PhoneModel("Google Pixel 9 Pro", "$1199", R.drawable.googlepixel9pro)
            )
        )

        val phoneModels = phoneModelsByBrand[selectedBrand] ?: listOf()

        //initialize the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.modelsRecyclerView)

        //set the adapter with the list of models
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PhoneModelAdapter(phoneModels) {
            selectedPhoneModel->
                // Save the selected phone model in SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("SELECTED_PHONE", selectedPhoneModel.name)
                editor.putString("SELECTED_PHONE_PRICE", selectedPhoneModel.price)
                editor.putInt("SELECTED_PHONE_IMAGE", selectedPhoneModel.imageResId); // Assuming image is a drawable resource ID
                editor.apply() // Commit changes

                val intent = Intent(this@BrandModelsActivity, PhoneDetailsActivity::class.java)
                startActivity(intent)
        }
    }
}