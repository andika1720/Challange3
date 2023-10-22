package com.example.challangebinar3.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.challangebinar3.R
import com.example.challangebinar3.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser



class FragmentProfile : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private var userAuth: FirebaseUser? = null
    private lateinit var auth: FirebaseAuth
//    private lateinit var uid : String
//    private lateinit var user : User
//    private lateinit var storageReference : StorageReference
//    private lateinit var databaseReference: DatabaseReference



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val _auth = FirebaseAuth.getInstance()
        userAuth = _auth.currentUser

        if (userAuth != null){
           val email = userAuth!!.email
           val username = userAuth!!.displayName
           val phoneNumber = userAuth!!.phoneNumber

            binding.etUsername.text= username
            binding.etEmail.text = email
            binding.etNoTelepon.text = phoneNumber

        }
//        auth = FirebaseAuth.getInstance()
//        uid = auth.currentUser?.uid.toString()
//        databaseReference = FirebaseDatabase.getInstance().getReference("user")
//        if (uid.isNotEmpty()){
//            dataUser()
//        }

        binding.btnLogout.setOnClickListener {
            logoutUser()
        }

    }

    private fun logoutUser() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        findNavController().navigate(R.id.action_fragmentProfile_to_userLogin)
        activity?.finish()
    }

//    private fun dataUser(){
//        databaseReference.child(uid).addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                user = snapshot.getValue(User::class.java)!!
//                binding.etUsername.text = user.username
//                binding.etEmail.text = user.email
//                binding.etNoTelepon.text = user.notelp
//            }
//
//            override fun onCancelled(error: DatabaseError) {}
//        })
//    }
}




