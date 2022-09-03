package com.madudka.tarot.view.cards

import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.core.text.bold
import androidx.core.text.color
import androidx.core.text.italic
import androidx.fragment.app.activityViewModels
import com.madudka.tarot.R
import com.madudka.tarot.databinding.CardsCardFragmentBinding
import com.madudka.tarot.viewmodel.cards.CardFullViewModel

class CardFullFragment : Fragment() {

    private lateinit var binding: CardsCardFragmentBinding
    private val viewModel: CardFullViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CardsCardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getCardFull().observe(viewLifecycleOwner){
            binding.imgViewDayCard.setImageBitmap(
                BitmapFactory.decodeByteArray(it.image, 0, it.image.size)
            )
            binding.tvCardName.text = it.name
            binding.tvCardType.text = it.type
            binding.tvCardOtherName.text = it.other_name

            binding.etvBriefly.expandCollapse.setImageResource(R.drawable.ic_arrow_down)
            binding.etvBriefly.expandTextView.setOnExpandStateChangeListener { _, isExpanded ->
                if (isExpanded) binding.etvBriefly.expandCollapse.setImageResource(R.drawable.ic_arrow_up)
                else binding.etvBriefly.expandCollapse.setImageResource(R.drawable.ic_arrow_down)
            }
            binding.etvBriefly.expandTextView.text = SpannableStringBuilder()
                .color(ContextCompat.getColor(requireContext(), R.color.peach)) {
                    bold { append(getString(R.string.briefly)) }
                }
//                .bold {
//                    color(ContextCompat.getColor(requireContext(), R.color.peach)) {
//                        append(getString(R.string.briefly))
//                    }
//                }
                .italic { append(getString(R.string.straight)) }
                .append(it.straight)
                .italic { append(getString(R.string.inverted)) }
                .append(it.inverted)


            binding.etvCommon.expandTextView.text = getString(
                R.string.card_info,
                getString(R.string.common),
                it.commonStraight,
                it.commonInverted
            )
            binding.etvLove.expandTextView.text = getString(
                R.string.card_info,
                getString(R.string.love),
                it.loveStraight,
                it.loveInverted
            )
            binding.etvQuestion.expandTextView.text = getString(
                R.string.card_info,
                getString(R.string.question),
                it.questionStraight,
                it.questionInverted
            )
            binding.etvDay.expandTextView.text = getString(
                R.string.card_info_single,
                getString(R.string.day_card),
                it.day
            )
            binding.etvAdvice.expandTextView.text = getString(
                R.string.card_info_single,
                getString(R.string.card_advice),
                it.advice
            )
        }
    }
}