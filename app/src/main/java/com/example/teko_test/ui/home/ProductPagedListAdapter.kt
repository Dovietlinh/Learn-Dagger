package com.example.teko_test.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teko_test.R
import com.example.teko_test.data.model.request.Product
import kotlinx.android.synthetic.main.item_product_home.view.imgProductHome
import kotlinx.android.synthetic.main.item_product_home.view.tvSellPrice
import kotlinx.android.synthetic.main.item_product_home.view.tvSupplierSalePrice
import kotlinx.android.synthetic.main.item_product_home.view.tvTitleProduct
import java.text.NumberFormat
import javax.inject.Inject

class ProductPagedListAdapter(private val fragment: HomeFragment) :
    PagedListAdapter<Product, RecyclerView.ViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View
        view = layoutInflater.inflate(R.layout.item_product_home, parent, false)
        return ProductItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductItemViewHolder).bind(getItem(position),fragment)
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.sku == newItem.sku
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    class ProductItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(product: Product?, fragment: HomeFragment) {
            val sizeImage = product!!.images.size
            if (sizeImage > 0) {
                Glide.with(itemView.context)
                    .load(product?.images[0].ulr)
                    .into(itemView.imgProductHome)
            }
            itemView.tvTitleProduct.text = product?.name
            if(product?.price?.sellPrice!=product?.price?.supplierSalePrice) {
                itemView.tvSupplierSalePrice.visibility = View.VISIBLE
                itemView.tvSupplierSalePrice.text = numberFormat(product?.price?.supplierSalePrice)
            }
            itemView.tvSellPrice.text = numberFormat(product?.price?.sellPrice)+"đ"
            itemView.setOnClickListener {
                fragment.onItemSelected(product?.sku)
            }

        }
        private fun numberFormat(number: Double): String {
            return NumberFormat.getNumberInstance().format(number)
        }

    }

}