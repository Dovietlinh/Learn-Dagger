package com.example.teko_test.data.model.response

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "owner", primaryKeys = ["sku"])
data class Owner(
    @ColumnInfo(name = "sku") val sku: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "images") val images: String = "",
    @ColumnInfo(name = "supplierSalePrice") val supplierSalePrice: Double = 0.0,
    @ColumnInfo(name = "sellPrice") val sellPrice: Double = 0.0,
    @ColumnInfo(name = "description") val description: String = ""
)

@Entity(tableName = "product_attribute")
data class ProductAttribute(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idAttribute") val idAttribute: Int = 0,
    @ColumnInfo(name = "productDetailsEntityID") val productDetailsEntityID: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "value") val value: String
)