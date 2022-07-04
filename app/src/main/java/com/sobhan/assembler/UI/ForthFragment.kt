package com.sobhan.assembler.UI

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.sobhan.assembler.R
import com.sobhan.assembler.adapters.AssembledRecyclerviewAdapter
import com.sobhan.assembler.databinding.FragmentForthBinding
import java.util.ArrayList


class ForthFragment : Fragment(R.layout.fragment_forth) {

    private lateinit var binding: FragmentForthBinding
    private var fetchedData: MutableList<String> = ArrayList()
    private var colorCode: MutableList<String> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForthBinding.bind(view)
        val navHostFragment = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(binding.toolbar, navHostFragment)

        val bundle = arguments
        if (bundle != null){
            fetchedData = ForthFragmentArgs.fromBundle(bundle).assembledItems.toMutableList()
        }

        colorCode.add("-16777216")
        colorCode.add("-16777216")
        colorCode.add("-16777216")
        colorCode.add("-16777216")
        colorCode.add("-16777216")

        fetchedData!!.forEach{
            when (it) {
                "cap" -> {
                    binding.ivCap1.visibility = View.VISIBLE
                    binding.btnCap.visibility = View.VISIBLE
                }
                "glass" -> {
                    binding.ivGlass1.visibility = View.VISIBLE
                    binding.btnGlass.visibility = View.VISIBLE
                }
                "tshirt" -> {
                    binding.ivTshirt1.visibility = View.VISIBLE
                    binding.btnTshirt.visibility = View.VISIBLE
                }
                "jeans" -> {
                    binding.ivJeans1.visibility = View.VISIBLE
                    binding.btnJeans.visibility = View.VISIBLE
                }
                "shoe" -> {
                    binding.ivShoe1.visibility = View.VISIBLE
                    binding.btnShoe.visibility = View.VISIBLE
                }
            }
        }

        binding.fabAdd.setOnClickListener {
            val action = ForthFragmentDirections.actionForthFragmentToFifthFragment(fetchedData.toTypedArray(),colorCode.toTypedArray())
            Navigation.findNavController(it).navigate(action)
            colorCode.clear()
        }

        binding.btnCap.setOnClickListener {
            colorPicker(binding.ivCap1)
            binding.ivCap1.setImageResource(R.drawable.cap)
        }

        binding.btnGlass.setOnClickListener {
            colorPicker(binding.ivGlass1)
            binding.ivGlass1.setImageResource(R.drawable.glass)
        }

        binding.btnTshirt.setOnClickListener {
            colorPicker(binding.ivTshirt1)
            binding.ivTshirt1.setImageResource(R.drawable.tshirt)
        }

        binding.btnJeans.setOnClickListener {
            colorPicker(binding.ivJeans1)
            binding.ivJeans1.setImageResource(R.drawable.jeans)
        }

        binding.btnShoe.setOnClickListener {
            colorPicker(binding.ivShoe1)
            binding.ivShoe1.setImageResource(R.drawable.shoe)
        }
    }

    private fun colorPicker(image: ImageView) {

        MaterialColorPickerDialog
            .Builder(requireContext())
            .setTitle("Pick Theme")
            .setColorShape(ColorShape.SQAURE)
            .setColorSwatch(ColorSwatch._300)
            .setDefaultColor(R.color.black)
            .setColorListener { color, colorHex ->
                ImageViewCompat.setImageTintList(image, ColorStateList.valueOf(color))
                Log.d("Forth Screen", "colorPicker: $color")
                when (image) {
                    binding.ivCap1 -> {
                        colorCode.removeAt(0)
                        colorCode.add(0, color.toString())
                    }
                    binding.ivGlass1 -> {
                        colorCode.removeAt(1)
                        colorCode.add(1,color.toString())
                    }
                    binding.ivTshirt1 -> {
                        colorCode.removeAt(2)
                        colorCode.add(2,color.toString())
                    }
                    binding.ivJeans1 -> {
                        colorCode.removeAt(3)
                        colorCode.add(3,color.toString())
                    }
                    binding.ivShoe1 -> {
                        colorCode.removeAt(4)
                        colorCode.add(4,color.toString())
                    }
                }
            }
            .show()

    }

}