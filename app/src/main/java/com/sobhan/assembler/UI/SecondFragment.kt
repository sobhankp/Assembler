package com.sobhan.assembler.UI

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sobhan.assembler.R
import com.sobhan.assembler.adapters.ItemRecyclerviewAdapter
import com.sobhan.assembler.databinding.FragmentSecondBinding
import com.sobhan.assembler.models.itemList

class SecondFragment : Fragment(R.layout.fragment_second) {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var mAdapter: ItemRecyclerviewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondBinding.bind(view)
        val navHostFragment = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(binding.toolbar, navHostFragment)

        val data = ArrayList<itemList>()
        data.add(itemList("Cap",false))
        data.add(itemList("Specs",false))
        data.add(itemList("T-Shirt",false))
        data.add(itemList("Jeans",false))
        data.add(itemList("Shoes",false))

        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerView.layoutManager = layoutManager
        mAdapter = ItemRecyclerviewAdapter(data)
        Log.d("second screen", "onViewCreated: ${data.size}")
        binding.recyclerView.adapter = mAdapter

        /*val unwrappedDrawable = AppCompatResources.getDrawable(requireContext(), R.drawable.cap)
        val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable!!)
        DrawableCompat.setTint(wrappedDrawable, Color.RED)*/

        binding.fabAdd.setOnClickListener {
            val selectedItems = mAdapter.selectItems()
            if(selectedItems.size != data.size){
                if(selectedItems.size != 3){
                    Snackbar.make(requireView(),"Kindly select all items or only 3 items", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            val action = SecondFragmentDirections.actionSecondFragment2ToThirdFragment(selectedItems.toIntArray())
            Navigation.findNavController(it).navigate(action)
        }
    }

}