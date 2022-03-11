package com.pablo.recuperacion.data.network

import android.text.Editable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pablo.recuperacion.data.model.ProductBody
import com.pablo.recuperacion.data.model.ProductModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiClient {
    @GET("product")
    fun getAllProducts(): Call<List<ProductModel>>

    @GET("product/{productId}")
    fun searchProducts(@Query("name")searchText:String): Call<List<ProductModel>>

    @POST("product")
    fun saveProduct(@Body name:String, descrption:String, stock:Int, regularPrice: Number, discountPrice: Number, available: Boolean, imageUrl: String ): Response<ProductModel>

    @DELETE("product/{productId}")
    fun deleteProduct(@Path("productId")productId:String): Call<ProductBody>

}