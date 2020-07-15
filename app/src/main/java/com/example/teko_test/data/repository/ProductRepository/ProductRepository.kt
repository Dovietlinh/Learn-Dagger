package com.example.teko_test.data.repository.ProductRepository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.teko_test.common.ProductConst.PAGE_SIZE
import com.example.teko_test.data.api.ApiProduct
import com.example.teko_test.data.model.request.Product
import com.example.teko_test.data.model.response.ProductDao
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiProduct: ApiProduct,
    private val productDao: ProductDao
) {
    private lateinit var productPagedList: LiveData<PagedList<Product>>

    fun fetchLiveProductPagedList(stringSearch: String): LiveData<PagedList<Product>> {
        val productDataSourceFactory =
            ProductDataSourceFactory(apiProduct, productDao, stringSearch)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .build()
        productPagedList = LivePagedListBuilder(productDataSourceFactory, config).build()
        return productPagedList
    }
}