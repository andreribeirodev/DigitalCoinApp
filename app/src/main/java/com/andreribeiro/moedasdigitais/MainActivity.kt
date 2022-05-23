package com.andreribeiro.moedasdigitais

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.andreribeiro.moedasdigitais.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupNavController()
    }

    private fun setupNavController() {
        val navController = navHostFragment.navController
        binding.bottomNavigationView.apply {
            setupWithNavController(navController)
        }
    }
}
