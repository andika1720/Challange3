package com.example.challangebinar3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.challangebinar3.databinding.ActivityUserLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserLogin : AppCompatActivity() {
    private lateinit var binding: ActivityUserLoginBinding
    private lateinit var sharedPreference: UserSharedPreference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLoginBinding.inflate(layoutInflater,)

        auth = FirebaseAuth.getInstance()


        binding.buttonLogin.setOnClickListener {

            login(binding.etUsernameLogin.text.toString(), binding.etPwLogin.text.toString())
        }
        binding.tvRegisLogin.setOnClickListener {
            startActivity(Intent(this, UserRegister::class.java))

            setContentView(binding.root)
        }
    }

    override fun onStart() {
        super.onStart()
        val CurrenttUser = auth.currentUser
        if (CurrenttUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
        fun login(email:String, password: String){
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{log ->
                    if (log.isSuccessful){
                        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                        sharedPreference.saveLogin(true)
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Email/Passsword Anda tidak terdaftar", Toast.LENGTH_SHORT).show()

                    }
                }
        }





}


