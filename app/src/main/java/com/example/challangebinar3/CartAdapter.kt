package com.example.challangebinar3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        holder.bind(currentItem, viewModel = cartViewModel)

    }

    class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartItem: Cart, viewModel: CartViewModel) {
            binding.tvDesc.text = cartItem.foodName
            binding.tvPrice.text = cartItem.priceMenu.toString()
            binding.addMin.totalHarga.text = cartItem.foodQuantity.toString()
            binding.noteCart.text = cartItem.foodNote.toString()
            Glide.with(this.binding.ivFood)
                .load(cartItem.imgId)
                .fitCenter()
                .into(binding.ivFood)

//        fun bind(cartItem: Cart, viewModel: CartViewModel,cartInvis: Boolean) {
//
//            if (cartInvis){
//                binding.ivDelete.visibility = View.GONE
//                binding.btPlus.visibility = View.INVISIBLE
//                binding.btMin.visibility = View.INVISIBLE
//                binding.tvDesc.text = cartItem.foodName
//                binding.ivFood.setImageResource(cartItem.imgId)
//                binding.tvPrice.text = cartItem.priceMenu.toString()
//                binding.tvNumber.text = cartItem.foodQuantity.toString()
//            }
//            else {
//                binding.tvDesc.text = cartItem.foodName
//                binding.ivFood.setImageResource(cartItem.imgId)
//                binding.tvPrice.text = cartItem.priceMenu.toString()
//                binding.tvNumber.text = cartItem.foodQuantity.toString()
//            }
                binding.ivDelete.setOnClickListener {
                    viewModel.deleteItemCart(cartItem.id)
                }
//            binding.tvDesc.text = cartItem.foodName
//            binding.ivFood.setImageResource(cartItem.imgId)
//            binding.tvPrice.text = cartItem.priceMenu.toString()
//            binding.tvNumber.text = cartItem.foodQuantity.toString()
//
//            binding.ivDelete.setOnClickListener {
//                viewModel.deleteItemCart(cartItem.id)
//            }
//
            binding.addMin.minusButtonDetail.setOnClickListener {
                viewModel.decrement(cartItem)
                binding.addMin.totalHarga.text = cartItem.foodQuantity.toString()
            }

            binding.addMin.plusButtonDetail.setOnClickListener {
                viewModel.increment(cartItem)
                binding.addMin.totalHarga.text = cartItem.foodQuantity.toString()
            }

//            binding.noteCart.setOnKeyListener { _, keycode, event ->
//                if ((event.action == KeyEvent.ACTION_DOWN) &&
//                        (keycode == KeyEvent.KEYCODE_ENTER)) {
//                    val notes = binding.noteCart.text.toString()
//                    viewModel.updateNotes(notes,cartItem)
//                    binding.noteCart.clearFocus()
//                    return@setOnKeyListener true
//                }
//                return@setOnKeyListener false
//            }


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