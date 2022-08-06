package com.madudka.tarot.view.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.madudka.tarot.databinding.ListItemDivinationVerifyBinding
import com.madudka.tarot.model.VerifyModel
import com.madudka.tarot.view.customScalePulseWithMove
import com.madudka.tarot.view.divination.VerifyViewPagerFragment

class VerifyListAdapter : BaseAdapter<VerifyModel>(){

    lateinit var clickListener: OnItemClickListener<VerifyModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageCardHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =  ListItemDivinationVerifyBinding.inflate(layoutInflater, parent, false)
        return ImageCardHolder(binding)
    }

    inner class ImageCardHolder(val binding: ListItemDivinationVerifyBinding) : BaseViewHolder(binding.root){
        override fun bindView(position: Int) {
            val item = listData[position]

            binding.imgViewItemVerify.apply {
                setImageBitmap(
                    BitmapFactory.decodeByteArray(item.image, 0, item.image.size)
                )
                rotation = if (item.inverted) 180F else 0F
            }

            binding.frameItemVerify.setOnClickListener {
                clickListener.onItemClick(item, position)
            }
        }
    }
}