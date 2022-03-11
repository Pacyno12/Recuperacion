package com.pablo.recuperacion.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pablo.recuperacion.data.database.ProductApp
import com.pablo.recuperacion.data.database.dao.ProductDao
import com.pablo.recuperacion.databinding.FragmentProductFavBinding

class ProductFavFragment : Fragment() {
    private var _binding: FragmentProductFavBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductFavBinding.inflate(inflater, container, false)
        return binding.root

    }
    private suspend fun requestData(){
        val app = _binding as ProductApp
        app.room.getProductDao().getAllProducts()
    }
}
