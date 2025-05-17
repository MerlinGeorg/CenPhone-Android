package com.example.team11_mapd711_project_milestone2.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.team11_mapd711_project_milestone2.AppDatabase
import com.example.team11_mapd711_project_milestone2.CustomerRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CustomerRepository
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    init {
        val dao = AppDatabase.getDatabase(application).customerDao()
        repository = CustomerRepository(dao)
    }

    fun loginCustomer(email: String, password: String) {
        viewModelScope.launch {
            val customer = repository.loginCustomer(email, password)
            _loginResult.value = customer != null
        }
    }
}