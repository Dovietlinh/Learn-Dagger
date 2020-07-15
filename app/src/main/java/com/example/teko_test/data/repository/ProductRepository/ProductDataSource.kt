package com.example.teko_test.data.repository.ProductRepository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.teko_test.common.ProductConst.CHANNEL
import com.example.teko_test.common.ProductConst.FIRST_PAGE
import com.example.teko_test.common.ProductConst.TERMINAL
import com.example.teko_test.data.api.ApiProduct
import com.example.teko_test.data.mapper.ProductMapper
import com.example.teko_test.data.model.request.Product
import com.example.teko_test.data.model.response.Owner
import com.example.teko_test.data.model.response.ProductDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ProductDataSource(
    private val apiProduct: ApiProduct,
    private val productDao: ProductDao,
    private val stringSearch: String
) : PageKeyedDataSource<Int, Product>() {

    private var page = FIRST_PAGE

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Product>
    ) {
        getProductRemote(page)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                try {
                    callback.onResult(it, null, page + 1)
                } catch (ex: Exception) {
                }
            }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
        getProductRemote(params.key)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                try {
                    callback.onResult(it, params.key + 1)
                } catch (ex: Exception) {
                }
            }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {

    }

    private fun getProductCaching(page: Int): Observable<List<Product>> {
        val getApi: Observable<List<Product>> = getProductRemote(page)
        val getDb: Observable<List<Product>> = getProductLocal(page)
        return Observable.mergeDelayError(getDb, getApi)
    }

    private fun getProductRemote(page: Int): Observable<List<Product>> {
        return apiProduct.getProductSearch(CHANNEL, "", stringSearch, TERMINAL, page)
            .subscribeOn(Schedulers.io())
            .debounce(500, TimeUnit.MILLISECONDS)
            .doOnNext { productRequest ->
                for (p in productRequest.result.productList) {
//                    val owner = ProductMapper.productMapToOwner(p)
//                    productDao.saveProductDetailsOwner(owner)
                }
            }
            .map {
                return@map it.result.productList
            }
    }

    private fun getProductLocal(page: Int): Observable<List<Product>> {
        return productDao.getProductByPage(stringSearch)
            .subscribeOn(Schedulers.io())
            .map { productEntityList ->
                val productList: MutableList<Product> = mutableListOf()
                for (t in productEntityList) {
                    val product = ProductMapper.productEntityMapToProduct(t)
                    productList.add(product)
                }
                return@map productList
            }
    }
}

class ProductDataSourceFactory(
    private val apiProduct: ApiProduct,
    private val productDao: ProductDao,
    private val stringSearch: String
) : DataSource.Factory<Int, Product>() {
    private val productLiveDataSource = MutableLiveData<ProductDataSource>()
    override fun create(): DataSource<Int, Product> {
        val productDataSource = ProductDataSource(apiProduct, productDao, stringSearch)
        productLiveDataSource.postValue(productDataSource)
        return productDataSource
    }

}