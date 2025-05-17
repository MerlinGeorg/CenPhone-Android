package com.example.team11_mapd711_project_milestone2

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.team11_mapd711_project_milestone2.Entity.Customer
import com.example.team11_mapd711_project_milestone2.ViewModel.RegistrationViewModel
import com.example.team11_mapd711_project_milestone2.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    private lateinit var viewModel: RegistrationViewModel

    //val database = (application as MyApp).database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Make the "Login here" part of the text clickable
        val loginLink = binding.textViewLoginLink
        val spannableString = SpannableString(loginLink.text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Navigate to LoginActivity
                val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        // Set the clickable span for "Login here"
        val loginStartIndex = loginLink.text.indexOf("Login here")
        spannableString.setSpan(
            clickableSpan,
            loginStartIndex,
            loginLink.text.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        loginLink.text = spannableString
        loginLink.movementMethod = android.text.method.LinkMovementMethod.getInstance()



        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

        binding.buttonRegister.setOnClickListener { registerUser() }

        viewModel.registrationResult.observe(this) { isSuccessful ->
            if (isSuccessful) {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun registerUser() {
        val customer = Customer(
            firstName = binding.editTextFirstName.text.toString(),
            lastName = binding.editTextLastName.text.toString(),
            email = binding.editTextEmail.text.toString(),
            password = binding.editTextPassword.text.toString(),
            address = binding.editTextAddress.text.toString(),
            city = binding.editTextCity.text.toString(),
            postalCode = binding.editTextPostalCode.text.toString(),
            country = binding.editTextCountry.text.toString()
        )

        viewModel.registerCustomer(customer)
    }
}