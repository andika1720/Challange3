package com.example.challangebinar3.ui.fragment

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.challangebinar3.R
import com.example.challangebinar3.ViewModel.DetailFragmentMenuViewModel
import com.example.challangebinar3.ViewModel.ViewModelFactory
import com.example.challangebinar3.dataApi.model.DataListMenu
import com.example.challangebinar3.databinding.FragmentDetailMenuBinding


class DetailFragmentMenu : Fragment() {

    private var _binding: FragmentDetailMenuBinding? = null
    private val binding get() = _binding!!


    private val url: String = "https://maps.app.goo.gl/CAN7FLsRkUeRZ2dTA"

    private lateinit var  viewModel: DetailFragmentMenuViewModel


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailMenuBinding.inflate(inflater, container, false)
        setUpCartViewModel()

        viewModel.totalPrice.observe(viewLifecycleOwner){
            binding.buttonDetail.text = "Tambah Ke Keranjang -Rp.$it "

        }

        setData()
        addToCart()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {

            btnBack()
            wViewModel()


            binding.tvLokasi.setOnClickListener {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)


                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        requireContext(),
                        "Google Maps tidak terinstal.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        } catch (e: NullPointerException){
            Toast.makeText(requireContext(), "Error: $e", Toast.LENGTH_SHORT).show()
        }
    }



    @Suppress("DEPRECATION")
    private fun btnBack() {
        binding.buttonBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }


    private fun addToCart() {
        binding.buttonDetail.setOnClickListener {
            val inputNote = binding.inputNote.text.toString()
            viewModel.addToCart(inputNote)
            findNavController().navigate(R.id.action_detailFragmentMenu_to_fragmentCart)
        }
    }

    private fun setUpCartViewModel() {
        val viewModelFactory = ViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailFragmentMenuViewModel::class.java]
    }

    //menerima data dari homefragment
    private fun setData() {
        @Suppress("DEPRECATION")
        val item = arguments?.getParcelable<DataListMenu>("key")

        Log.e("isi item parcel", item.toString())
        item?.let {
            Glide.with(binding.ivDetail)
                .load(item.imageUrl)
                .fitCenter()
                .into(binding.ivDetail)
            binding.nameMenu.text = item.nama
            binding.priceMenu.text = item.hargaFormat
            binding.descDetailMenu.text = item.detail

            viewModel.initSelectedItem(item!!)
        }
    }



    private fun wViewModel(){
        val  observer = Observer<Int> {
            binding.buttonAdd.totalHarga.text = it.toString()
        }

        viewModel.counter.observe(viewLifecycleOwner, observer)



        binding.buttonAdd.plusButtonDetail.setOnClickListener {
            viewModel.increment()
        }

        binding.buttonAdd.minusButtonDetail.setOnClickListener {
            viewModel.decrement()
        }
    }


}
