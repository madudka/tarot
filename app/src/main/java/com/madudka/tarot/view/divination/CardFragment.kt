package com.madudka.tarot.view.divination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.madudka.tarot.R
import com.madudka.tarot.databinding.DivinationCardFragmentBinding
import com.madudka.tarot.glide.loadImage
import com.madudka.tarot.viewmodel.divination.CardViewModel

class CardFragment : Fragment() {

    private lateinit var binding: DivinationCardFragmentBinding
    private val viewModel: CardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DivinationCardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateView()
    }

    private fun updateView(){
        viewModel.getCard().observe(viewLifecycleOwner) {
            binding.tvCardName.text = it.name
            binding.tvCardOtherName.text = it.otherName
            binding.tvCardType.text = it.type
            binding.tvCardInfo.text = it.info

            //TODO загрузка изобаржения
            binding.imgViewDayCard.loadImage(requireContext(), it.image, it.id)
        }

        viewModel.getError().observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), R.string.dark_forces, Toast.LENGTH_SHORT).show()
        }
    }
}