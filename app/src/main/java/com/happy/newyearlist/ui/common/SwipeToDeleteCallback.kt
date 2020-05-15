package com.happy.newyearlist.ui.common

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.happy.newyearlist.ui.RecordAdapter

class SwipeToDeleteCallback (private val adapter: RecordAdapter): ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        val position = viewHolder.adapterPosition
        adapter.recentlyDeletedItem = adapter.items[position]
        adapter.callback.delete(adapter.items[position])
        adapter.showUndoSnackBar()

    }


}