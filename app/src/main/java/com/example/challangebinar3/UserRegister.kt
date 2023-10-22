package com.example.challangebinar3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.challangebinar3.dataApi.model.User
import com.example.challangebinar3.databinding.ActivityUserRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class UserRegister : AppCompatActivity() {

    private lateinit var binding: ActivityUserRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.tvRegisLogin.setOnClickListener {
            val intent = Intent(this, UserLogin::class.java)
            startActivity(intent)
        }

        binding.buttonReg.setOnClickListener {
            val email = binding.etEmailRegis.text.toString()
            val username = binding.etUsernameRegis.text.toString()
            val pass = binding.etPwRegis.text.toString()
            val noTelp = binding.etNoTelepon.text.toString()
            val intent = Intent(this, UserLogin::class.java)
            startActivity(intent)
                when {
                    email.isEmpty() -> {
                        Toast.makeText(this, "Email belum terisi", Toast.LENGTH_SHORT).show()
                        binding.etEmailRegis.requestFocus()
                        return@setOnClickListener
                    }

                    username.isEmpty() -> {
                        Toast.makeText(this, "Username belum terisi", Toast.LENGTH_SHORT).show()
                        binding.etUsernameRegis.requestFocus()
                        return@setOnClickListener
                    }

                    pass.isEmpty() -> {
                        Toast.makeText(this, "Password Belum terisi", Toast.LENGTH_SHORT).show()
                        binding.etPwRegis.requestFocus()
                        return@setOnClickListener
                    }

                    noTelp.isEmpty() -> {
                        Toast.makeText(this, "No Telephone Belum terisi", Toast.LENGTH_SHORT).show()
                        binding.etNoTelepon.requestFocus()
                        return@setOnClickListener
                    }
                }
            register(email,username, pass, noTelp )
        }
    }

    private fun register(email: String, password: String, username: String, notelp: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ it ->
                if (it.isSuccessful){
                    val dbUser = database.reference.child("user").child(auth.currentUser!!.uid)
                    val user  = User(username, email, notelp, auth.currentUser!!.uid)

                    dbUser.setValue(user).addOnCompleteListener {
                        if (it.isSuccessful)
                            Toast.makeText(this,"Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, UserLogin::class.java)
                        startActivity(intent)
                        finish()
                    }
                }else{
                    Toast.makeText(this ,"${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
