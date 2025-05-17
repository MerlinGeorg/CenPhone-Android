package com.example.team11_mapd711_project_milestone2

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.team11_mapd711_project_milestone2.ViewModel.ProfileViewModel
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        Log.d("ProfileActivity", "Content view set to: ${R.layout.activity_profile}")

        val rootLayout = findViewById<ConstraintLayout>(R.id.profile_root_layout)
        Log.d("ProfileActivity", "Root layout visibility: ${rootLayout.visibility}")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.profile_root_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val profileSection = findViewById<LinearLayout>(R.id.profileSection)
        Log.d("ProfileActivity", "Profile section visibility: ${profileSection.visibility}")

        val textViewFirstName: TextView = findViewById(R.id.firstName)
        val textViewLastName: TextView = findViewById(R.id.lastName)
        val textViewEmail: TextView = findViewById(R.id.textViewEmail)
        val textViewAddress: TextView = findViewById(R.id.textViewAddress)
        val textViewCity: TextView = findViewById(R.id.textViewCity)
        val textViewPostalCode: TextView = findViewById(R.id.postalCode)
        val textViewCountry: TextView = findViewById(R.id.country)

        // Get the email from Intent
        val email = intent.getStringExtra("EMAIL") ?: ""
        Log.d("ProfileActivity", "Received email: $email")

        if (email.isEmpty()) {
            Toast.makeText(this, "Email not found!", Toast.LENGTH_SHORT).show()
            return
        }

        // Fetch customer data using email
        viewModel.getCustomer(email)

        // Observe the customer data and bind it to the UI
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.customer.collect { customer ->
                    if (customer != null) {
                        textViewFirstName.text = customer.firstName
                        textViewLastName.text = customer.lastName
                        textViewEmail.text = customer.email
                        textViewAddress.text = customer.address
                        textViewCity.text = customer.city
                        textViewPostalCode.text = customer.postalCode
                        textViewCountry.text = customer.country

                        Log.d("ProfileActivity", "UI updated with customer data: $customer")
                        Toast.makeText(this@ProfileActivity, "Profile updated: ${customer.firstName}", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.e("ProfileActivity", "Customer is null")
                        Toast.makeText(this@ProfileActivity, "Customer not found!", Toast.LENGTH_SHORT).show()
                    }

                    Log.d("ProfileActivity", "FirstName visibility: ${textViewFirstName.visibility}")
                    Log.d("ProfileActivity", "FirstName text: ${textViewFirstName.text}")

                    rootLayout.post {
                        rootLayout.invalidate()
                        rootLayout.requestLayout()
                    }
                }
            }
        }

        // Set up click listeners for buttons
        findViewById<LinearLayout>(R.id.editProfileButton).setOnClickListener {
            // TODO: Implement edit profile functionality
            Toast.makeText(this, "Edit Profile clicked", Toast.LENGTH_SHORT).show()
        }

        findViewById<LinearLayout>(R.id.ordersButton).setOnClickListener {
            // TODO: Implement view orders functionality
            Toast.makeText(this, "Orders clicked", Toast.LENGTH_SHORT).show()
        }

        findViewById<LinearLayout>(R.id.logoutButton).setOnClickListener {
            // TODO: Implement logout functionality
            Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show()
            // Example:
            // startActivity(Intent(this, LoginActivity::class.java))
            // finish()
        }
    }
}
