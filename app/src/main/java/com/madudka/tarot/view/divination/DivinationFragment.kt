package com.madudka.tarot.view.divination

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.madudka.tarot.R
import com.madudka.tarot.databinding.DivinationFragmentBinding
import com.madudka.tarot.viewmodel.divination.DivinationViewModel

class DivinationFragment : Fragment() {

    companion object {
        fun newInstance() = DivinationFragment()
    }

    private lateinit var viewModel: DivinationViewModel
    private lateinit var binding: DivinationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DivinationFragmentBinding.inflate(inflater, container, false)

        binding.btnDivinationVerify.setOnClickListener {
            it.findNavController().navigate(R.id.action_divinationFragment_to_divinationVerifyFragment)
        }
        binding.btnDivinationDayCard.setOnClickListener {
            it.findNavController().navigate(R.id.action_divinationFragment_to_divinationDayCardInputFragment)
        }
        binding.btnDivinationAdvice.setOnClickListener {
            it.findNavController().navigate(R.id.action_divinationFragment_to_divinationAdviceFragment)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DivinationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}