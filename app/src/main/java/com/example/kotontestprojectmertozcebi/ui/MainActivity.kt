package com.example.kotontestprojectmertozcebi.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotontestprojectmertozcebi.databinding.ActivityMainBinding
import com.example.kotontestprojectmertozcebi.ui.Adapter.CategoryAdapter
import com.example.kotontestprojectmertozcebi.viewmodel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DataViewModel
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ViewModel'i oluşturmak için ViewModelProvider.Factory kullanın
        viewModel = ViewModelProvider(this, SavedStateViewModelFactory(application, this)).get(DataViewModel::class.java)

        setupRecyclerView()
        observeViewModel()

        // Veri çekme işlemini başlatıyoruz
        viewModel.fetchCategories()
    }

    private fun setupRecyclerView() {
        categoryAdapter = CategoryAdapter { category ->
            Toast.makeText(this, "Selected category: $category", Toast.LENGTH_SHORT).show()
            // Burada kategoriye tıklandığında yapılacak işlemleri ekleyebilirsiniz
        }
        binding.recyclerViewCategories.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = categoryAdapter
        }
    }

    private fun observeViewModel() {
        try {
            viewModel.categories.observe(this) { categories ->
                if (categories != null) {
                    categoryAdapter.submitList(categories)
                } else {
                    Toast.makeText(this, "Failed to load categories", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
