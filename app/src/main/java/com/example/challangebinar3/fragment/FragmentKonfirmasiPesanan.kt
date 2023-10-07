package com.example.challangebinar3.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangebinar3.CartAdapter
import com.example.challangebinar3.ViewModel.CartViewModel

import com.example.challangebinar3.ViewModel.ViewModelFactory
import com.example.challangebinar3.databinding.FragmentKonfirmasiPesananBinding



class FragmentKonfirmasiPesanan : Fragment() {
    private lateinit var binding: FragmentKonfirmasiPesananBinding
    private lateinit var cartViewModel:CartViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentKonfirmasiPesananBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCartVm()
        getConfirm()
        showRv()
    }
    private fun getConfirm() {
        cartViewModel.allCartItems.observe(viewLifecycleOwner) {
            var listMenu = ""
            var priceMenu = ""
            var totalPrice = 0
            it.forEach { item ->
                listMenu += "${item.foodName} - ${item.foodQuantity} x ${item.priceMenu}\n"
                priceMenu += "Rp. ${item.totalPrice}\n"
                totalPrice += item.totalPrice
            }

            val totalText = "Rp. $totalPrice"
            binding.tvNameConfirm.text = listMenu
            binding.tvQuantityConfirm.text = priceMenu
            binding.tvPriceConfirm.text = totalText
        }
    }


    private fun setCartVm() {
        val viewModelFactory = ViewModelFactory(requireActivity().application)
        cartViewModel = ViewModelProvider(this, viewModelFactory)[CartViewModel::class.java]
    }

    private fun showRv() {
        val adapter = CartAdapter(cartViewModel, true)

        binding.rvConfirm.adapter = adapter
        binding.rvConfirm.layoutManager = LinearLayoutManager(requireContext())

        cartViewModel.allCartItems.observe(viewLifecycleOwner) {
            adapter.setData(it)
            var totalPrice = 0
            it.forEach { item ->
                totalPrice += item.totalPrice
            }
        }
    }



    }