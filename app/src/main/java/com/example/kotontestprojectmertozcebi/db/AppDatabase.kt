// AppDatabase.kt
package com.example.kotontestprojectmertozcebi.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotontestprojectmertozcebi.db.dao.CategoryDao
import com.example.kotontestprojectmertozcebi.db.dao.ImageDao
import com.example.kotontestprojectmertozcebi.db.dao.ProductDao
import com.example.kotontestprojectmertozcebi.model.Category
import com.example.kotontestprojectmertozcebi.model.Image
import com.example.kotontestprojectmertozcebi.model.Product

@Database(entities = [Category::class, Product::class, Image::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun imageDao(): ImageDao
}
