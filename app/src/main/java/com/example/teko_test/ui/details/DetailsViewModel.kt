package com.example.teko_test.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teko_test.data.model.request.ProductDetails
import com.example.teko_test.data.repository.ProductDetailsRepository.ProductDetailsRepository
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val productDetailsRepository: ProductDetailsRepository) :
    ViewModel() {

    val productDetailsLiveData = MutableLiveData<ProductDetails>()
    fun getProductDetails(productSku: Int): LiveData<ProductDetails> {
        productDetailsRepository.getDetailsProduct(productSku)
            .subscribeOn(Schedulers.io())
            .subscribe {
                productDetailsLiveData.postValue(it)
            }
        return productDetailsLiveData
    }
}