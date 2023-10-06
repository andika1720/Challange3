package com.example.challangebinar3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challangebinar3.Database.Cart
import com.example.challangebinar3.ViewModel.CartViewModel
import com.example.challangebinar3.databinding.ItemCartBinding
import com.google.android.material.snackbar.Snackbar

class CartAdapter(
    private val cartViewModel: CartViewModel
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var cartItems: List<Cart> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = cartItems[position]
        holder.bind(currentItem)

    }

    class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartItem: Cart) {
            binding.tvDesc.text = cartItem.foodName
            binding.ivFood.setImageResource(cartItem.imgId)
            binding.tvPrice.text = cartItem.priceMenu.toString()
            binding.tvNumber.text = cartItem.foodQuantity.toString()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(cartItems: List<Cart>) {
        this.cartItems = cartItems
        notifyDataSetChanged()
    }

    private fun showSnackBar(view: View) {
        Snackbar.make(view, "Item removed from the cart", Snackbar.LENGTH_SHORT).show()
    }
}