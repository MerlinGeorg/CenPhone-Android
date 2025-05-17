package com.example.team11_mapd711_project_milestone2.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class Customer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val address: String,
    val city: String,
    val postalCode: String,
    val country: String
)