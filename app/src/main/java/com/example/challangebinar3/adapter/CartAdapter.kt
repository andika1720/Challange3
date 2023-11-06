package com.example.challangebinar3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challangebinar3.Database.Cart
import com.example.challangebinar3.ViewModel.NewViewModel
import com.example.challangebinar3.databinding.ItemCartBinding

class CartAdapter(
    private val cartViewModel: NewViewModel
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {


    private var cartItems: List<Cart> = emptyList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = cartItems[position]
        holder.bind(currentItem, viewModel = cartViewModel)

    }

    class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartItem: Cart, viewModel: NewViewModel) {
            binding.tvDesc.text = cartItem.foodName
            binding.tvPrice.text = cartItem.priceMenu.toString()
            binding.addMin.totalHarga.text = cartItem.foodQuantity.toString()
            binding.noteCart.text = cartItem.foodNote.toString()
            Glide.with(this.binding.ivFood)
                .load(cartItem.imgId)
                .fitCenter()
                .into(binding.ivFood)

                binding.ivDelete.setOnClickListener {
                    viewModel.deleteItemCart(cartItem.id)
                }

            binding.addMin.minusButtonDetail.setOnClickListener {
                viewModel.decrementCart(cartItem)
                binding.addMin.totalHarga.text = cartItem.foodQuantity.toString()
            }

            binding.addMin.plusButtonDetail.setOnClickListener {
                viewModel.incrementCart(cartItem)
                binding.addMin.totalHarga.text = cartItem.foodQuantity.toString()
            }

        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(cartItems: List<Cart>) {
        this.cartItems = cartItems
        notifyDataSetChanged()
    }



}