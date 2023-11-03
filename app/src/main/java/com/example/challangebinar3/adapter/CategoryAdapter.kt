package com.example.challangebinar3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challangebinar3.dataApi.model.DataCategoryMenu
import com.example.challangebinar3.databinding.ItemHorizontalBinding

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val differ= object : DiffUtil.ItemCallback<DataCategoryMenu>(){
        override fun areItemsTheSame(
            oldItem: DataCategoryMenu,
            newItem: DataCategoryMenu
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DataCategoryMenu,
            newItem: DataCategoryMenu
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val dif = AsyncListDiffer(this, differ)

    fun sendCategoryMenu(value: List<DataCategoryMenu>) = dif.submitList(value)
    inner class ViewHolder(private var binding: ItemHorizontalBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataCategoryMenu){
            binding.apply {
                textMenu.text = data.nama
                Glide.with(this.imagevMenu)
                    .load(data.imageUrl)
                    .fitCenter()
                    .into(binding.imagevMenu)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        return ViewHolder(ItemHorizontalBinding.inflate(view, parent, false))
    }

    override fun getItemCount(): Int {
        return dif.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dif.currentList[position]
        data.let { holder.bind(data) }
    }


}

