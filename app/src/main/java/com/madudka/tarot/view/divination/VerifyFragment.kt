package com.madudka.tarot.view.divination

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.madudka.tarot.R
import com.madudka.tarot.databinding.DivinationVerifyFragmentBinding
import com.madudka.tarot.viewmodel.divination.VerifyViewModel

class VerifyFragment : Fragment() {

    companion object {
        fun newInstance() = VerifyFragment()
    }

    private lateinit var viewModel: VerifyViewModel
    private lateinit var binding: DivinationVerifyFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DivinationVerifyFragmentBinding.inflate(layoutInflater)
        val manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        binding.rvVerifyCards.layoutManager = manager

        return inflater.inflate(R.layout.divination_verify_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VerifyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}