package com.example.team11_mapd711_project_milestone2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BrandsActivity : AppCompatActivity() {

    private var selectedBrand:String = ""
    private lateinit var brandImage:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_brands)


        val brandSpinner = findViewById<Spinner>(R.id.spinnerBrands)
        brandImage = findViewById(R.id.brandImage)
        val continueButton = findViewById<Button>(R.id.continueButton)

        //to show corresponding image on selecting different menu options
        brandSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedBrand = parent?.getItemAtPosition(position).toString()
                updateBrandImage(selectedBrand)

                // Check if the selected brand is not "Select"
                if (selectedBrand != "Select") {
                    updateBrandImage(selectedBrand)
                    continueButton.isEnabled = true // Enable button if a brand is selected
                } else {
                    brandImage.setImageDrawable(null) // Clear the image
                    continueButton.isEnabled = false // Keep button disabled if "Select" is chosen
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                continueButton.isEnabled = false
            }
        }


        continueButton.setOnClickListener{

            //to pass selectedBrand value to next screen
            val i = Intent(this@BrandsActivity, BrandModelsActivity::class.java)
            i.putExtra("SELECTED_BRAND", selectedBrand)
            startActivity(i)
        }
    }

    //to update image while switching between menu options
    private fun updateBrandImage(brand: String){

        when(brand){
            "iPhone" -> brandImage.setImageResource(R.drawable.iphone)
            "Samsung" -> brandImage.setImageResource(R.drawable.samsung)
            "Google Pixel" -> brandImage.setImageResource(R.drawable.googlepixel)
        }
    }
}