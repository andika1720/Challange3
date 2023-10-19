package com.example.challangebinar3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challangebinar3.databinding.ActivityUserLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserLogin : AppCompatActivity() {
    private lateinit var binding: ActivityUserLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = Firebase.auth

//        binding.btnAction.setOnclickListener{
//            login(binding.edtEmail.text.toString(), binding.edtPass.text.toString())
//        }
//    }
//        fun login(email:String, password: String){
//            auth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener{
//                    if (it.isSuccessful){
//                        //maka berhasil Login
//                        startActivity(Intent(this, MainActivity::class.java))
//                    } else {
//                        register(email, password)
//                    }
                }
        }
//    fun register (email:String, password: String){
//        auth.createUserWithEmailandPassword(email,password)
//            .addOnCompleteListener{
//                if (it.isSuccessful){
//                    //maka munculkan toast successfull
    //startActivity(Intent(this, MainActivity::class.java))
    //Toast.MakeText(this,"Registrasi Berhasil",

//                } else {
//                    // maka munculkan toast gagal
//                }
//            }
//    }


