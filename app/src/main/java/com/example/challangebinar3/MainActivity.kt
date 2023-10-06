package com.example.challangebinar3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.challangebinar3.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    val navHostFragment =
        supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
    val navController = navHostFragment.navController

    binding.bottomNavigationView.setupWithNavController(navController)

    navController.addOnDestinationChangedListener {_,destination,_ ->
        when(destination.id){
            R.id.detailFragmentMenu -> hideBotNav()
            R.id.fragmentKonfirmasiPesanan -> hideBotNav()
            else -> shotBotNav()
        }
    }

    }


    private fun shotBotNav(){
        binding.bottomNavigationView.visibility = View.VISIBLE
    }
    private fun hideBotNav(){
        binding.bottomNavigationView.visibility = View.GONE
    }
}