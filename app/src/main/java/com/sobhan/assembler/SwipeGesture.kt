package com.sobhan.assembler

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.sign


abstract class SwipeGesture(context: Context?) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.END or
            ItemTouchHelper.UP or
            ItemTouchHelper.DOWN,
    0){

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun interpolateOutOfBoundsScroll(
        recyclerView: RecyclerView,
        viewSize: Int,
        viewSizeOutOfBounds: Int,
        totalSize: Int,
        msSinceStartScroll: Long
    ): Int {
        val direction = sign(viewSizeOutOfBounds.toDouble()).toInt()
        return 10 * direction
    }



}