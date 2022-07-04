package com.sobhan.assembler.UI

import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.view.DragEvent
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.children
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sobhan.assembler.R
import com.sobhan.assembler.SwipeGesture
import com.sobhan.assembler.adapters.AssembledRecyclerviewAdapter
import com.sobhan.assembler.databinding.FragmentThirdBinding
import java.util.*

class ThirdFragment : Fragment(R.layout.fragment_third) {

    private lateinit var binding: FragmentThirdBinding
    private var fetchedData: IntArray? = null
    private lateinit var mAssembleAdapter: AssembledRecyclerviewAdapter
    val list: MutableList<String> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentThirdBinding.bind(view)
        val navHostFragment = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(binding.toolbar, navHostFragment)

        val bundle = arguments
        if (bundle != null){
            fetchedData = ThirdFragmentArgs.fromBundle(bundle).itemList
        }

        fetchedData!!.forEach{
            when (it) {
                0 -> binding.ivCap.visibility = View.VISIBLE
                1 -> binding.ivGlass.visibility = View.VISIBLE
                2 -> binding.ivTshirt.visibility = View.VISIBLE
                3 -> binding.ivJeans.visibility = View.VISIBLE
                4 -> binding.ivShoe.visibility = View.VISIBLE
            }
        }

        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = layoutManager
        mAssembleAdapter = AssembledRecyclerviewAdapter(fetchedData!!)
        binding.recyclerView.adapter = mAssembleAdapter

        val swipeGesture = object : SwipeGesture(context) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPos = viewHolder.adapterPosition
                val toPos = target.adapterPosition

                Collections.swap(fetchedData!!.toMutableList(),fromPos,toPos)
                mAssembleAdapter.notifyItemMoved(fromPos,toPos)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                TODO("Not yet implemented")
            }

        }
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding.recyclerView)

        binding.fabAdd.setOnClickListener {
            list.clear()
            for (childView in binding.llCentre.children) {
                when (childView.id) {
                    R.id.ivCap -> list.add("cap")
                    R.id.ivGlass -> list.add("glass")
                    R.id.ivTshirt -> list.add("tshirt")
                    R.id.ivJeans -> list.add("jeans")
                    R.id.ivShoe -> list.add("shoe")
                }
            }
            if(list.size > 0){
                val action = ThirdFragmentDirections.actionThirdFragmentToForthFragment(list.toTypedArray())
                Navigation.findNavController(it).navigate(action)
            }
            else
                Snackbar.make(requireView(),
                    "No Items selected. Kindly drag and drop minimum one item to the centre of the screen.",
                    Snackbar.LENGTH_LONG).
                show()

        }

        binding.llCentre.setOnDragListener(dragListener)
        binding.llLeft.setOnDragListener(dragListener)
        binding.llRight.setOnDragListener(dragListener)

        binding.ivCap.setOnLongClickListener {
            val clipText = "Cap dragged"
            val item = ClipData.Item(clipText)
            val mimeType = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText,mimeType,item)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data,dragShadowBuilder,it,0)
            it.visibility = View.INVISIBLE

            true
        }

        binding.ivGlass.setOnLongClickListener {
            val clipText = "Glass dragged"
            val item = ClipData.Item(clipText)
            val mimeType = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText,mimeType,item)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data,dragShadowBuilder,it,0)
            it.visibility = View.INVISIBLE

            true
        }

        binding.ivTshirt.setOnLongClickListener {
            val clipText = "T-shirt dragged"
            val item = ClipData.Item(clipText)
            val mimeType = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText,mimeType,item)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data,dragShadowBuilder,it,0)
            it.visibility = View.INVISIBLE

            true
        }

        binding.ivJeans.setOnLongClickListener {
            val clipText = "Jeans dragged"
            val item = ClipData.Item(clipText)
            val mimeType = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText,mimeType,item)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data,dragShadowBuilder,it,0)
            it.visibility = View.INVISIBLE

            true
        }

        binding.ivShoe.setOnLongClickListener {
            val clipText = "Shoe dragged"
            val item = ClipData.Item(clipText)
            val mimeType = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText,mimeType,item)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data,dragShadowBuilder,it,0)
            it.visibility = View.INVISIBLE

            true
        }

    }

    val dragListener = View.OnDragListener { view, event ->
        when(event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> {
                true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                val item = event.clipData.getItemAt(0)
                val dragData = item.text
                Toast.makeText(requireContext(), dragData, Toast.LENGTH_SHORT).show()
                view.invalidate()

                val v = event.localState as View
                val owner = v.parent as ViewGroup
                owner.removeView(v)

                val destination = view as LinearLayout
                if (v.parent != null) {
                    val viewGroup = v.parent as ViewGroup
                    viewGroup.removeView(v)
                }
                destination.addView(v)
                v.visibility = View.VISIBLE

                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                view.invalidate()
                true
            }
            else -> false
        }

    }

}