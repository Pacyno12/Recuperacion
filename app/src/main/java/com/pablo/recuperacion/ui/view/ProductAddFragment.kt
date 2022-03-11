package com.pablo.recuperacion.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.pablo.recuperacion.ProductAdapter
import com.pablo.recuperacion.R
import com.pablo.recuperacion.core.RetrofitHelper
import com.pablo.recuperacion.databinding.FragmentProductAddBinding
import retrofit2.Response


class ProductAddFragment : Fragment() {
    private var _binding: FragmentProductAddBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = ProductAdapter {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack("product")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    private fun requestData() {
        val nombre = binding.txtNombre.text
        val descripcion = binding.txtDescripcion.text
        val stock = binding.txtStock.text
        val regularPrice = binding.txtRegularPrice.text
        val discountPrice = binding.txtDiscountPrice.text
        val available = binding.chkAvailable.isActivated
    //    RetrofitHelper.service.saveProduct("$nombre", "$descripcion", "$stock", "$regularPrice", "$discountPrice", "$available")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductAddBinding.inflate(inflater, container, false)
        return binding.root
    }
}
