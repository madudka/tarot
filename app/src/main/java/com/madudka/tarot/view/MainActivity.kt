package com.madudka.tarot.view

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.madudka.tarot.R
import com.madudka.tarot.databinding.ActivityMainBinding
import com.madudka.tarot.services.NetworkConnection
import com.madudka.tarot.services.SoundService
import com.madudka.tarot.utils.fadeHide
import com.madudka.tarot.utils.fadeShow
import com.madudka.tarot.utils.showInternetConnectionDialog
import com.madudka.tarot.view.App.Companion.online

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var soundService: SoundService
    private var ssBound = false
    private val ssConnection = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            soundService = (service as SoundService.SSBinder).getService()
            ssBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            ssBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        setupBottomNavigationView()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        if (!online) showInternetConnectionDialog(this)
    }

    override fun onStart() {
        super.onStart()
        Intent(this, SoundService::class.java).also {
            bindService(it, ssConnection, Context.BIND_AUTO_CREATE)
        }
        observeInternetConnection()
    }

    override fun onPause() {
        super.onPause()
        if (ssBound) soundService.pause()
    }

    override fun onStop() {
        super.onStop()
        if (ssBound) {
            unbindService(ssConnection)
            ssBound = false
        }
    }

    private fun setupBottomNavigationView(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavView.visibility =
                if(destination.id == R.id.imageCardsFragment) View.GONE
                else View.VISIBLE

            when (destination.id) {
                R.id.cardsFragment, R.id.layoutsFragment, R.id.divinationFragment,
                R.id.astroFragment, R.id.settingsFragment -> binding.topPanel.fadeHide()
                else -> binding.topPanel.fadeShow()
            }
        }
    }

    private fun observeInternetConnection(){
        val connectionLiveData = NetworkConnection(applicationContext)
        connectionLiveData.observe(this, Observer { isConnected ->
            isConnected?.let {
                online = isConnected
            }
        })
    }
}