package com.pablo.recuperacion.ui.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import com.pablo.recuperacion.ProductAdapter
import com.pablo.recuperacion.core.RetrofitHelper
import com.pablo.recuperacion.data.model.ProductModel
import com.pablo.recuperacion.data.model.ProductResponse
import com.pablo.recuperacion.databinding.FragmentProductListBinding
import com.pablo.recuperacion.products
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListFragment : Fragment() {
    private var _binding: FragmentProductListBinding? = null
    private val adapter = ProductAdapter {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack("product")
        }
    }

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun requestData() {
        RetrofitHelper.service.getAllProducts().enqueue(object : Callback<List<ProductModel>> {
            override fun onResponse(
                call: Call<List<ProductModel>>,
                response: Response<List<ProductModel>>
            ) {
                if (response.isSuccessful) {
                    adapter.submitList(response.body())
                } else {
                    Toast.makeText(context, "Error en la respuesta", Toast.LENGTH_SHORT).show()
                    val code = response.code()
                    val message = response.message()
                    Log.e("requestData", "error en la respuesta: $code <> $message")
                }
            }

            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                Toast.makeText(context, "Error en la conexión", Toast.LENGTH_SHORT).show()
                Log.e("requestData", "error", t)
            }
        })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestData()
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = GridLayoutManager(context, 2)
        adapter.submitList(products)

        binding.btnSearch.visibility = View.GONE
        binding.tietSearch.doOnTextChanged() { text, _, _, _ ->
            val query = text.toString().lowercase()
            val usersFiltered = filterProductsByQuery(query)
            adapter.submitList(usersFiltered)
        }
        binding.tietSearch.setOnEditorActionListener { textView, i, keyEvent ->
            hideKeyboard()
            true
        }

    }

    private fun filterProductsByQuery(query: String) = products.filter { product ->
        product.name.lowercase().contains(query.lowercase())
    }

    private fun hideKeyboard() {
        binding.rvProducts.requestFocus()
        with(binding.rvProducts) {
            requestFocus()
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(windowToken, 0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProductListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}