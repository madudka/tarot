package com.madudka.tarot.view.cards

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.madudka.tarot.R
import com.madudka.tarot.viewmodel.CardsViewModel

class CardsFragment : Fragment() {

    companion object {
        fun newInstance() = CardsFragment()
    }

    private lateinit var viewModel: CardsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cards_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CardsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}