package com.pablo.recuperacion.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "product_table")
data class ProductEntity (
    @ColumnInfo(name = "id")val id: String,
    @ColumnInfo(name = "name")val name: String,
    @ColumnInfo(name = "description")val description: String,
    @ColumnInfo(name = "stock")val stock: Int,
    @ColumnInfo(name = "regularPrice")val regularPrice: Number,
    @ColumnInfo(name = "discountPrice")val discountPrice: Number,
    @ColumnInfo(name = "available")val available: Boolean,
    @ColumnInfo(name = "imageUrl")val imageUrl: String,
)