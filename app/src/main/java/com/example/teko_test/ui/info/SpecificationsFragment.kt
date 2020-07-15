package com.example.teko_test.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teko_test.R
import com.example.teko_test.ui.ViewModelFragment
import com.example.teko_test.ui.details.DetailsViewModel
import kotlinx.android.synthetic.main.specifications_fragment.rcvSpecification


class SpecificationsFragment : ViewModelFragment(){
    private val viewModel: DetailsViewModel by injectActivityVIewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.specifications_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewAdapter()
    }
    private fun initViewAdapter(){
        val specificationAdapter = SpecificationAdapter()
        val linearLayoutManager = LinearLayoutManager(context)
        rcvSpecification?.apply {
            this.setHasFixedSize(true)
            this.layoutManager = linearLayoutManager
            this.itemAnimator = DefaultItemAnimator()
            rcvSpecification.adapter = specificationAdapter
        }

        viewModel.productDetailsLiveData.observe(viewLifecycleOwner, Observer {
            specificationAdapter.submitList(it.attributeGroups)
        })
    }
}