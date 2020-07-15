package com.example.teko_test.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.teko_test.data.repository.ProductRepository.ProductRepository
import com.example.teko_test.data.model.request.Product
import com.example.teko_test.data.model.request.ProductDetails
import com.example.teko_test.data.repository.ProductDetailsRepository.ProductDetailsRepository
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val productRepository: ProductRepository,private val productDetailsRepository: ProductDetailsRepository) : ViewModel() {
    fun productPagedList(searchString: String): LiveData<PagedList<Product>> {
        return productRepository.fetchLiveProductPagedList(searchString)
    }
}