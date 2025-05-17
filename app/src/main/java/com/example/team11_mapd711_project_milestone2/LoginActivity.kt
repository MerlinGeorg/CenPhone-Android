package com.example.team11_mapd711_project_milestone2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.team11_mapd711_project_milestone2.ViewModel.LoginViewModel
import com.example.team11_mapd711_project_milestone2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private var userEmail: String? = null // Variable to store the entered email

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if(email.isNotEmpty() && password.isNotEmpty()){
                userEmail =email
                viewModel.loginCustomer(email, password)
            } else {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            }

        }

        viewModel.loginResult.observe(this) { isSuccessful ->
            if (isSuccessful) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("EMAIL", userEmail)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up the "Register here" link click listener
        binding.textViewRegisterLink.setOnClickListener {
            // Navigate to RegistrationActivity when "Register here" is clicked
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }
}