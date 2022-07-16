package com.madudka.tarot.view.divination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.madudka.tarot.R
import com.madudka.tarot.databinding.DivinationCardAdviceInputFragmentBinding
import com.madudka.tarot.viewmodel.divination.CardAdviceViewModel

class CardAdviceInputFragment : Fragment() {

    private lateinit var binding: DivinationCardAdviceInputFragmentBinding
    private val viewModel: CardAdviceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DivinationCardAdviceInputFragmentBinding.inflate(inflater, container, false)
        
        binding.textInput.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty() && text.last() == '?'){
                viewModel.loadCard()
                findNavController().navigate(R.id.action_divinationCardAdviceInputFragment_to_divinationCardAdviceImageFragment)
            }
        }

        return binding.root
    }
}