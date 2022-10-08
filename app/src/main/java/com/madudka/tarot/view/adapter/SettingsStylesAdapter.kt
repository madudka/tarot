package com.madudka.tarot.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.madudka.tarot.databinding.ListItemSettingsStylesBinding
import com.madudka.tarot.model.SettingsStylesModel
import com.madudka.tarot.glide.loadImage

class SettingsStylesAdapter : BaseAdapter<SettingsStylesModel>() {

    lateinit var clickListener: OnItemClickListener<SettingsStylesModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsStyleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemSettingsStylesBinding.inflate(layoutInflater, parent, false)
        return SettingsStyleViewHolder(binding, parent.context)
    }

    inner class SettingsStyleViewHolder(val binding: ListItemSettingsStylesBinding, val context: Context) : BaseViewHolder(binding.root){
        override fun bindView(position: Int) {
            val item = listData[position]
            //val context = binding.root.context

            loadImage(context, item.pathRefCard, binding.imgViewCard)
            loadImage(context, item.pathRefCardBack, binding.imgViewBackCard)

            //binding.imgViewCard.setImageBitmap(item.imageCard.toBitmap())
            //binding.imgViewBackCard.setImageBitmap(item.imageCardBack.toBitmap())
            binding.tvCardStyleName.text = item.name

            binding.cardView.setOnLongClickListener {
                clickListener.onItemClick(item, position)
                true
            }
        }
    }
}