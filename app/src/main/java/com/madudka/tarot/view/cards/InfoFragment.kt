package com.madudka.tarot.view.cards

import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.color
import androidx.core.text.italic
import androidx.core.text.scale
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.madudka.tarot.R
import com.madudka.tarot.databinding.CardsInfoFragmentBinding
import com.madudka.tarot.databinding.ItemExpandedTextViewBinding
import com.madudka.tarot.utils.toBitmap
import com.madudka.tarot.viewmodel.cards.CardFullViewModel

class InfoFragment : Fragment() {

    private lateinit var binding: CardsInfoFragmentBinding
    private val viewModel: CardFullViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CardsInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getCardFull().observe(viewLifecycleOwner){
            binding.imgViewDayCard.setImageBitmap(it.image.toBitmap())
            binding.tvCardName.text = it.name
            binding.tvCardType.text = it.type
            binding.tvCardOtherName.text = it.other_name

            setExpandableTextView(binding.etvBriefly, getString(R.string.briefly), it.straight, it.inverted)
            setExpandableTextView(binding.etvCommon, getString(R.string.common), it.commonStraight, it.commonInverted)
            setExpandableTextView(binding.etvLove, getString(R.string.love), it.loveStraight, it.loveInverted)
            setExpandableTextView(binding.etvQuestion, getString(R.string.question), it.questionStraight, it.questionInverted)
            setExpandableTextView(binding.etvDay, getString(R.string.day), it.day)
            setExpandableTextView(binding.etvAdvice, getString(R.string.advice), it.advice)
        }

        binding.imgViewDayCard.setOnClickListener {
            findNavController().navigate(R.id.action_cardsInfoFragment_to_imageFragment)
        }
    }



    private fun setExpandableTextView(etv: ItemExpandedTextViewBinding, header: String, straight: String, inverted: String){
        setExpandImage(etv)
        etv.expandTextView.text = setExpandTextViewHeader(header)
            .italic { append(getString(R.string.straight)) }
            .append(straight)
            .italic { append(getString(R.string.inverted)) }
            .append(inverted)
    }

    private fun setExpandableTextView(etv: ItemExpandedTextViewBinding, header: String, info: String){
        setExpandImage(etv)
        etv.expandTextView.text = setExpandTextViewHeader(header).append(info)
    }

    private fun setExpandImage(etv: ItemExpandedTextViewBinding){
        etv.expandCollapse.setImageResource(R.drawable.ic_arrow_down)
        etv.expandTextView.setOnExpandStateChangeListener { _, isExpanded ->
            if (isExpanded) etv.expandCollapse.setImageResource(R.drawable.ic_arrow_up)
            else etv.expandCollapse.setImageResource(R.drawable.ic_arrow_down)
        }
    }

    private fun setExpandTextViewHeader(header: String) = SpannableStringBuilder()
        .scale(1.5F) {
            bold {
                color(ContextCompat.getColor(requireContext(), R.color.peach)) {
                    append(header)
                }
            }
        }
}