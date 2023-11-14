package com.example.challangebinar3.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangebinar3.adapter.CartAdapter
import com.example.challangebinar3.viewModel.NewViewModel
import com.example.challangebinar3.dataApi.model.DataOrders
import com.example.challangebinar3.dataApi.model.ItemOrder
import com.example.challangebinar3.databinding.FragmentKonfirmasiPesananBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.android.inject


class FragmentKonfirmasiPesanan : Fragment() {
    private lateinit var binding: FragmentKonfirmasiPesananBinding

    private lateinit var auth : FirebaseAuth
    private val cartViewModel: NewViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentKonfirmasiPesananBinding.inflate(inflater, container, false)
//        setCartVm()
        getConfirm()
        showRv()
        payment()
        cartViewModel.success.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "OrderSuccses", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    private fun getConfirm(): Int {
        var finaltotal = 0
        cartViewModel.allCartItems.observe(viewLifecycleOwner) {
            var listMenu = ""
            var priceMenu = ""
            var total1 = ""
            var totalPrice = 0
            it.forEach { item ->
                listMenu += "${item.foodName} - ${item.foodQuantity} x ${item.priceMenu}\n"
                priceMenu += "Rp. ${item.totalPrice}\n "
                total1 = "Total = "
                totalPrice += item.totalPrice!!
            }

            val totalText = "Rp. $totalPrice"
            finaltotal = totalPrice
            binding.tvNameConfirm.text = listMenu
            binding.tvQuantityConfirm.text = priceMenu
            binding.totalConfirm.text = total1
            binding.tvPriceConfirm.text = totalText
        }
        return finaltotal
    }

    private fun payment(){
        binding.btPayment.setOnClickListener {
            auth = Firebase.auth
            val currentAuth = auth.currentUser
            val orderItems = cartViewModel.allCartItems.value ?: emptyList()

            if (orderItems.isNotEmpty()){
                val orderReq = DataOrders(currentAuth?.email.toString(), getConfirm(), orderItems.map {
                    ItemOrder(it.foodName,it.foodQuantity,it.foodNote, it.totalPrice!!)
                })

                cartViewModel.postData(orderReq)
            }

            val dialogPayment = DialogPembayaran()
            dialogPayment.show(childFragmentManager, "PaymentSuccesfull")
            cartViewModel.deleteItems()
        }
    }
    private fun showRv() {
        val adapter = CartAdapter(cartViewModel)

        binding.rvConfirm.adapter = adapter
        binding.rvConfirm.layoutManager = LinearLayoutManager(requireContext())

        cartViewModel.allCartItems.observe(viewLifecycleOwner) {
            adapter.setData(it)
            var totalPrice = 0
            it.forEach { item ->
                totalPrice += item.totalPrice!!
            }
        }
    }



    }
