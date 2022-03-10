package com.pablo.recuperacion.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pablo.recuperacion.databinding.FragmentProductAddBinding
import com.pablo.recuperacion.databinding.FragmentProductDetailBinding


class ProductAddFragment : Fragment() {
    private var _binding: FragmentProductAddBinding? = null
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
        _binding = FragmentProductAddBinding.inflate(inflater, container, false)
        return binding.root
    }
}
