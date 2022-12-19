package com.madudka.tarot.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.madudka.tarot.databinding.ListItemSettingsStylesBinding
import com.madudka.tarot.glide.loadImage
import com.madudka.tarot.view.App.settings
import java.util.*
import kotlin.properties.Delegates

class SettingsStylesAdapter : BaseAdapter<String>() {

    lateinit var clickListener: OnItemClickListener<String>
    private var lastPosition by Delegates.notNull<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsStyleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemSettingsStylesBinding.inflate(layoutInflater, parent, false)
        return SettingsStyleViewHolder(binding)
    }

    inner class SettingsStyleViewHolder(private val binding: ListItemSettingsStylesBinding) : BaseViewHolder(binding.root){
        override fun bindView(position: Int) {
            val item = listData[position]
            lastPosition = 0

            //TODO Load images
            binding.imgViewCard.loadImage(binding.root.context, style = item)
            binding.imgViewBackCard.loadImage(binding.root.context, id = 0, style = item)

            if (item == settings.cardStyle) lastPosition = position
            binding.cardView.isChecked = item == settings.cardStyle

            binding.tvCardStyleName.text = item.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }

            binding.cardView.setOnLongClickListener {
                clickListener.onItemClick(item, position)
                notifyItemChanged(lastPosition)
                //binding.cardView.isChecked = true
                true
            }
        }
    }
}