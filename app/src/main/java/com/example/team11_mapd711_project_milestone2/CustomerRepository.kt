package com.example.team11_mapd711_project_milestone2

import com.example.team11_mapd711_project_milestone2.Dao.CustomerDao
import com.example.team11_mapd711_project_milestone2.Entity.Customer

class CustomerRepository(private val customerDao: CustomerDao) {

    suspend fun registerCustomer(customer: Customer) {
        customerDao.insertCustomer(customer)
    }

    suspend fun loginCustomer(email: String, password: String): Customer? {
        return customerDao.getCustomer(email, password)
    }

    suspend fun getCustomerByEmail(email: String): Customer? {
        return customerDao.getCustomerByEmail(email)
    }
}