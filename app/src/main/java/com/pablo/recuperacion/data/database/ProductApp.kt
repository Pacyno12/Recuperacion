package com.pablo.recuperacion.data.database

import android.app.Application
import androidx.room.Room

class ProductApp: Application() {
val room = Room.databaseBuilder(this, ProductDatabase::class.java, "product").build()
}