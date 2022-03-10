package com.pablo.recuperacion.data.network

import com.pablo.recuperacion.data.model.ProductBody
import com.pablo.recuperacion.data.model.ProductModel
import retrofit2.Call
import retrofit2.http.*

interface ApiClient {
    @GET("product")
    fun getAllProducts(): Call<List<ProductModel>>

    @GET("product/{productId}")
    fun searchProducts(@Query("name")searchText:String): Call<List<ProductModel>>

    @POST("product")
    fun saveProduct(@Body params: ProductModel): Call<ProductBody>

    @DELETE("product/{productId}")
    fun deleteProduct(@Path("productId")productId:String): Call<ProductBody>

}