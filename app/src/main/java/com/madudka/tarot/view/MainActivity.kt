package com.madudka.tarot.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.madudka.tarot.R
import com.madudka.tarot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

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
}