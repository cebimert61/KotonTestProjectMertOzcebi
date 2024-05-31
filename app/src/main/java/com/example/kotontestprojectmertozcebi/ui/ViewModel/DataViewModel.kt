package com.example.kotontestprojectmertozcebi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotontestprojectmertozcebi.model.Product
import com.example.kotontestprojectmertozcebi.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>> = _categories

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val categories = repository.getCategories()
                _categories.postValue(categories)
            } catch (e: Exception) {
                _categories.postValue(null)
                e.printStackTrace()
            }
        }
    }

    fun fetchProductsByCategory(categoryName: String) {  // Metod adı fetchProductsByCategory olmalı
        viewModelScope.launch {
            try {
                val products = repository.getProductsByCategory(categoryName)
                _products.postValue(products)
            } catch (e: Exception) {
                _products.postValue(null)
                e.printStackTrace()
            }
        }
    }

    fun fetchProductDetails(productId: Int) {
        viewModelScope.launch {
            try {
                val product = repository.getProductDetails(productId)
                _product.postValue(product)
            } catch (e: Exception) {
                _product.postValue(null)
                e.printStackTrace()
            }
        }
    }
}
