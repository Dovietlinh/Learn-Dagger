package com.example.teko_test.ui.details

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.teko_test.R
import com.example.teko_test.common.ProductConst.PRODUCT_SKU
import com.example.teko_test.data.model.request.Image
import com.example.teko_test.data.model.request.ProductDetails
import com.example.teko_test.ui.ViewModelFragment
import com.example.teko_test.ui.info.SlidingImageAdapter
import kotlinx.android.synthetic.main.details_fragment.indicator
import kotlinx.android.synthetic.main.details_fragment.pagerImage
import kotlinx.android.synthetic.main.details_fragment.tabContainer
import kotlinx.android.synthetic.main.details_fragment.titleDetails
import kotlinx.android.synthetic.main.details_fragment.tvButtom
import kotlinx.android.synthetic.main.details_fragment.tvSellPrice
import kotlinx.android.synthetic.main.details_fragment.tvSellPriceHeader
import kotlinx.android.synthetic.main.details_fragment.tvSkuProduct
import kotlinx.android.synthetic.main.details_fragment.tvSupplierSalePrice
import kotlinx.android.synthetic.main.details_fragment.txtTitleDetail
import kotlinx.android.synthetic.main.details_fragment.vpInfoProduct
import java.text.NumberFormat
import java.util.Timer
import java.util.TimerTask

class DetailsFragment : ViewModelFragment() {
    private lateinit var viewpageradapter: ViewPagerAdapter
    private lateinit var viewpagerSlidingadapter: SlidingImageAdapter
    private val viewModel: DetailsViewModel by injectActivityVIewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewpager()
    }

    private fun numberFormat(number: Double): String {
        return NumberFormat.getNumberInstance().format(number)
    }

    private fun initViewpager() {
        var skuProduct = 0
        val skuArguments = this.arguments?.getInt(PRODUCT_SKU)
        skuArguments?.let {
            skuProduct = it
        }
        viewModel.getProductDetails(skuProduct).observe(viewLifecycleOwner, Observer {
            loadView(it)
        })
    }

    private fun loadView(it: ProductDetails){
        var titleHeader = it.name
        if (it.name.length > 26) {
            titleHeader = titleHeader.substring(0, 24) + "..."
        }
        titleDetails.text = titleHeader
        tvSellPriceHeader.text = numberFormat(it.price.sellPrice) + " đ"
        tvSellPrice.text = numberFormat(it.price.sellPrice) + " đ"
        tvButtom.text = numberFormat(it.price.sellPrice) + " đ"
        if (it.price.sellPrice != it.price.supplierSalePrice) {
            tvSupplierSalePrice.text = numberFormat(it.price.supplierSalePrice)
            tvSupplierSalePrice.visibility = View.VISIBLE
        }
        txtTitleDetail.text = it.name
        tvSkuProduct.text = it.sku.toString()
        setupIndicator(it.images)
        setupViewPagerAdapter(it.sku)
    }

    private fun setupViewPagerAdapter(skuProduct: Int) {
        viewpageradapter = ViewPagerAdapter(parentFragmentManager, requireContext(), skuProduct)
        vpInfoProduct.adapter = viewpageradapter
        tabContainer.setupWithViewPager(vpInfoProduct)
    }

    private fun setupIndicator(images: List<Image>) {
        var numberPage = 0
        viewpagerSlidingadapter = SlidingImageAdapter(images)
        pagerImage.adapter = viewpagerSlidingadapter
        indicator.setViewPager(pagerImage)
        val density = resources.displayMetrics.density

        indicator.radius = 5 * density

        numberPage = images.size
        var currentPage = 0

        val handler = Handler()
        val update = Runnable {
            if (currentPage === numberPage) {
                currentPage = 0
            }
            try {
                pagerImage.setCurrentItem(currentPage++, true)
            } catch (ex: Exception) {
            }

        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }

        }, 3000, 3000)

        indicator.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(pos: Int) {}
        })
    }

}