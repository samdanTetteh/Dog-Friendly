package com.ijikod.dog_friendly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.ijikod.dog_friendly.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_holder) as NavHostFragment
    }

    private val appBarConfiguration : AppBarConfiguration by lazy {
        AppBarConfiguration(navHostFragment.navController.graph)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // setup toolbar with navigation component
        binding.toolbar.setupWithNavController(navHostFragment.navController, appBarConfiguration)
    }
}