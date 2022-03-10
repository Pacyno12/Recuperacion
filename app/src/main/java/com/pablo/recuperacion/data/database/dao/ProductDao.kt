package com.pablo.recuperacion.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pablo.recuperacion.data.database.entities.ProductEntity
import com.pablo.recuperacion.data.model.ProductModel

@Dao
interface ProductDao {
    @Query("SELECT * FROM product_table")
    suspend fun getAllProducts():List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun inserProducts(products:List<ProductModel>)
}