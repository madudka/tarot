package com.madudka.tarot.view.divination

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.madudka.tarot.R
import com.madudka.tarot.viewmodel.divination.AdviceViewModel

class CardAdviceFragment : Fragment() {

    companion object {
        fun newInstance() = CardAdviceFragment()
    }

    private lateinit var viewModel: AdviceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.divination_advice_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdviceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}