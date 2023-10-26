package com.example.challangebinar3.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangebinar3.CartAdapter
import com.example.challangebinar3.R
import com.example.challangebinar3.ViewModel.CartViewModel
import com.example.challangebinar3.ViewModel.ViewModelFactory
import com.example.challangebinar3.databinding.FragmentCartBinding


class FragmentCart : Fragment() {

    private lateinit var binding: FragmentCartBinding

    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartAdapter: CartAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        setUpCartViewModel()

        cartAdapter = CartAdapter(cartViewModel)
        binding.rvCart.setHasFixedSize(true)
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCart.adapter = cartAdapter

        cartViewModel.allCartItems.observe(viewLifecycleOwner) {
            cartAdapter.setData(it)

            var totalPrice = 0
            it.forEach{item ->
                totalPrice += item.totalPrice!!
            }
            val priceTv = "Rp. $totalPrice"
            binding.TotalCart.text = priceTv
        }
        addToConfirm()
        return binding.root
    }

    private fun setUpCartViewModel() {
        val viewModelFactory = ViewModelFactory(requireActivity().application)
        cartViewModel = ViewModelProvider(this, viewModelFactory)[CartViewModel::class.java]
    }

    private fun addToConfirm() {
        binding.btPesan.setOnClickListener {
            findNavController().navigate(
                R.id.action_fragmentCart_to_fragmentKonfirmasiPesanan
            )
        }
    }
}
