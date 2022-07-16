package com.madudka.tarot.view.divination

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.madudka.tarot.databinding.DivinationCardAdviceFragmentBinding
import com.madudka.tarot.viewmodel.divination.CardAdviceViewModel

class CardAdviceFragment : Fragment() {

    private lateinit var binding: DivinationCardAdviceFragmentBinding
    private val viewModel: CardAdviceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DivinationCardAdviceFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCardAdvice().observe(viewLifecycleOwner){
            binding.tvCardName.text = it.name
            binding.tvCardOtherName.text = it.otherName
            binding.tvCardType.text = it.type
            binding.tvCardInfo.text = it.info
            binding.imgViewDayCard.setImageBitmap(
                BitmapFactory.decodeByteArray(
                    it.image, 0, it.image.size
                )
            )
        }
    }
}