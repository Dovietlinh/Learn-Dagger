package com.example.teko_test.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.teko_test.R
import com.example.teko_test.common.ProductConst.PRODUCT_SKU
import com.example.teko_test.ui.details.DetailsFragment
import com.example.teko_test.ui.home.HomeFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val mFragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = mFragmentManager.beginTransaction()
        val homeFragment = HomeFragment()
        fragmentTransaction.replace(
            R.id.frameParent,
            homeFragment
        ).commit()
    }

    fun showDetailsFragment(sku: Int) {
        val b = Bundle()
        b.putInt(PRODUCT_SKU, sku)
        val mFragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val detailsFragment = DetailsFragment()
        detailsFragment.arguments = b
        fragmentTransaction.add(
            R.id.frameParent,
            detailsFragment
        ).commit()
    }

    fun backFragment(view: View) {
        onBackPressed()
    }

}
