// ImageDao.kt
package com.example.kotontestprojectmertozcebi.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotontestprojectmertozcebi.model.Image

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(image: Image)

    @Query("SELECT * FROM Image WHERE productId = :productId")
    suspend fun getImagesByProductId(productId: Int): List<Image>
}
