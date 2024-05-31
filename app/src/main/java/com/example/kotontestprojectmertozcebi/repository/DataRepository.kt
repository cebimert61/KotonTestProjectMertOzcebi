package com.example.kotontestprojectmertozcebi.repository

import android.util.Log
import com.example.kotontestprojectmertozcebi.db.dao.CategoryDao
import com.example.kotontestprojectmertozcebi.db.dao.ProductDao
import com.example.kotontestprojectmertozcebi.db.dao.ImageDao
import com.example.kotontestprojectmertozcebi.model.Category
import com.example.kotontestprojectmertozcebi.model.Product
import com.example.kotontestprojectmertozcebi.network.ApiService
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepository @Inject constructor(
    private val categoryDao: CategoryDao,
    private val productDao: ProductDao,
    private val imageDao: ImageDao,
    private val apiService: ApiService
) {
    suspend fun getCategories(): List<String> {
        return withContext(Dispatchers.IO) {
            try {
                val categories = apiService.getCategories()
                categories.forEach { categoryDao.insert(Category(name = it)) }
                categories
            } catch (e: Exception) {
                Log.e("DataRepository", "Error fetching categories", e)
                emptyList()
            }
        }
    }

    suspend fun getProductsByCategory(categoryName: String): List<Product> {
        return withContext(Dispatchers.IO) {
            try {
                val products = productDao.getProductsByCategory(categoryName)
                if (products.isEmpty()) {
                    val apiProducts = apiService.getProducts().filter { it.category == categoryName }
                    apiProducts.forEach { productDao.insert(it) }
                    apiProducts
                } else {
                    products
                }
            } catch (e: Exception) {
                Log.e("DataRepository", "Error fetching products", e)
                emptyList()
            }
        }
    }

    suspend fun getProductDetails(productId: Int): Product {
        return withContext(Dispatchers.IO) {
            try {
                val product = apiService.getProductDetails(productId)
                productDao.insert(product)
                product
            } catch (e: Exception) {
                Log.e("DataRepository", "Error fetching product details", e)
                Product(0, "", "", 0.0, 0.0, 0.0, 0, "", "", "", emptyList())
            }
        }
    }
}
