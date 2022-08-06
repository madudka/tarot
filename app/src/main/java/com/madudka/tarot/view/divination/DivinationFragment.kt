package com.madudka.tarot.view.divination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.madudka.tarot.DivinationType
import com.madudka.tarot.R
import com.madudka.tarot.databinding.DivinationFragmentBinding
import com.madudka.tarot.view.App.Companion.now
import com.madudka.tarot.view.App.Companion.settings
import com.madudka.tarot.view.showDialogInfo

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
        checkLimits()

        return binding.root
    }

    private fun setupNavController(){
        binding.btnDivinationVerify.setOnClickListener {
            if (flagVerify){
                it.findNavController()
                    .navigate(R.id.action_divinationFragment_to_verifyFragment)
            }
            else showDialogInfo(requireContext())
        }
        binding.btnDivinationDayCard.setOnClickListener {
            if (flagDayCard) {
                val action = DivinationFragmentDirections.actionDivinationFragmentToInputFragment(DivinationType.DAY_CARD)
                it.findNavController().navigate(action)
            }
            else showDialogInfo(requireContext())
        }
        binding.btnDivinationAdvice.setOnClickListener {
            val action = DivinationFragmentDirections.actionDivinationFragmentToInputFragment(DivinationType.ADVICE)
            it.findNavController().navigate(action)
        }
    }

    private fun checkLimits() {
        if (now <= settings.verifyDate) {
            flagVerify = false
            binding.btnDivinationVerify.alpha = 0.5F
        }
        if (now <= settings.dayCardDate) {
            flagDayCard = false
            binding.btnDivinationDayCard.alpha = 0.5F
        }
    }
}