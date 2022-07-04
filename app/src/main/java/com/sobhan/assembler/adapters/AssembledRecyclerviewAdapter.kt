package com.sobhan.assembler.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sobhan.assembler.R
import com.sobhan.assembler.models.selectedItemList

class AssembledRecyclerviewAdapter(private var selectedItemList: IntArray) : RecyclerView.Adapter<AssembledRecyclerviewAdapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.display_assembler_item_list,parent,false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val itemsViewModel = selectedItemList[position]

        when (itemsViewModel) {
            0 -> {
                holder.itemImage.setImageResource(R.drawable.cap)
                holder.itemName.text = "Caps"
            }
            1 -> {
                holder.itemImage.setImageResource(R.drawable.glass)
                holder.itemName.text = "Specs"
            }
            2 -> {
                holder.itemImage.setImageResource(R.drawable.tshirt)
                holder.itemName.text = "T-Shirt"
            }
            3 -> {
                holder.itemImage.setImageResource(R.drawable.jeans)
                holder.itemName.text = "Jeans"
            }
            4 -> {
                holder.itemImage.setImageResource(R.drawable.shoe)
                holder.itemName.text = "Shoes"
            }
        }

    }

    override fun getItemCount(): Int {
        return selectedItemList.size
    }

    inner class myViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val itemName: TextView = itemView.findViewById(R.id.tvItemName)
        val itemImage: ImageView = itemView.findViewById(R.id.ivItem)
    }
}