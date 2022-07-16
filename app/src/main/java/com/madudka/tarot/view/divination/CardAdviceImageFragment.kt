package com.madudka.tarot.view.divination

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.madudka.tarot.R
import com.madudka.tarot.databinding.DivinationCardAdviceImageFragmentBinding
import com.madudka.tarot.view.customCenterYRotate
import com.madudka.tarot.viewmodel.divination.CardAdviceViewModel

class CardAdviceImageFragment : Fragment() {

    private lateinit var binding: DivinationCardAdviceImageFragmentBinding
    private val viewModel: CardAdviceViewModel by activityViewModels()

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
        binding = DivinationCardAdviceImageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: убрать тестовую рубашку, определить рабочий вариант
        binding.imgViewCard.setImageResource(R.drawable.test_back_card_img)

        var imgRotate = false
        binding.imgViewCard.setOnClickListener {
            if (imgRotate){
                binding.tvHelp.visibility = View.INVISIBLE
                findNavController().navigate(R.id.action_divinationCardAdviceImageFragment_to_divinationAdviceFragment)
            } else{
                imgRotate = true

                binding.imgViewCard.customCenterYRotate(0.0f, 90.0f, dur = 700)
                binding.imgViewCard.customCenterYRotate(270.0f, 360.0f, dur = 700, del = 700){
                    viewModel.getImage()?.let {
                        binding.imgViewCard.setImageBitmap(
                            BitmapFactory.decodeByteArray(it, 0, it.size)
                        )
                    }
                }
            }
        }
    }
}