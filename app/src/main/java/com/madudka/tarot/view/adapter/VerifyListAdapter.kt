package com.madudka.tarot.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.madudka.tarot.databinding.ListItemDivinationVerifyBinding
import com.madudka.tarot.glide.loadImage
import com.madudka.tarot.model.VerifyModel
import com.madudka.tarot.services.NetworkConnection
import com.madudka.tarot.utils.toBitmap

class VerifyListAdapter : BaseAdapter<VerifyModel>(){

    lateinit var clickListener: OnItemClickListener<VerifyModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageCardHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =  ListItemDivinationVerifyBinding.inflate(layoutInflater, parent, false)
        return ImageCardHolder(binding)
    }

    inner class ImageCardHolder(private val binding: ListItemDivinationVerifyBinding) : BaseViewHolder(binding.root){
        override fun bindView(position: Int) {
            val item = listData[position]

            binding.imgViewItemVerify.apply {
                //TODO загрузка изображений
                loadImage(binding.root.context, item.image, item.id)
                rotation = if (item.inverted) 180F else 0F
            }

            binding.layoutItemVerify.setOnClickListener {
                clickListener.onItemClick(item, position)
            }
        }
    }
}