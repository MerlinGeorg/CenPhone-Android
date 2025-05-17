//package com.example.team11_mapd711_project_milestone2
//
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import Customer
//import kotlinx.coroutines.launch
//import repository.CustomerRepository
//
//class CustomerViewModel(private val repository: CustomerRepository) : ViewModel() {
//
//    fun updateCustomer(email: String, updatedCustomer: Customer, onSuccess: () -> Unit, onError: (String) -> Unit) {
//        viewModelScope.launch {
//            try {
//                // Get the existing customer from the database
//                val existingCustomer = repository.getCustomerByEmail(email)
//                if (existingCustomer != null) {
//                    // Update the customer's details
//                    updatedCustomer.customId = existingCustomer.customId // Retain the ID for updating the same record
//                    repository.insertCustomer(updatedCustomer) // This assumes your DAO's insert method handles updates
//                    onSuccess()
//                } else {
//                    onError("Customer not found")
//                }
//            } catch (e: Exception) {
//                onError(e.message ?: "Unknown error occurred")
//            }
//        }
//    }
//
//    fun registerCustomer(customer: Customer, onSuccess: () -> Unit, onError: (String) -> Unit){
//        viewModelScope.launch {
//            val existingCustomer = repository.getCustomerByEmail(customer.email)
//            if (existingCustomer == null) {
//                repository.insertCustomer(customer)
//                onSuccess()
//            } else {
//                onError("Email already registered!")
//            }
//        }
//    }
//
//    suspend fun getCustomerByEmail(email: String): Customer? {
//        return repository.getCustomerByEmail(email)
//    }
//
//    fun getAllCustomers(onResult: (List<Customer>) -> Unit) {
//        viewModelScope.launch {
//            val customers = repository.getAllCustomers()
//            onResult(customers)
//        }
//    }
//
//    fun deleteAllCustomers(onSuccess: () -> Unit, onError: (String) -> Unit){
//        viewModelScope.launch {
//            try {
//                repository.deleteAllCustomers()
//                onSuccess()
//            } catch (e: Exception){
//                onError(e.message?: "Unknown Error")
//            }
//        }
//    }
//
//    fun loginCustomer(email: String, password: String, onSuccess: (Int, String, String, String,
//                                                                   String, String, String, String, String) -> Unit, onError: (String) -> Unit) {
//        viewModelScope.launch {
//            try {
//                val customer = repository.authenticateCustomer(email, password)
//                print("Customer: "+customer)
//                if (customer != null) {
//                    onSuccess(customer.customId, customer.userName, customer.email, customer.password,
//                        customer.firstName, customer.lastName, customer.address, customer.city, customer.postal)
//                } else {
//                    onError("Invalid email or password")
//                }
//            } catch (e: Exception) {
//                onError(e.message ?: "Unknown error occurred")
//            }
//        }
//    }
//
//}