package com.madudka.tarot.view.divination

import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateViewModelFactory
import com.madudka.tarot.R
import com.madudka.tarot.databinding.DivinationDayCardFragmentBinding
import com.madudka.tarot.viewmodel.divination.DayCardViewModel

class DayCardFragment : Fragment() {

//    companion object {
//        fun newInstance() = DayCardFragment()
//    }

    private lateinit var binding: DivinationDayCardFragmentBinding
    private val viewModel: DayCardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DivinationDayCardFragmentBinding.inflate(inflater, container, false)
        //viewModel = ViewModelProvider(this)[DayCardViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDayCard().observe(viewLifecycleOwner) {
            binding.tvCardName.text = it.name
            binding.tvCardOtherName.text = it.otherName
            binding.tvCardType.text = it.type
            binding.tvCardInfo.text = it.info
            binding.imgViewDayCard.setImageBitmap(
                BitmapFactory.decodeByteArray(it.image, 0, it.image.size))
        }
    }
}