package com.madudka.tarot.view.cards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.madudka.tarot.R
import com.madudka.tarot.databinding.CardsImageFragmentBinding
import com.madudka.tarot.glide.loadImage
import com.madudka.tarot.viewmodel.cards.CardFullViewModel

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
        //TODO загрузка изображения
        viewModel.getCardFull().observe(viewLifecycleOwner){
            binding.imgViewCard.loadImage(requireContext(), it.image, it.id)
        }

        viewModel.getError().observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), R.string.dark_forces, Toast.LENGTH_SHORT).show()
        }
    }
}