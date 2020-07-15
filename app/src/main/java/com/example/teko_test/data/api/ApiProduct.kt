package com.example.teko_test.data.api

import com.example.teko_test.common.ProductConst.PAGE_SIZE
import com.example.teko_test.data.model.request.ProductDetailsRequest
import com.example.teko_test.data.model.request.ProductRequest
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiProduct {
    @GET("search/")
    fun getProductSearch(
        @Query("channel") channel: String,
        @Query("visitorId") visitorId: String,
        @Query("q") query: String,
        @Query("terminal") terminal: String,
        @Query("_page") page: Int,
        @Query("_limit") limit: Int = PAGE_SIZE
    ): Observable<ProductRequest>

    @GET("products/{product_sku}")
    fun getProductDetails(
        @Path("product_sku") sku: Int,
        @Query("channel") channel: String,
        @Query("terminal") terminal: String
    ): Observable<ProductDetailsRequest>
}