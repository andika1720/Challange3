package com.example.challangebinar3




import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challangebinar3.dataApi.model.DataListMenu
import com.example.challangebinar3.databinding.GridVerticalBinding
import com.example.challangebinar3.databinding.VerticalItemBinding


class HorizontalAdapter(
    var gridMode : Boolean = true,
    var onItemClick:OnClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val differ = object : DiffUtil.ItemCallback<DataListMenu>(){
        override fun areItemsTheSame(oldItem: DataListMenu, newItem: DataListMenu): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataListMenu, newItem: DataListMenu): Boolean {
            return oldItem == newItem
        }

    }

    private val dif = AsyncListDiffer(this, differ)

    fun sendListMenu(value: List<DataListMenu>) = dif.submitList(value)
    inner class VerticalViewHolder(private var binding: VerticalItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataListMenu) {
            binding.apply {
                textMenu.text = data.nama
                tvPrice.text = data.hargaFormat
                Glide.with(this.imagevMenu)
                    .load(data.imageUrl)
                    .fitCenter()
                    .into(imagevMenu)
            }
        }
    }

    inner class GridViewHolder(private var binding: GridVerticalBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: DataListMenu){
            binding.apply {
                textMenu.text = data.nama
                tvPrice.text = data.hargaFormat
                Glide.with(this.imagevMenu)
                    .load(data.imageUrl)
                    .fitCenter()
                    .into(imagevMenu)
            }
        }

    }

    interface OnClickListener {
        fun itemClick(data: DataListMenu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
        return if (gridMode){
            GridViewHolder(GridVerticalBinding.inflate(view, parent, false))
        } else {
            VerticalViewHolder(VerticalItemBinding.inflate(view,parent,false))
        }
    }

    override fun getItemCount(): Int {
        return dif.currentList.size-1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dif.currentList[position]
        when(holder) {
            is GridViewHolder -> holder.bind(data)
            is VerticalViewHolder -> holder.bind(data)
        }

        holder.itemView.setOnClickListener {
            onItemClick.itemClick(data)
        }

    }

}
