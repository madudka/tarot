package com.madudka.tarot.view.divination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.madudka.tarot.R
import com.madudka.tarot.databinding.DivinationVerifyViewPagerFragmentBinding
import com.madudka.tarot.model.VerifyModel
import com.madudka.tarot.view.BaseFragment
import com.madudka.tarot.view.adapter.VerifyViewPagerAdapter
import com.madudka.tarot.viewmodel.divination.VerifyViewModel

class VerifyViewPagerFragment : BaseFragment<List<VerifyModel>>() {

    private lateinit var binding: DivinationVerifyViewPagerFragmentBinding
    private val viewModel: VerifyViewModel by activityViewModels()
    private val viewPagerAdapter = VerifyViewPagerAdapter()
    private val args: VerifyViewPagerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DivinationVerifyViewPagerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewPager.adapter = viewPagerAdapter

        viewModel.getVerify().observe(viewLifecycleOwner){
            setData(it)
        }

        viewModel.getError().observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), R.string.dark_forces, Toast.LENGTH_SHORT).show()
        }
    }

    override fun updateView() {
        listData?.let {
            viewPagerAdapter.updateData(it)
            binding.viewPager.setCurrentItem(args.position, false)
        }
    }
}