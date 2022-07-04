package com.sobhan.assembler.UI

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.ImageViewCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.sobhan.assembler.R
import com.sobhan.assembler.databinding.FragmentFifthBinding
import java.util.ArrayList

class FifthFragment : Fragment(R.layout.fragment_fifth) {

    private lateinit var binding: FragmentFifthBinding
    private var fetchedData: MutableList<String> = ArrayList()
    private var fetchedColor: MutableList<String> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFifthBinding.bind(view)
        val navHostFragment = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(binding.toolbar, navHostFragment)

        val bundle = arguments
        if (bundle != null){
            fetchedData = FifthFragmentArgs.fromBundle(bundle).product.toMutableList()
            fetchedColor = FifthFragmentArgs.fromBundle(bundle).colorCode.toMutableList()
        }
        Log.d("Fifth Frag", "onViewCreated: ${fetchedColor.size}")

        fetchedData.forEach{
            when (it) {
                "cap" -> {
                    binding.ivCap1.visibility = View.VISIBLE
                }
                "glass" -> {
                    binding.ivGlass1.visibility = View.VISIBLE
                }
                "tshirt" -> {
                    binding.ivTshirt1.visibility = View.VISIBLE
                }
                "jeans" -> {
                    binding.ivJeans1.visibility = View.VISIBLE
                }
                "shoe" -> {
                    binding.ivShoe1.visibility = View.VISIBLE
                }
            }
        }

        fetchedColor.forEachIndexed{index, item ->
            when (index) {
                0 -> {
                    ImageViewCompat.setImageTintList(binding.ivCap1, ColorStateList.valueOf(item.toInt()))
                }
                1 -> {
                    ImageViewCompat.setImageTintList(binding.ivGlass1, ColorStateList.valueOf(item.toInt()))
                }
                2 -> {
                    ImageViewCompat.setImageTintList(binding.ivTshirt1, ColorStateList.valueOf(item.toInt()))
                }
                3 -> {
                    ImageViewCompat.setImageTintList(binding.ivJeans1, ColorStateList.valueOf(item.toInt()))
                }
                4 -> {
                    ImageViewCompat.setImageTintList(binding.ivShoe1, ColorStateList.valueOf(item.toInt()))
                }
            }
        }

        binding.fabAdd.setOnClickListener {
            val action = FifthFragmentDirections.actionFifthFragmentToFirstFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}