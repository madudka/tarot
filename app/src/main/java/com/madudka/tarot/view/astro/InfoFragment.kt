package com.madudka.tarot.view.astro

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.madudka.tarot.R
import com.madudka.tarot.databinding.AstroInfoFragmentBinding
import com.madudka.tarot.utils.SignType
import com.madudka.tarot.viewmodel.astro.AstroViewModel
import com.madudka.tarot.viewmodel.astro.TranslatorViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class InfoFragment : Fragment() {

    private lateinit var binding: AstroInfoFragmentBinding
    private val viewModel: AstroViewModel by activityViewModels()
    private val args: InfoFragmentArgs by navArgs()
    private val translatorViewModel: TranslatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AstroInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.imgViewLoading.setBackgroundResource(R.drawable.anim_potions)
        (binding.imgViewLoading.background as AnimationDrawable).start()

        binding.imgViewSign.setImageResource(getSignDrawable())
        binding.tvSignDate.text = StringBuilder()
            .append(args.signType.description)
            .append(" ")
            .append(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
            .append(" ")
            .append(args.dayType.description)

        viewModel.getAstro().observe(viewLifecycleOwner){
            translatorViewModel.translateAstro(it)
            translatorViewModel.getDescription().observe(viewLifecycleOwner){ description ->
                binding.tvDescription.setClickableOrigin(description, it.description)
            }

            binding.tvCompatibility.text = getString(R.string.compatibility, it.compatibility.signTranslate())

            translatorViewModel.getMood().observe(viewLifecycleOwner){ mood ->
                binding.tvMood.setClickableOrigin(getString(R.string.mood, mood), getString(R.string.mood, it.mood))
            }
            translatorViewModel.getColor().observe(viewLifecycleOwner){ color ->
                binding.tvColor.setClickableOrigin(getString(R.string.color, color), getString(R.string.color, it.color))
            }

            binding.tvLuckyNumber.text = getString(R.string.lucky_number, it.lucky_number)
            binding.tvLuckyTime.text = getString(R.string.lucky_time, it.lucky_time.normalizeTime())

            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Default){
                translatorViewModel.getTranslating().collect { flag ->
                    if (flag){
                        withContext(Dispatchers.Main){
                            (binding.imgViewLoading.background as AnimationDrawable).stop()
                            binding.imgViewLoading.visibility = View.GONE
                            binding.infoLayout.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

        viewModel.getError().observe(viewLifecycleOwner){
            binding.infoLayout.visibility = View.GONE
            (binding.imgViewLoading.background as AnimationDrawable).stop()
            binding.imgViewLoading.setBackgroundResource(R.drawable.ic_error)
        }
    }

    private fun getSignDrawable(): Int = when (args.signType) {
        SignType.Aries -> R.drawable.aries_circle
        SignType.Taurus -> R.drawable.taurus_circle
        SignType.Gemini -> R.drawable.gemini_circle
        SignType.Cancer -> R.drawable.cancer_circle
        SignType.Leo -> R.drawable.leo_circle
        SignType.Virgo -> R.drawable.virgo_circle
        SignType.Libra -> R.drawable.libra_circle
        SignType.Scorpio -> R.drawable.scorpio_circle
        SignType.Sagittarius -> R.drawable.sagittarius_circle
        SignType.Capricorn -> R.drawable.capricornus_circle
        SignType.Aquarius -> R.drawable.aquarius_circle
        else -> R.drawable.pisces_circle
    }

    private fun String.normalizeTime() = LocalTime
        .parse(this.uppercase(), DateTimeFormatter.ofPattern("ha"))
        .format(DateTimeFormatter.ofPattern("HH:mm"))
        .toString()

    private fun String.signTranslate() =
        SignType.values().firstOrNull { it.name == this }?.description ?: ""

    private fun TextView.setClickableOrigin(text: String, origin: String){
        val spannableString = SpannableString(getString(R.string.string_with_origin, text))

        spannableString.setSpan(
                object : ClickableSpan(){
                    override fun onClick(widget: View) {
                        (widget as TextView).text = origin
                    }
                },
            text.length + 2,
            text.length + 10,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        this.setText(spannableString, TextView.BufferType.SPANNABLE)
        this.movementMethod = LinkMovementMethod.getInstance()
    }
}