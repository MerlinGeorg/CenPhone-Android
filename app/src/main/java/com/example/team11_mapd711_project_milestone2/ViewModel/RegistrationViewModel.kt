package com.example.team11_mapd711_project_milestone2.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.team11_mapd711_project_milestone2.AppDatabase
import com.example.team11_mapd711_project_milestone2.CustomerRepository
import com.example.team11_mapd711_project_milestone2.Entity.Customer
import kotlinx.coroutines.launch

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CustomerRepository

    private val _registrationResult = MutableLiveData<Boolean>()

    val registrationResult: LiveData<Boolean> get() = _registrationResult

    init {
        val dao = AppDatabase.getDatabase(application).customerDao()
        repository = CustomerRepository(dao)
    }

    fun registerCustomer(customer: Customer) {
        viewModelScope.launch {
            try {
                repository.registerCustomer(customer)
                _registrationResult.value = true // Registration successful
            } catch (e: Exception) {
                _registrationResult.value = false // Registration failed
            }
        }
    }
}