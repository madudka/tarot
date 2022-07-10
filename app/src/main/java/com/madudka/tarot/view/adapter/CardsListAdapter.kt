package com.madudka.tarot.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.madudka.tarot.R
import com.madudka.tarot.model.CardModel

class CardsListAdapter : BaseAdapter<CardModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_divination_verify, parent, false)
        return CardViewHolder(view)
    }

    inner class CardViewHolder(view: View) : BaseViewHolder(view){
        override fun bindView(position: Int) {
            //TODO("Not yet implemented")
        }

    }
}