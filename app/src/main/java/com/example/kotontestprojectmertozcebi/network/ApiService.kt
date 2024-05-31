package com.example.kotontestprojectmertozcebi.network

import com.example.kotontestprojectmertozcebi.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products/categories")
    suspend fun getCategories(): List<String>

    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id") productId: Int): Product
}
