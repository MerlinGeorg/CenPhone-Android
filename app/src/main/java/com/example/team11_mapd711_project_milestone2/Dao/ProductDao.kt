package com.example.team11_mapd711_project_milestone2.Dao

import androidx.room.*
import com.example.team11_mapd711_project_milestone2.Entity.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAllProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: Product)

    @Query("SELECT * FROM products WHERE brand = :brand")
    fun getProductsByBrand(brand: String): List<Product>
}