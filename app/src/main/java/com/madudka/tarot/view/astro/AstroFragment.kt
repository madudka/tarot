package com.madudka.tarot.view.astro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.madudka.tarot.utils.DayType
import com.madudka.tarot.R
import com.madudka.tarot.utils.SignType
import com.madudka.tarot.databinding.AstroFragmentBinding
import com.madudka.tarot.utils.showInternetConnectionDialog
import com.madudka.tarot.view.App
import com.madudka.tarot.viewmodel.astro.AstroViewModel

class AstroFragment : Fragment() {

    private lateinit var binding: AstroFragmentBinding
    private val viewModel: AstroViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AstroFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!App.online) showInternetConnectionDialog(requireContext())

        binding.imgViewAries.onSignClick()
        binding.imgViewLeo.onSignClick()
        binding.imgViewSagittarius.onSignClick()
        binding.imgViewTaurus.onSignClick()
        binding.imgViewVirgo.onSignClick()
        binding.imgViewCapricornus.onSignClick()
        binding.imgViewGemini.onSignClick()
        binding.imgViewLibra.onSignClick()
        binding.imgViewAquarius.onSignClick()
        binding.imgViewCancer.onSignClick()
        binding.imgViewScorpio.onSignClick()
        binding.imgViewPisces.onSignClick()
    }

    private fun ImageView.onSignClick() {
        this.setOnClickListener {
            val day = if (binding.chipYesterday.isChecked) DayType.yesterday
            else if (binding.chipToday.isChecked) DayType.today
            else DayType.tomorrow

            val sign = when (it.id){
                R.id.img_view_aries -> SignType.Aries
                R.id.img_view_leo -> SignType.Leo
                R.id.img_view_sagittarius -> SignType.Sagittarius
                R.id.img_view_taurus -> SignType.Taurus
                R.id.img_view_virgo -> SignType.Virgo
                R.id.img_view_capricornus -> SignType.Capricorn
                R.id.img_view_gemini -> SignType.Gemini
                R.id.img_view_libra -> SignType.Libra
                R.id.img_view_aquarius -> SignType.Aquarius
                R.id.img_view_cancer -> SignType.Cancer
                R.id.img_view_scorpio -> SignType.Scorpio
                else -> SignType.Pisces
            }

            viewModel.loadAstro(sign, day)
            val action = AstroFragmentDirections.actionAstroFragmentToAstroInfoFragment(sign, day)
            findNavController().navigate(action)
        }
    }
}