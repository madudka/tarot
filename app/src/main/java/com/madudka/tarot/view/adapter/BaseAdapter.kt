package com.madudka.tarot.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    private val _listData by lazy { mutableListOf<T>() }
    protected val listData = _listData

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount() = _listData.size

    fun updateData(data: List<T>, onlineFlag: Boolean = false) {
        if (_listData.isNotEmpty() || data.isEmpty()) _listData.clear()
        _listData.addAll(data)
        notifyDataSetChanged()
    }

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view){
        abstract fun bindView(position: Int)
    }
}