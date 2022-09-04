package com.madudka.tarot.view.cards

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.madudka.tarot.R
import com.madudka.tarot.databinding.CardsImageFragmentBinding
import com.madudka.tarot.viewmodel.cards.CardFullViewModel
import com.madudka.tarot.viewmodel.divination.CardViewModel


class ImageFragment : Fragment() {

    private lateinit var binding: CardsImageFragmentBinding
    private val viewModel: CardFullViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CardsImageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getImage()?.let {
            binding.imgViewCard.setImageBitmap(
                BitmapFactory.decodeByteArray(it, 0, it.size)
            )
        }
    }
}