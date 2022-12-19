package com.madudka.tarot.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.madudka.tarot.databinding.DivinationVerifyViewPagerItemBinding
import com.madudka.tarot.glide.loadImage
import com.madudka.tarot.model.VerifyModel

class VerifyViewPagerAdapter : BaseAdapter<VerifyModel>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DivinationVerifyViewPagerItemBinding.inflate(layoutInflater, parent, false)
        return ImageCardHolder(binding)
    }

    inner class ImageCardHolder(private val binding: DivinationVerifyViewPagerItemBinding) : BaseViewHolder(binding.root){
        override fun bindView(position: Int) {
            val item = listData[position]

            binding.imgViewCard.apply {
                //TODO Load images
                loadImage(binding.root.context, item.image, item.id)
                rotation = if (item.inverted) 180F else 0F
            }
        }
    }

}