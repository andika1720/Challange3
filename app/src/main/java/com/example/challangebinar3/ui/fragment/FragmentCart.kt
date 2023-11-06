package com.example.challangebinar3.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangebinar3.adapter.CartAdapter
import com.example.challangebinar3.R
import com.example.challangebinar3.viewModel.NewViewModel
import com.example.challangebinar3.databinding.FragmentCartBinding
import org.koin.android.ext.android.inject


class FragmentCart : Fragment() {

    private lateinit var binding: FragmentCartBinding

    private  val cartViewModel: NewViewModel by inject()
    private lateinit var cartAdapter: CartAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        cartAdapter = CartAdapter(cartViewModel)
        binding.rvCart.setHasFixedSize(true)
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCart.adapter = cartAdapter

        cartViewModel.allCartItems().observe(viewLifecycleOwner) {
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

    private fun addToConfirm() {
        binding.btPesan.setOnClickListener {
            findNavController().navigate(
                R.id.action_fragmentCart_to_fragmentKonfirmasiPesanan
            )
        }
    }
}
