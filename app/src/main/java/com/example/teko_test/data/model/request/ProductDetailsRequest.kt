package com.example.teko_test.data.model.request

import com.google.gson.annotations.SerializedName

data class ProductDetailsRequest(
    @SerializedName("result")
    val result: ResultRequest
)

data class ResultRequest(
    @SerializedName("product")
    val product: ProductDetails
)

data class ProductDetails(
    @SerializedName("sku")
    val sku: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("price")
    val price: Price,
    @SerializedName("seoInfo")
    val seoInfo: SeoInfo,
    @SerializedName("attributeGroups")
    val attributeGroups: List<AttributeGroups>
)

data class SeoInfo(
    @SerializedName("description")
    val description: String
)

data class AttributeGroups(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String
)