package com.pablo.recuperacion.ui.view

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.annotations.Expose
import com.pablo.recuperacion.core.RetrofitHelper
import com.pablo.recuperacion.databinding.FragmentProductDetailBinding
import com.pablo.recuperacion.imageUrl
import com.pablo.recuperacion.products


class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding
        get() = _binding!!
    private var productId: String? = null
    private var name: String? = null
    private var description: String? = null
    private var stock: Int? = null
    private var regularPrice: Number? = null
    private var discountPrice: Number? = null
    private var available: Boolean? = null
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getString(PRODUCT_ID)
            name = it.getString(NAME)
            description = it.getString(DESCRIPTION)
            stock = it.getInt(STOCK)
            available = it.getBoolean(AVAILABLE)
            imageUrl = it.getString(IMAGEURL)

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
        } ?: productNotFound()
        binding.root.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.btnFav.setOnClickListener {
        //  binding.btnFav.setImageResource(R.drawable.FavFull)
        }
        binding.btnBorrar.setOnClickListener {
            RetrofitHelper.service.deleteProduct(PRODUCT_ID)
        }
    }

    private fun productNotFound() {
        Toast.makeText(context, "Producto no encontrado", Toast.LENGTH_SHORT).show()

        parentFragmentManager.popBackStack()
    }

    companion object {
        private const val PRODUCT_ID = "id"
        private const val IMAGEURL = "imageUrl"
        private const val NAME = "name"
        private const val DESCRIPTION = "description"
        private const val STOCK = "stock"
        private const val REGULAR_PRICE = "regularPrice"
        private const val DISCOUNT_PRICE = "discountPrice"
        private const val AVAILABLE = "available"


        @JvmStatic
        fun newInstance(productId: String, name: String, description: String, stock: Int, regularPrice: Number, discountPrice: Number, available: Boolean, imageUrl: String) =
            ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(PRODUCT_ID, productId)
                    putString(NAME, name)
                    putString(DESCRIPTION, description)
                    putInt(STOCK, stock)
                //    put(REGULAR_PRICE, regularPrice)
                //    put(DISCOUNT_PRICE, discountPrice)
                    putBoolean(AVAILABLE, available)
                    putString(IMAGEURL, imageUrl)
                }
            }
    }
}