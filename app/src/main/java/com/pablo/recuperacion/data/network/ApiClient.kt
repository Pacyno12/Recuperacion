package com.pablo.recuperacion.data.network

import com.pablo.recuperacion.data.model.ProductModel
import com.pablo.recuperacion.data.model.ProductResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {
    @GET("product")
    fun getAllProducts(): Call<List<ProductModel>>

    @POST("/createProduct")
    fun createProduct():Response<ProductResponse>
}