package com.example.team11_mapd711_project_milestone2

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.team11_mapd711_project_milestone2.databinding.ActivityMapsNewBinding
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient

class MapsActivityNew : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var placesClient: PlacesClient

    private val locationDetails = mapOf(
        "Morningside" to "ChIJZUu7PXHa1IkRYf17XwT9arw",
        "Downsview" to "ChIJzTIO9_oxK4gRExOniHr9EgE",
        "Ashtonbee" to "ChIJYeo18-HN1IkRH0H7iJLxWq8",
        "Progress" to "ChIJjwfZH_LQ1IkRGCvNwJL-40Y",
        "SAC" to "ChIJMZxuZJDM1IkRvurXx10RKAg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_maps_new)

        // Retrieve the selected location name from the Intent

        Places.initialize(applicationContext, getString(R.string.places_api_key))
        placesClient = Places.createClient(this)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Show the loading indicator when activity starts
        val loadingLayout = findViewById<LinearLayout>(R.id.loadingLayout)
        loadingLayout.visibility = View.VISIBLE
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Hide the loading indicator once the map is ready
        val loadingLayout = findViewById<LinearLayout>(R.id.loadingLayout)
        loadingLayout.visibility = View.GONE

        // Get the location name and find its coordinates
        val locationName = intent.getStringExtra("locationName") ?: return


        val btnStandard = findViewById<Button>(R.id.btnStandardView)
        val btnSatellite = findViewById<Button>(R.id.btnSatelliteView)
        val btnHybrid = findViewById<Button>(R.id.btnHybridView)
        val btnSaveLocation = findViewById<Button>(R.id.btnSaveLocation)

        // Standard View
        btnStandard.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            Toast.makeText(this, "Standard View", Toast.LENGTH_SHORT).show()
        }

        // Satellite View
        btnSatellite.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            Toast.makeText(this, "Satellite View", Toast.LENGTH_SHORT).show()
        }

        // Hybrid View
        btnHybrid.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            Toast.makeText(this, "Hybrid View", Toast.LENGTH_SHORT).show()
        }

         val placeId = locationDetails[locationName] ?: return

         fetchPlaceDetails(placeId)

        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)

        btnSaveLocation.setOnClickListener {

            val editor = sharedPreferences.edit()
            editor.putString("PICKUP_LOCATION", locationName)
            editor.apply()

            val intent = Intent(this, CustomerInfoActivity::class.java)
           // intent.putExtra("selectedLocation", locationName)
            startActivity(intent)
        }
    }

    private fun fetchPlaceDetails (placeId: String) {

        val placeFields = listOf(
            Place.Field.NAME,
            Place.Field.ADDRESS,
            Place.Field.PHONE_NUMBER,
            Place.Field.WEBSITE_URI,
            Place.Field.OPENING_HOURS,
            Place.Field.LAT_LNG
        )

        val request = FetchPlaceRequest.builder(placeId, placeFields).build()

        placesClient.fetchPlace(request).addOnSuccessListener { response ->
            val place = response.place

            // Add a marker for the place
            place.latLng?.let { latLng ->
                mMap.addMarker(MarkerOptions().position(latLng).title(place.name))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            }

            // Display the details in a dialog or bottom sheet
            showPlaceDetailsDialog(place)
        }.addOnFailureListener { exception ->
            Log.e("MapActivity", "Place not found: ${exception.message}")
        }
    }

    private fun showPlaceDetailsDialog(place: Place) {
        // Inflate the custom layout
        val dialogView = layoutInflater.inflate(R.layout.place_details_dialog, null)


        val name = dialogView.findViewById<TextView>(R.id.placeName)
        val address = dialogView.findViewById<TextView>(R.id.placeAddress)
        val phone = dialogView.findViewById<TextView>(R.id.placePhone)
        val website = dialogView.findViewById<TextView>(R.id.placeWebsite)
        val hours = dialogView.findViewById<TextView>(R.id.placeHours)

        // Populate the fields with data
        name.text = place.name ?: "N/A"
        address.text = "Address: ${place.address ?: "N/A"}"
        phone.text = "Phone: ${place.phoneNumber ?: "N/A"}"
        website.text = "Website: ${place.websiteUri ?: "N/A"}"
        hours.text = if (place.openingHours?.weekdayText != null) {
            "Hours:\n${place.openingHours?.weekdayText?.joinToString("\n")}"
        } else {
            "Hours: N/A"
        }

        // Build and show the dialog
        AlertDialog.Builder(this)
            .setTitle("Location Details")
            .setView(dialogView)
            .setPositiveButton("OK", null)
            .show()
    }
}