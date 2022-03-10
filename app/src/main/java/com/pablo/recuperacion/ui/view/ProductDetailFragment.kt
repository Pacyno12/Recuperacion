package com.pablo.recuperacion.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pablo.recuperacion.databinding.FragmentProductDetailBinding
import com.pablo.recuperacion.imageUrl
import com.pablo.recuperacion.products

class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding
        get() = _binding!!
    private var productId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getString(PRODUCT_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = products.firstOrNull { it.id == productId }
        product?.let {
            binding.ivPhoto.imageUrl(it.imageUrl)
            binding.tvName.text = it.name
            binding.tvDesc.text = it.description
            binding.tvStock.text = it.stock.toString()
            binding.tvRegularPrice.text = it.regularPrice.toString()
            binding.tvDiscountPrice.text = it.discountPrice.toString()
            binding.tvAvailable.text = it.available.toString()
        } ?: userNotFound()
        binding.root.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

    }

    private fun userNotFound() {
        Toast.makeText(context, "Producto no encontrado", Toast.LENGTH_SHORT).show()
        parentFragmentManager.popBackStack()
    }

    companion object {
        private const val PRODUCT_ID = "id"

        @JvmStatic
        fun newInstance(productId: String) =
            ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(PRODUCT_ID, productId)
                }
            }
    }
}