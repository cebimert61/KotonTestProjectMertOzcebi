// Category.kt
package com.example.kotontestprojectmertozcebi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey val name: String
)
