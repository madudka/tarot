package com.madudka.tarot.view.divination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.madudka.tarot.R
import com.madudka.tarot.databinding.DivinationVerifyFragmentBinding
import com.madudka.tarot.model.VerifyModel
import com.madudka.tarot.view.App.now
import com.madudka.tarot.view.App.settings
import com.madudka.tarot.view.BaseFragment
import com.madudka.tarot.view.adapter.OnItemClickListener
import com.madudka.tarot.view.adapter.VerifyListAdapter
import com.madudka.tarot.viewmodel.divination.VerifyViewModel

class VerifyFragment : BaseFragment<List<VerifyModel>>() {

    private lateinit var binding: DivinationVerifyFragmentBinding
    private val viewModel: VerifyViewModel by activityViewModels()
    private val imageCardListAdapter = VerifyListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DivinationVerifyFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        settings.verifyDate = now()

        val manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        val animation = AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_anim_fall_down)
        imageCardListAdapter.clickListener = clickListener
        binding.rvVerifyCards.apply {
            adapter = imageCardListAdapter
            layoutManager = manager
            layoutAnimation = animation
            setHasFixedSize(true)
        }

        viewModel.getVerify().observe(viewLifecycleOwner) {
            setData(it)
            updateView()
        }

        viewModel.getError().observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), R.string.dark_forces, Toast.LENGTH_SHORT).show()
        }
    }

    override fun updateView() {
        listData?.let { list ->
            imageCardListAdapter.updateData(list)
            binding.rvVerifyCards.scheduleLayoutAnimation()
            binding.verifyInfo.text = setVerifyInfo(list.count { !it.inverted })
        }
    }

    private fun setVerifyInfo(straight: Int) = when {
        straight > 5 -> getString(R.string.verify_info_yes, straight)
        straight < 5 -> getString(R.string.verify_info_no, straight)
        else -> getString(R.string.verify_info_unknown)
    }


    private val clickListener = object : OnItemClickListener<VerifyModel> {
        override fun onItemClick(item: VerifyModel, position: Int) {
            val action = VerifyFragmentDirections
                .actionVerifyFragmentToVerifyViewPagerFragment(position)
            findNavController().navigate(action)
        }
    }
}