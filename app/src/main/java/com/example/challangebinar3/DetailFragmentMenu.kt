package com.example.challangebinar3

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.challangebinar3.databinding.FragmentDetailMenuBinding


class DetailFragmentMenu : Fragment() {

    private var _binding: FragmentDetailMenuBinding? = null
    private val binding get() = _binding!!

    private val url: String = "https://maps.app.goo.gl/CAN7FLsRkUeRZ2dTA"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {

            // Ini untuk menangkap data dari Home Fragment
            // Karena pake safe args makanya kita pake fromBundle(arguments as Bundle)
            // dia mengambil arguments yang kita buat tadi di my_Nav
            val data = DetailFragmentMenuArgs.fromBundle(arguments as Bundle)
            binding.ivDetail.setImageResource(data.ivDetail)
            binding.nameMenu.text = data.nameMenu
            binding.priceMenu.text = data.priceMenu
            binding.descDetailMenu.text = data.descDetailMenu

            binding.tvLokasi.setOnClickListener {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(requireContext(), "Google Maps tidak terinstal.", Toast.LENGTH_SHORT).show()
                }
            }

        } catch (e: NullPointerException){
            Toast.makeText(requireContext(), "Error: $e", Toast.LENGTH_SHORT).show()
        }
    }
}