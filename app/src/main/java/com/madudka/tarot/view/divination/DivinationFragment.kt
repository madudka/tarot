package com.madudka.tarot.view.divination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.madudka.tarot.R
import com.madudka.tarot.databinding.DivinationFragmentBinding
import com.madudka.tarot.utils.DivinationType
import com.madudka.tarot.utils.showCases
import com.madudka.tarot.utils.showDialogInfo
import com.madudka.tarot.view.App.now
import com.madudka.tarot.view.App.settings
import com.madudka.tarot.view.OnKeyboardClosedListener

private const val VERIFY_KEY = "VERIFY"
private const val DAY_CARD_KEY = "DAY_CARD"
private const val ADVICE_KEY = "ADVICE"

class DivinationFragment : Fragment() {

    private lateinit var binding: DivinationFragmentBinding
    private var flagVerify = true
    private var flagDayCard = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DivinationFragmentBinding.inflate(inflater, container, false)

        setupNavController()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showCases(
            requireActivity(),
            listOf(
                Triple(binding.btnDivinationVerify, getString(R.string.verify_hint), VERIFY_KEY),
                Triple(binding.btnDivinationDayCard, getString(R.string.day_card_hint), DAY_CARD_KEY),
                Triple(binding.btnDivinationAdvice, getString(R.string.advice_hint), ADVICE_KEY)
            )
        )
    }

    override fun onResume() {
        super.onResume()
        checkLimits()
    }

    private fun setupNavController() {
        binding.btnDivinationVerify.setOnClickListener {
            if (flagVerify) {
                it.findNavController()
                    .navigate(R.id.action_divinationFragment_to_verifyFragment)
            } else showDialogInfo(requireContext())
        }
        binding.btnDivinationDayCard.setOnClickListener {
            if (flagDayCard) {
                val action = DivinationFragmentDirections.actionDivinationFragmentToInputFragment(
                    DivinationType.DAY_CARD
                )
                it.findNavController().navigate(action)
            } else showDialogInfo(requireContext())
        }
        binding.btnDivinationAdvice.setOnClickListener {
            val action =
                DivinationFragmentDirections.actionDivinationFragmentToInputFragment(DivinationType.ADVICE)
            it.findNavController().navigate(action)
        }
    }

    private fun checkLimits() {
        if (now() <= settings.verifyDate) {
            flagVerify = false
            binding.btnDivinationVerify.alpha = 0.5F
        }
        if (now() <= settings.dayCardDate) {
            flagDayCard = false
            binding.btnDivinationDayCard.alpha = 0.5F
        }
    }
}