package com.madudka.tarot.view.layouts

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.madudka.tarot.LayoutType
import com.madudka.tarot.R
import com.madudka.tarot.databinding.LayoutsInfoFragmentBinding
import com.madudka.tarot.view.showSignificatorInfo
import com.madudka.tarot.viewmodel.layouts.LayoutsFullViewModel

class InfoFragment : Fragment() {

    private lateinit var binding: LayoutsInfoFragmentBinding
    private val viewModel: LayoutsFullViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutsInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getLayout().observe(viewLifecycleOwner) {
            binding.imgViewLayout.setImageBitmap(
                BitmapFactory.decodeByteArray(it.image, 0, it.image?.size!!)
            )
            binding.tvLayoutName.text = it.name
            binding.tvLayoutCardCount.text = getString(R.string.layout_card_count, it.cardCount)
            binding.tvLayoutSignificatorCount.text = getString(
                R.string.layout_significator_count,
                if (it.signifierCount > 0) it.signifierCount else getString(R.string.no)
            )
            binding.tvLayoutType.text = resources.getStringArray(R.array.layouts_filer_list)[it.type]
            binding.tvInfo.text = it.description



            binding.imgViewSignificator.setOnClickListener {
                showSignificatorInfo(requireContext())
            }
        }
    }
}