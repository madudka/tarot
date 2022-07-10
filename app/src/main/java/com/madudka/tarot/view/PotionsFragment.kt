package com.madudka.tarot.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.madudka.tarot.R
import com.madudka.tarot.viewmodel.PotionsViewModel

class PotionsFragment : Fragment() {

    companion object {
        fun newInstance() = PotionsFragment()
    }

    private lateinit var viewModel: PotionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.potions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PotionsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}