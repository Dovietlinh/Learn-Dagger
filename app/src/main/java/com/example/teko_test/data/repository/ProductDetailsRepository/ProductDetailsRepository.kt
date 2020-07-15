package com.example.teko_test.data.repository.ProductDetailsRepository

import com.example.teko_test.data.model.request.ProductDetails
import io.reactivex.Observable
import javax.inject.Inject

class ProductDetailsRepository @Inject constructor(
    private val productDetailsDataSource: ProductDetailsDataSource
) {
    fun getDetailsProduct(productSku: Int): Observable<ProductDetails> {
        val getApi: Observable<ProductDetails> =
            productDetailsDataSource.fetchProductDetails(productSku)
        val getLocal: Observable<ProductDetails> =
            productDetailsDataSource.getProductDetailsLocal(productSku)
//        return Observable.mergeDelayError(getLocal, getApi)
        return getApi
    }
}