package com.example.teko_test.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.teko_test.R
import com.example.teko_test.common.ProductConst
import com.example.teko_test.ui.ViewModelFragment
import com.example.teko_test.ui.details.DetailsViewModel
import kotlinx.android.synthetic.main.description_fragment.wvDescription

class DescriptionFragment: ViewModelFragment() {
    private val viewModel: DetailsViewModel by injectActivityVIewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.description_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var dataHTML = ""
        viewModel.productDetailsLiveData.observe(viewLifecycleOwner, Observer {
            dataHTML=it.seoInfo.description
            wvDescription.loadDataWithBaseURL("",dataHTML,"text/html","UTF-8","")
        })
    }

}