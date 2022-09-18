package com.madudka.tarot.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.madudka.tarot.databinding.ListItemLayoutsBinding
import com.madudka.tarot.model.LayoutModel

class LayoutsListAdapter : BaseAdapter<LayoutModel>() {

    lateinit var clickListener: OnItemClickListener<LayoutModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LayoutViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemLayoutsBinding.inflate(layoutInflater, parent, false)
        return LayoutViewHolder(binding)
    }

    inner class LayoutViewHolder(val binding: ListItemLayoutsBinding) : BaseViewHolder(binding.root){
        override fun bindView(position: Int) {
            val item = listData[position]

            binding.tvLayoutName.text = item.name
            binding.layoutItemCards.setOnClickListener {
                clickListener.onItemClick(item, position)
            }
        }
    }
}