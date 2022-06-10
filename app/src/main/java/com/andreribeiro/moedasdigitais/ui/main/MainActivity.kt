package com.andreribeiro.moedasdigitais.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.andreribeiro.moedasdigitais.R
import com.andreribeiro.moedasdigitais.databinding.ActivityMainBinding
import com.andreribeiro.moedasdigitais.databinding.PartAppnameDateBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val bindingAppNameTime: PartAppnameDateBinding by lazy {
        PartAppnameDateBinding.inflate(layoutInflater)
    }

    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupNavController()

        val layoutPartAppNameTime = bindingAppNameTime

        val date = Calendar.getInstance().time
        val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        layoutPartAppNameTime.textViewDate.text = dateTimeFormat.toString()
    }

    private fun setupNavController() {
        val navController = navHostFragment.navController
        binding.bottomNavigationView.apply {
            setupWithNavController(navController)
        }
    }
}
