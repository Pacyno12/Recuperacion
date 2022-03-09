package com.pablo.recuperacion

import android.app.Application
import androidx.fragment.app.Fragment
import com.pablo.recuperacion.data.model.ProductModel

class App : Application() {
    val productModels: MutableList<ProductModel> = mutableListOf()

    override fun onCreate() {
        super.onCreate()
    }
}

private val Fragment.app: App
    get() = this.activity?.application as App

val Fragment.products
    get() = app.productModels