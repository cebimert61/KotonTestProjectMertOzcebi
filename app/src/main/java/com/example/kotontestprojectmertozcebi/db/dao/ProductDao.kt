package com.example.kotontestprojectmertozcebi.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotontestprojectmertozcebi.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    @Query("SELECT * FROM Product WHERE category = :categoryName")
    suspend fun getProductsByCategory(categoryName: String): List<Product>
}
