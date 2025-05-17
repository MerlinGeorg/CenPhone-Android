package com.example.team11_mapd711_project_milestone2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.SupportMapFragment

class DeliveryActivity : AppCompatActivity() {
    private lateinit var messageTextView: TextView
    private lateinit var locationsListView: ListView
  //  private lateinit var map: View
    private val locations = listOf("Morningside", "Downsview", "Ashtonbee", "Progress", "SAC")
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)

        // Get references to the UI components
        val deliveryOptionsGroup = findViewById<RadioGroup>(R.id.deliveryOptionsGroup)
        messageTextView = findViewById(R.id.messageTextView)
        locationsListView = findViewById(R.id.locationsListView)
       // map = findViewById(R.id.map)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) // This initializes the map

        restoreSelectedDeliveryMode(deliveryOptionsGroup)


        // Handle selection changes in the delivery options radio group
        deliveryOptionsGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbDoorDelivery -> {
                    // Show a message for Door Delivery
                    messageTextView.text = "Your order will be delivered soon."
                    messageTextView.visibility = View.VISIBLE
                    locationsListView.visibility = View.GONE
                    if (mapFragment != null) {
                        supportFragmentManager.beginTransaction().hide(mapFragment).commit()
                    }
                }
                R.id.rbPickUpLocation -> {
                    // Show the list of locations for Pick-Up Location
                    messageTextView.visibility = View.GONE
                    locationsListView.visibility = View.VISIBLE
                    if (mapFragment != null) {
                        supportFragmentManager.beginTransaction().show(mapFragment).commit()
                    }

                    showLocations()
                }
            }
            // Get the selected radio button text and store it in SharedPreferences
            val selectedButton = findViewById<RadioButton>(checkedId)
            val deliveryMode = selectedButton.text.toString()

            val editor = sharedPreferences.edit()
            editor.putString("DELIVERY_MODE", deliveryMode)
            editor.apply()
        }
    }

    // Function to populate the list of pick-up locations
    private fun showLocations() {
        // Create an adapter to display the locations in the ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, locations)
        locationsListView.adapter = adapter

        // Handle item clicks in the ListView
        locationsListView.setOnItemClickListener { _, _, position, _ ->
            val selectedLocation = locations[position]

            // Open MapActivity and pass the selected location
            val intent = Intent(this, MapsActivityNew::class.java)
            intent.putExtra("locationName", selectedLocation)
            startActivity(intent)
        }
    }

    // Restore the previously selected delivery mode
    private fun restoreSelectedDeliveryMode(deliveryOptionsGroup: RadioGroup) {
        val savedMode = sharedPreferences.getString("DELIVERY_MODE", null)
        if (savedMode != null) {
            when (savedMode) {
                getString(R.string.door_delivery) -> deliveryOptionsGroup.check(R.id.rbDoorDelivery)
                getString(R.string.pickup_location) -> deliveryOptionsGroup.check(R.id.rbPickUpLocation)
            }
        }
    }
}