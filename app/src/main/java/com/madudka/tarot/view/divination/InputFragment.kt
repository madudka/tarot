package com.madudka.tarot.view.divination

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.madudka.tarot.utils.DivinationType
import com.madudka.tarot.R
import com.madudka.tarot.databinding.DivinationInputFragmentBinding
import com.madudka.tarot.view.OnKeyboardDismissListener
import com.madudka.tarot.viewmodel.divination.CardViewModel

class InputFragment : Fragment() {

    private lateinit var binding: DivinationInputFragmentBinding
    private val viewModel: CardViewModel by activityViewModels()
    private val args: InputFragmentArgs by navArgs()
    private lateinit var keyboardDismissListener: OnKeyboardDismissListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DivinationInputFragmentBinding.inflate(inflater, container, false)
        activity?.let { keyboardDismissListener = it as OnKeyboardDismissListener }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (args.divinationType == DivinationType.DAY_CARD) updateViewDayCard()
        else updateViewAdvice()

        binding.textInput.setOnKeyListener { _, keyCode, event ->
            if ((keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_BACK)
                && event.action == KeyEvent.ACTION_DOWN){
                keyboardDismissListener.onKeyBoardDismiss()
                return@setOnKeyListener true
            } else return@setOnKeyListener false
        }
    }

    private fun updateViewDayCard() {
        binding.tvHeader.text = getString(R.string.day_card)
        binding.tvDescription.text = getString(R.string.your_choice)
        binding.textInputLayout.apply {
            counterMaxLength = 1
            hint = getString(R.string.write_num_0_9)
            helperText = getString(R.string.num_influence)
        }
        binding.textInput.inputType = EditorInfo.TYPE_CLASS_NUMBER

        binding.textInput.doOnTextChanged { text, _, _, _ ->
            text?.let {
                val num = it.toString().toInt()
                if (num in (0..9)) {
                    keyboardDismissListener.onKeyBoardDismiss()
                    viewModel.loadDayCard(num)
                    val action = InputFragmentDirections.actionInputFragmentToImageFragment(DivinationType.DAY_CARD)
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun updateViewAdvice(){
        binding.tvHeader.text = getString(R.string.card_advice)
        binding.tvDescription.text = getString(R.string.your_question)
        binding.textInputLayout.apply {
            hint = getString(R.string.write_question_mark)
            helperText = getString(R.string.think_question)
        }
        binding.textInput.inputType = EditorInfo.TYPE_CLASS_TEXT

        binding.textInput.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty() && text.last() == '?'){
                keyboardDismissListener.onKeyBoardDismiss()
                viewModel.loadCardAdvice()
                val action = InputFragmentDirections.actionInputFragmentToImageFragment(DivinationType.ADVICE)
                findNavController().navigate(action)
            }
        }
    }
}