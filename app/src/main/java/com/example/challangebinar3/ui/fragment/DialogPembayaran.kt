package com.example.challangebinar3.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.challangebinar3.R
import com.example.challangebinar3.databinding.FragmentDialogPembayaranBinding


class DialogPembayaran : DialogFragment() {

    private lateinit var binding: FragmentDialogPembayaranBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDialogPembayaranBinding.inflate(layoutInflater,container,false)

        backtoMenu()
        return binding.root
    }

    private fun backtoMenu(){
        binding.backToMenu.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }

}