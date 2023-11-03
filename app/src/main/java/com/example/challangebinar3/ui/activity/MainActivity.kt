package com.example.challangebinar3.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.challangebinar3.R
import com.example.challangebinar3.databinding.ActivityMainBinding
import com.example.challangebinar3.util.SharePreference


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharePreference.initial(this)

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