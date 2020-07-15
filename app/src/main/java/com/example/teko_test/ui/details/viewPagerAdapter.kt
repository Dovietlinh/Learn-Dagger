package com.example.teko_test.ui.details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.teko_test.R
import com.example.teko_test.common.ProductConst
import com.example.teko_test.ui.home.HomeFragment
import com.example.teko_test.ui.info.ComparingFragment
import com.example.teko_test.ui.info.DescriptionFragment
import com.example.teko_test.ui.info.SpecificationsFragment

class ViewPagerAdapter(fm: FragmentManager, vContext: Context, sku: Int) : FragmentPagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    private val context: Context = vContext
    private val sku: Int = sku
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = DescriptionFragment()
            1 -> fragment = SpecificationsFragment()
            2 -> fragment = ComparingFragment()
        }
        return fragment as Fragment
    }


    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        when (position) {
            0 -> title = context.getString(R.string.description_header)
            1 -> title = context.getString(R.string.info_product_header)
            2 -> title = context.getString(R.string.so_sanh_gia)
        }
        return title
    }

}