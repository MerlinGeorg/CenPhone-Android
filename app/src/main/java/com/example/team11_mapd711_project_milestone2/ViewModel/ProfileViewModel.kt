package com.example.team11_mapd711_project_milestone2.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.team11_mapd711_project_milestone2.AppDatabase
import com.example.team11_mapd711_project_milestone2.CustomerRepository
import com.example.team11_mapd711_project_milestone2.Entity.Customer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CustomerRepository
    private val _customer = MutableStateFlow<Customer?>(null)
    val customer: StateFlow<Customer?> = _customer.asStateFlow()

    init {
        val dao = AppDatabase.getDatabase(application).customerDao()
        repository = CustomerRepository(dao)
    }

    fun getCustomer(email: String) {
        viewModelScope.launch {
            val customerData = repository.getCustomerByEmail(email)
            _customer.value = customerData
        }
    }
}
