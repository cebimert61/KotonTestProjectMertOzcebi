package com.example.kotontestprojectmertozcebi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotontestprojectmertozcebi.databinding.ActivityProductListBinding
import com.example.kotontestprojectmertozcebi.ui.Adapter.ProductAdapter
import com.example.kotontestprojectmertozcebi.viewmodel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductListBinding
    private lateinit var viewModel: DataViewModel
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryName = intent.getStringExtra("CATEGORY_NAME") ?: ""

        // ViewModel'i oluşturmak için ViewModelProvider.Factory kullanın
        viewModel = ViewModelProvider(this, SavedStateViewModelFactory(application, this)).get(DataViewModel::class.java)

        setupRecyclerView()
        observeViewModel()

        viewModel.fetchProductsByCategory(categoryName)
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter { product ->
            val dialog = ProductDetailDialogFragment.newInstance(product.id)
            dialog.show(supportFragmentManager, "ProductDetailDialog")
        }
        binding.recyclerViewProducts.apply {
            layoutManager = LinearLayoutManager(this@ProductListActivity)
            adapter = productAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.products.observe(this) { products ->
            if (products != null) {
                productAdapter.submitList(products)
            }
        }
    }
}
