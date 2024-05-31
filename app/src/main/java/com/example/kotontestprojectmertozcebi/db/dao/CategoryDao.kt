// CategoryDao.kt
package com.example.kotontestprojectmertozcebi.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotontestprojectmertozcebi.model.Category

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category)

    @Query("SELECT * FROM Category")
    suspend fun getAllCategories(): List<Category>
}
