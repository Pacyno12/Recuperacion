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
    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getString(USER_ID)
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
        val product = products.firstOrNull { it.id == userId }
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
        private const val USER_ID = "user_id"

        @JvmStatic
        fun newInstance(userId: String) =
            ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(USER_ID, userId)
                }
            }
    }
}