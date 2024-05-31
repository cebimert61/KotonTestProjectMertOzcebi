package com.example.kotontestprojectmertozcebi.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotontestprojectmertozcebi.databinding.DialogProductDetailBinding
import com.example.kotontestprojectmertozcebi.ui.adapter.ProductImageAdapter

import com.example.kotontestprojectmertozcebi.viewmodel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailDialogFragment : DialogFragment() {

    private lateinit var binding: DialogProductDetailBinding
    private lateinit var viewModel: DataViewModel
    private lateinit var imageAdapter: ProductImageAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogProductDetailBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)

        val productId = arguments?.getInt("PRODUCT_ID") ?: 0

        setupRecyclerView()
        observeViewModel()

        viewModel.fetchProductDetails(productId)

        return Dialog(requireContext()).apply {
            setContentView(binding.root)
        }
    }

    private fun setupRecyclerView() {
        imageAdapter = ProductImageAdapter()
        binding.recyclerViewProductImages.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewProductImages.adapter = imageAdapter
    }

    private fun observeViewModel() {
        viewModel.product.observe(this) { product ->
            binding.textViewProductDetailName.text = product.title
            binding.textViewProductDetailPrice.text = product.price.toString()
            binding.textViewProductDetailDescription.text = product.description
            imageAdapter.submitList(product.images)
        }
    }

    companion object {
        fun newInstance(productId: Int): ProductDetailDialogFragment {
            return ProductDetailDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt("PRODUCT_ID", productId)
                }
            }
        }
    }
}
