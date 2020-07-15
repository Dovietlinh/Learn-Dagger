package com.example.teko_test.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teko_test.R
import com.example.teko_test.common.ProductConst
import com.example.teko_test.ui.MainActivity
import com.example.teko_test.ui.ViewModelFragment
import com.example.teko_test.ui.details.DetailsFragment
import kotlinx.android.synthetic.main.home_fragment.edtSearchString
import kotlinx.android.synthetic.main.home_fragment.rcvProductHome

class HomeFragment : ViewModelFragment() {

    private lateinit var productListAdapter: ProductPagedListAdapter
    private val viewModel: HomeViewModel by injectActivityVIewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewAdapter()
    }

    private fun initViewAdapter() {
        productListAdapter = ProductPagedListAdapter(this)
        val linearLayoutManager = LinearLayoutManager(context)

        rcvProductHome?.apply {
            this.setHasFixedSize(true)
            this.layoutManager = linearLayoutManager
            this.itemAnimator = DefaultItemAnimator()
            this.adapter = productListAdapter
        }

        edtSearchString.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.productPagedList(s.toString())
                    .observe(viewLifecycleOwner, Observer {
                        productListAdapter.submitList(it)
                    })
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }
    fun onItemSelected(sku: Int){
        val b = Bundle()
        b.putInt(ProductConst.PRODUCT_SKU, sku)
        val mFragmentManager: FragmentManager = parentFragmentManager
        val fragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val detailsFragment = DetailsFragment()
        detailsFragment.arguments = b
        fragmentTransaction.add(
            R.id.frameParent,
            detailsFragment
        ).commit()
//        (activity as MainActivity).showDetailsFragment(sku)
    }
}