package com.example.team11_mapd711_project_milestone2.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.team11_mapd711_project_milestone2.Entity.Customer

@Dao
interface CustomerDao {

    @Insert
    suspend fun insertCustomer(customer: Customer)

    @Query("SELECT * FROM customers WHERE email = :email AND password = :password")
    suspend fun getCustomer(email: String, password: String): Customer?

    @Query("SELECT * FROM customers WHERE email = :email")
    suspend fun getCustomerByEmail(email: String): Customer?
}