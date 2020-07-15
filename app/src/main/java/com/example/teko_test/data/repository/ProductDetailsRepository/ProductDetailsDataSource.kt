package com.example.teko_test.data.repository.ProductDetailsRepository

import com.example.teko_test.common.ProductConst.CHANNEL
import com.example.teko_test.common.ProductConst.TERMINAL
import com.example.teko_test.data.api.ApiProduct
import com.example.teko_test.data.mapper.ProductMapper
import com.example.teko_test.data.model.request.ProductDetails
import com.example.teko_test.data.model.response.Owner
import com.example.teko_test.data.model.response.ProductAttribute
import com.example.teko_test.data.model.response.ProductDao
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ProductDetailsDataSource @Inject constructor(
    private val apiProduct: ApiProduct,
    private val productDao: ProductDao
) {
    fun fetchProductDetails(productSku: Int): Observable<ProductDetails> {
        return apiProduct.getProductDetails(productSku, CHANNEL, TERMINAL)
            .doOnNext {
//                productDao.saveProductDetailsOwner(ProductMapper.productRequestToOwner(it))
//                productDao.deleteProductAttribute(it.result.product.sku)
//                productDao.saveProductAttribute(ProductMapper.productRequestToAttribute(it))
            }
            .map {
                return@map it.result.product
            }
    }

    fun getProductDetailsLocal(productSku: Int): Observable<ProductDetails> {
        return Observable.zip(productDao.getProductDetailsOwner(productSku),
            productDao.getAttributeBySku(productSku),
            BiFunction<Owner, List<ProductAttribute>, ProductDetails> { owner, attributes ->
                ProductMapper.productDetailsEntityToProductRequest(owner, attributes)
            })
    }
}