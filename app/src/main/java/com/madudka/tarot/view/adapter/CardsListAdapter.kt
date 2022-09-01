package com.madudka.tarot.view.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import com.madudka.tarot.databinding.ListItemCardsBinding
import com.madudka.tarot.model.CardModel

class CardsListAdapter : BaseAdapter<CardModel>() {

    lateinit var clickListener: OnItemClickListener<CardModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemCardsBinding.inflate(layoutInflater, parent, false)
        return CardViewHolder(binding)
    }

    inner class CardViewHolder(private val binding: ListItemCardsBinding) : BaseViewHolder(binding.root){
        override fun bindView(position: Int) {
            val item = listData[position]

            binding.imgViewItemCard.setImageBitmap(
                BitmapFactory.decodeByteArray(item.image, 0, item.image.size)
            )

            binding.tvCardName.text = item.name

            binding.layoutItemCards.setOnClickListener {
                clickListener.onItemClick(item, position)
            }
        }
    }
}