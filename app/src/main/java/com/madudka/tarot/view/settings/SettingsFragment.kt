package com.madudka.tarot.view.settings

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.storage.FirebaseStorage
import com.madudka.tarot.R
import com.madudka.tarot.databinding.SettingsFragmentBinding
import com.madudka.tarot.model.SettingsStylesModel
import com.madudka.tarot.view.App.Companion.settings
import com.madudka.tarot.services.SoundService
import com.madudka.tarot.view.BaseFragment
import com.madudka.tarot.view.adapter.OnItemClickListener
import com.madudka.tarot.view.adapter.SettingsStylesAdapter
import com.madudka.tarot.viewmodel.settings.SettingsViewModel

class SettingsFragment : BaseFragment<List<SettingsStylesModel>>() {

    private lateinit var binding: SettingsFragmentBinding
    private val viewModel: SettingsViewModel by activityViewModels()
    private lateinit var soundService: SoundService
    private var ssBound = false
    private val settingsStylesAdapter = SettingsStylesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.switchMusic.apply {
            isChecked = settings.music
            setOnCheckedChangeListener { _, isChecked ->
                if (ssBound) {
                    settings.music = isChecked
                    if (isChecked) soundService.start() else soundService.pause()
                }
            }
        }

        binding.btnClearCache.setOnClickListener {
            context?.cacheDir?.deleteRecursively()
        }

        val manager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        val animation = AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_anim_fall_down)
        binding.rvStyles.apply {
            adapter = settingsStylesAdapter
            layoutManager = manager
            layoutAnimation = animation
            setHasFixedSize(true)
        }

        viewModel.getPrefixes().observe(viewLifecycleOwner){ list ->
            setData(list.map { createSettingsStyle(it) })
        }
    }

    override fun onStart() {
        super.onStart()
        activity?.bindService(
            Intent(activity, SoundService::class.java),
            ssConnection,
            Context.BIND_AUTO_CREATE
        )
    }

    override fun onStop() {
        super.onStop()
        if (ssBound) {
            activity?.unbindService(ssConnection)
            ssBound = false
        }
    }

    override fun updateView() {
        listData?.let {
            settingsStylesAdapter.updateData(it)
            binding.rvStyles.scheduleLayoutAnimation()
        }
    }

    private val ssConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            soundService = (service as SoundService.SSBinder).getService()
            ssBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            ssBound = false
        }
    }

    private val clickListener = object : OnItemClickListener<SettingsStylesModel>{
        override fun onItemClick(item: SettingsStylesModel, position: Int) {
            //TODO Загрузка изображений с показом прогресса и сохранение в room
        }

    }

    private fun createSettingsStyle(style: String) : SettingsStylesModel{
        val storageRef = FirebaseStorage.getInstance().reference
        val pathRefCard = storageRef.child("$style/${(1..22).random()}.jpg")
        val pathRefCardBack = storageRef.child("$style/0.jpg")

        return SettingsStylesModel(style, pathRefCard, pathRefCardBack)
    }
}