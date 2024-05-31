// Image.kt
package com.example.kotontestprojectmertozcebi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Image(
    @PrimaryKey val id: Int,
    val productId: Int,
    val url: String
)
