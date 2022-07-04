package com.sobhan.assembler.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.sobhan.assembler.R
import com.sobhan.assembler.models.itemList


class ItemRecyclerviewAdapter(
    private var mList: List<itemList>
    ) : RecyclerView.Adapter<ItemRecyclerviewAdapter.myViewHolder>() {

    private var isEnabled = true
    private val itemSelectedList = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.display_item_list,parent,false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        when (position) {
            0 -> holder.itemImage.setImageResource(R.drawable.cap)
            1 -> holder.itemImage.setImageResource(R.drawable.glass)
            2 -> holder.itemImage.setImageResource(R.drawable.tshirt)
            3 -> holder.itemImage.setImageResource(R.drawable.jeans)
            4 -> holder.itemImage.setImageResource(R.drawable.shoe)
        }

        holder.itemName.text = itemsViewModel.text
        holder.itemSelected.visibility = View.INVISIBLE

        holder.itemImage.setOnClickListener{
            if(itemSelectedList.contains(position)){
                itemSelectedList.remove(position)
                holder.itemSelected.visibility = View.INVISIBLE
                itemsViewModel.selected = false
            }else {
                selectedList(holder,itemsViewModel,position)
            }
        }
    }

    private fun selectedList(holder: ItemRecyclerviewAdapter.myViewHolder, itemsViewModel: itemList, position: Int) {

        isEnabled = true
        itemSelectedList.add(position)
        itemsViewModel.selected = true
        holder.itemSelected.visibility = View.VISIBLE
        //itemsViewModel.text
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun selectItems(): MutableList<Int> {
        return itemSelectedList
    }

    inner class myViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val itemName: TextView = itemView.findViewById(R.id.tvItemName)
        val itemImage: ImageView = itemView.findViewById(R.id.ivItem)
        val itemSelected: ImageView = itemView.findViewById(R.id.ivSelected)
    }
}