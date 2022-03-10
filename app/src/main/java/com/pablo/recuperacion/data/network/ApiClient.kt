package com.pablo.recuperacion.data.network

import com.pablo.recuperacion.data.model.ProductModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {
    @GET("product")
    fun getAllProducts(): Call<List<ProductModel>>

    @POST()
    fun createProduct(): Call<List<ProductModel>>
}