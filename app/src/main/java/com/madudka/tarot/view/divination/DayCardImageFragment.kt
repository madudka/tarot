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
import androidx.navigation.fragment.findNavController
import com.madudka.tarot.R
import com.madudka.tarot.databinding.DivinationDayCardImageFragmentBinding
import com.madudka.tarot.view.App.Companion.now
import com.madudka.tarot.view.App.Companion.settings
import com.madudka.tarot.view.customCenterYRotate
import com.madudka.tarot.view.customScaleOutWithMove
import com.madudka.tarot.viewmodel.divination.DayCardViewModel

class DayCardImageFragment : Fragment() {

    private lateinit var binding: DivinationDayCardImageFragmentBinding
    private val viewModel: DayCardViewModel by activityViewModels()

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
        binding = DivinationDayCardImageFragmentBinding.inflate(layoutInflater, container, false)

        settings.dayCardDate = now

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgViewCard.setImageResource(R.drawable.test_back_card_img)

        var imgRotate = false
        binding.imgViewCard.setOnClickListener {
            if (imgRotate) {
                binding.tvHelp.visibility = View.INVISIBLE
                binding.imgViewCard.customScaleOutWithMove {
                    findNavController().navigate(R.id.action_divinationDayCardImageFragment_to_divinationDayCardFragment)
                }
            } else {
                imgRotate = true

                binding.imgViewCard.customCenterYRotate(0.0f, 90.0f, dur = 700)
                binding.imgViewCard.customCenterYRotate(270.0f, 360.0f, dur = 700, del = 700){
                    viewModel.getImage()?.let {
                        binding.imgViewCard.setImageBitmap(
                            BitmapFactory.decodeByteArray(it, 0, it.size)
                        )
                    }
                }
                binding.tvHelp.visibility = View.VISIBLE
            }
        }
    }


}