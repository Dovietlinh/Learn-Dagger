package com.example.teko_test.data.model.response

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "db_products", primaryKeys = ["sku"])
data class ProductEntity(
    @ColumnInfo(name = "sku") val sku: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "images") val image: String = "",
    @ColumnInfo(name = "supplierSalePrice") val supplierSalePrice: Double = 0.0,
    @ColumnInfo(name = "sellPrice") val sellPrice: Double = 0.0
)