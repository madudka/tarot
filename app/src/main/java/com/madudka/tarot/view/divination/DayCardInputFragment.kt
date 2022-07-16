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
import com.madudka.tarot.databinding.DivinationDayCardInputFragmentBinding
import com.madudka.tarot.viewmodel.divination.DayCardViewModel

class DayCardInputFragment : Fragment() {

    private lateinit var binding: DivinationDayCardInputFragmentBinding
    private val viewModel: DayCardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DivinationDayCardInputFragmentBinding.inflate(layoutInflater, container, false)

        binding.textInput.doOnTextChanged { text, _, _, _ ->
            val num = text.toString().toInt()
            if (num in (0..9)) {
                viewModel.loadCard(num)
                findNavController().navigate(R.id.action_divinationDayCardInputFragment_to_divinationDayCardImageFragment)
            }
        }

        return binding.root
    }
}