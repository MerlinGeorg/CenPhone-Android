package com.example.team11_mapd711_project_milestone2.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey val id: Int,
    val brand: String,
    val model: String,
    val price: String,
    val imageResId: Int
    )