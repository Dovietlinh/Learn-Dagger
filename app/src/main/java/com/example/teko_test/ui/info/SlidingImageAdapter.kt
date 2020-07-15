package com.example.teko_test.ui.info

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.teko_test.R
import com.example.teko_test.data.model.request.Image


class SlidingImageAdapter(urls: List<Image>) : PagerAdapter() {
    private val urls = urls
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return urls.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var imageLayout: View = LayoutInflater.from(container.context)
            .inflate(R.layout.sliding_images, container, false)!!

        val imageView: ImageView = imageLayout
            .findViewById<View>(R.id.imageSliding) as ImageView


        Glide.with(container.context)
            .load(urls[position].ulr)
            .into(imageView)

        container.addView(imageLayout, 0)
        return imageLayout
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {

    }

    override fun saveState(): Parcelable? {
        return null
    }

}