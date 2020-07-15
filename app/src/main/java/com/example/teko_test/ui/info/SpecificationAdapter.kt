package com.example.teko_test.ui.info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teko_test.R
import com.example.teko_test.data.model.request.AttributeGroups
import kotlinx.android.synthetic.main.item_sepcifications_details.view.tvNameSpecification
import kotlinx.android.synthetic.main.item_sepcifications_details.view.tvValueSpecification

class SpecificationAdapter :
    ListAdapter<AttributeGroups, RecyclerView.ViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View
        view = layoutInflater.inflate(R.layout.item_sepcifications_details, parent, false)
        return AttributeGroupsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AttributeGroupsItemViewHolder).bind(getItem(position), position)
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<AttributeGroups>() {
        override fun areItemsTheSame(oldItem: AttributeGroups, newItem: AttributeGroups): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: AttributeGroups,
            newItem: AttributeGroups
        ): Boolean {
            return oldItem == newItem
        }
    }

    class AttributeGroupsItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(attributeGroups: AttributeGroups?, position: Int) {
            itemView.tvNameSpecification.text = attributeGroups?.name
            itemView.tvValueSpecification.text = attributeGroups?.value
            if (position % 2 != 0) {
                itemView.setBackgroundResource(R.color.white)
            }
        }
    }
}