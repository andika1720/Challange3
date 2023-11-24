package com.example.challangebinar3.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.challangebinar3.R
import com.example.challangebinar3.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
//import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream


class FragmentProfile : Fragment() {

    companion object{
        const val REQUEST_CAMERA = 100
    }
    private lateinit var imgUri: Uri

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

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAuth = FirebaseAuth.getInstance()
        val mUserAuth = mAuth.currentUser


            if (mUserAuth != null) {
                if (mUserAuth.photoUrl != null) {
                    Glide.with(this)
                        .load(mUserAuth.photoUrl)
                        .into(binding.imgProfile)
                } else {
                    Glide.with(this)
                        .load("https://picsum.photos/seed/picsum/200/3000")
                        .into(binding.imgProfile)
                }
            }


            binding.etUsername.setText(mUserAuth?.displayName)
            binding.etEmail.text = mUserAuth?.email
            binding.etNoTelepon.setText(mUserAuth?.phoneNumber)

            if (mUserAuth!!.isEmailVerified){
                binding.verified.visibility = View.VISIBLE
            } else {
                binding.unverif.visibility = View.VISIBLE
            }

            if (mUserAuth.phoneNumber.isNullOrEmpty()){
                binding.etNoTelepon.setText("")
            } else{
                binding.etNoTelepon.setText(mUserAuth.phoneNumber)
            }
        binding.imgProfile.setOnClickListener {
            toCamera()
        }
        binding.btnSave.setOnClickListener {
            val image = when{
                ::imgUri.isInitialized -> imgUri
                mUserAuth.photoUrl == null -> Uri.parse("https://picsum.photos/seed/picsum/200/3000")
                else -> mUserAuth.photoUrl
            }
            val names = binding.etUsername.text.toString().trim()

            if (names.isEmpty()){
                binding.etUsername.error = "Username harus diisi"
                binding.etUsername.requestFocus()
                return@setOnClickListener
            }

            UserProfileChangeRequest.Builder()
                .setDisplayName(names)
                .setPhotoUri(image)
                .build().also {
                    userAuth?.updateProfile(it)?.addOnCompleteListener { it1 ->
                        if (it1.isSuccessful){
                            Toast.makeText(activity, "ProfileUpdated", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(activity, "${it1.exception?.message}",Toast.LENGTH_SHORT).show()
                        }
                    }
                }

        }





//        if (userAuth != null){
//           val email = userAuth!!.email
//           val username = userAuth!!.displayName
//           val phoneNumber = userAuth!!.phoneNumber
//
//            binding.etUsername.text= username
//            binding.etEmail.text = email
//            binding.etNoTelepon.text = phoneNumber
//
//        }
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

    @Suppress("DEPRECATION")
    @SuppressLint("QueryPermissionsNeeded")
    private fun toCamera(){
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {intent ->
            activity?.packageManager?.let {
                intent.resolveActivity(it).also {
                    startActivityForResult(intent, REQUEST_CAMERA)
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK){
            val imgBitmap = data?.extras?.get("data") as Bitmap
            uploadImage(imgBitmap)
        }
    }

    private fun uploadImage(imgBitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        val refre = FirebaseStorage.getInstance().reference.child("img/${FirebaseAuth.getInstance()
            .currentUser?.uid}")

        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100,baos)
        val img = baos.toByteArray()

        refre.putBytes(img)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    refre.downloadUrl.addOnCompleteListener{ task1 ->
                        task1.result?.let {
                            imgUri = it
                            binding.imgProfile.setImageBitmap(imgBitmap)
                        }
                    }
                }
            }
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




