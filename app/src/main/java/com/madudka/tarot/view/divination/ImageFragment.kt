package com.madudka.tarot.view.divination

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.madudka.tarot.R
import com.madudka.tarot.databinding.DivinationImageFragmentBinding
import com.madudka.tarot.glide.loadImage
import com.madudka.tarot.utils.*
import com.madudka.tarot.view.App.now
import com.madudka.tarot.view.App.settings
import com.madudka.tarot.viewmodel.divination.CardViewModel

class ImageFragment : Fragment() {

    private lateinit var binding: DivinationImageFragmentBinding
    private val viewModel: CardViewModel by activityViewModels()
    private val args: ImageFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        //По нажатию кнопки "Назад" уходим по бэкстеку, без отмены анимации
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DivinationImageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (args.divinationType == DivinationType.DAY_CARD) settings.dayCardDate = now()
        updateView()
    }

    private fun updateView(){
        binding.tvHeader.text =
            if (args.divinationType == DivinationType.DAY_CARD) getString(R.string.day_card)
            else getString(R.string.card_advice)

        //TODO: убрать тестовую рубашку, определить рабочий вариант
        binding.imgViewCard.loadImage(requireContext(), id = 0)

        var imgRotate = false
        binding.imgViewCard.setOnClickListener {
            if (imgRotate) {
                binding.tvHelp.visibility = View.INVISIBLE
                binding.imgViewCard.customScaleOutWithMove {
                    findNavController().navigate(R.id.action_imageFragment_to_cardFragment)
                }
            } else {
                imgRotate = true

                binding.imgViewCard.customCenterYRotate(0.0f, 90.0f, dur = 700)
                binding.imgViewCard.customCenterYRotate(270.0f, 360.0f, dur = 700, del = 700){
                    viewModel.getCard().observe(viewLifecycleOwner){
                        binding.imgViewCard.loadImage(requireContext(), it.image, it.id)
                    }
                    //TODO загрузка изображения
                }
                binding.tvHelp.visibility = View.VISIBLE
            }
        }
    }
}