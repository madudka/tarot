package com.madudka.tarot.view.adapter

interface OnItemClickListener<T> {
    fun onItemClick(item: T, position: Int)
}