package com.madudka.tarot.view.settings

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.madudka.tarot.R
import com.madudka.tarot.databinding.SettingsFragmentBinding
import com.madudka.tarot.glide.GlideApp
import com.madudka.tarot.glide.loadCardBackImage
import com.madudka.tarot.services.SoundService
import com.madudka.tarot.utils.showCase
import com.madudka.tarot.utils.showCleanCacheDialog
import com.madudka.tarot.utils.showInternetConnectionDialog
import com.madudka.tarot.view.App
import com.madudka.tarot.view.App.settings
import com.madudka.tarot.view.BaseFragment
import com.madudka.tarot.view.adapter.OnItemClickListener
import com.madudka.tarot.view.adapter.SettingsStylesAdapter
import com.madudka.tarot.viewmodel.settings.SettingsViewModel
import kotlinx.coroutines.*

private const val SETTINGS_KEY = "SETTINGS_KEY"

class SettingsFragment : BaseFragment<List<String>>() {

    private lateinit var binding: SettingsFragmentBinding
    private val viewModel: SettingsViewModel by activityViewModels()
    private lateinit var soundService: SoundService
    private var ssBound = false
    private val settingsStylesAdapter = SettingsStylesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!App.online) showInternetConnectionDialog(requireContext())

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
            GlideApp.get(requireContext()).clearMemory()

            CoroutineScope(Job() + Dispatchers.IO).launch(CoroutineExceptionHandler{_,_ ->}) {
                GlideApp.get(requireContext()).clearDiskCache()
                requireContext().cacheDir.deleteRecursively()
                withContext(Dispatchers.Main) {
                    showCleanCacheDialog(requireContext())
                }
            }
        }

        setupRecyclerView()

        viewModel.getPrefixes().observe(viewLifecycleOwner){
            setData(it)
        }

        viewModel.getError().observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), R.string.dark_forces, Toast.LENGTH_SHORT).show()
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

    private fun setupRecyclerView() {
        val manager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        val animation = AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_anim_fall_down)
        settingsStylesAdapter.clickListener = clickListener
        binding.rvStyles.apply {
            adapter = settingsStylesAdapter
            layoutManager = manager
            layoutAnimation = animation
            setHasFixedSize(true)
            addOnItemTouchListener(object : RecyclerView.OnItemTouchListener{
                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    return true
                }

                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                    if (binding.rvStyles.size > 0){
                        showCase(
                            requireActivity(),
                            binding.rvStyles[0],
                            getString(R.string.taro_style_hint),
                            SETTINGS_KEY
                        )
                    }
                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

                }
            })
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

    private val clickListener = object : OnItemClickListener<String> {
        override fun onItemClick(item: String, position: Int) {
            settings.cardStyle = item
            loadCardBackImage(requireContext())
            settingsStylesAdapter.notifyItemChanged(position)
        }
    }
}