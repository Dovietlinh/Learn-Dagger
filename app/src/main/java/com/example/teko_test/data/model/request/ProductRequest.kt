package com.example.teko_test.data.model.request

import com.google.gson.annotations.SerializedName

data class ProductRequest(
    @SerializedName("result")
    val result: Result,
    @SerializedName("extra")
    val extra: Extra
)

data class Result(
    @SerializedName("products")
    val productList: List<Product>
)

data class Product(
    @SerializedName("sku")
    val sku: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("price")
    val price: Price
)

data class Extra(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pageSize")
    val pageSize: Int,
    @SerializedName("totalItems")
    val totalItems: Int
)

data class Image(
    @SerializedName("url")
    val ulr: String
)

data class Price(
    @SerializedName("supplierSalePrice")
    val supplierSalePrice: Double,
    @SerializedName("sellPrice")
    val sellPrice: Double
)