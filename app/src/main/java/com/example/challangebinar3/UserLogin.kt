package com.example.challangebinar3
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.challangebinar3.databinding.ActivityUserLoginBinding
import com.google.firebase.auth.FirebaseAuth


class UserLogin : AppCompatActivity() {
    private lateinit var binding: ActivityUserLoginBinding
    private lateinit var sharedPreference: UserSharedPreference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        sharedPreference = UserSharedPreference(this)


        binding.tvRegisLogin.setOnClickListener {
            startActivity(Intent(this, UserRegister::class.java))
            finish() }

        binding.buttonLogin.setOnClickListener {
            val email: String = binding.etUsernameLogin.text.toString()
            val password: String = binding.etPwLogin.text.toString()
            when {
                email.isEmpty() -> {
                    Toast.makeText(this, "Email belum terisi", Toast.LENGTH_SHORT).show()
                    binding.etUsernameLogin.requestFocus()
                    return@setOnClickListener
                }

                password.isEmpty() -> {
                    Toast.makeText(this, "Password Belum terisi", Toast.LENGTH_SHORT).show()
                    binding.etPwLogin.requestFocus()
                    return@setOnClickListener
                }
            }
            login(email, password)
        }
    }


    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    sharedPreference.saveLogin(true)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    //sharedPreference.saveLogin(true)
                    Toast.makeText(this, "Login Berhasil $email", Toast.LENGTH_SHORT).show()


                    finish()
                } else {
                    Toast.makeText(this, "Email/Password Salah", Toast.LENGTH_SHORT).show()
                }
            }
    }
}



