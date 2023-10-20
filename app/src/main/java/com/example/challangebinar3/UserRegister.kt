package com.example.challangebinar3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challangebinar3.databinding.ActivityUserRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class UserRegister : AppCompatActivity() {

    private lateinit var binding: ActivityUserRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserRegisterBinding.inflate(layoutInflater)


        auth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_user_register)

        binding.tvRegisLogin.setOnClickListener {
            startActivity(Intent(this, UserLogin::class.java))

        }
        setContentView(binding.root)
    }

    private fun register
}