package com.pablo.recuperacion.data.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ProductResponse(
    @SerializedName("product")
    @Expose
    val product: List<ProductModel>
)