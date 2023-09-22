package com.example.challangebinar3


import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView


class HorizontalAdapter(
    val listFood : ArrayList<ParcelMakanan>,
    private val gridMode : Boolean = true,
    var onItemClick: ((ParcelMakanan)-> Unit) ? = null) :
    RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.text_menu)!!
        val image = itemView. findViewById<ImageView>(R.id.imagev_menu)!!
        val price = itemView.findViewById<TextView>(R.id.tv_RpPrice)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val ( image, name, price,_ ) = listFood[position]
        holder.image.setImageResource(image)
        holder.name.text = name
        holder.price.text = price


        val currentItem = listFood[position]
        holder.itemView.setOnClickListener {
            onItemClick?. invoke(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

}
