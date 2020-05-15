package com.happy.newyearlist.ui.common

import androidx.recyclerview.widget.DiffUtil
import com.happy.newyearlist.models.Record

class RecordDiffUtilsCallbacks(
        val oldList: List<Record>,
        val newList: List<Record>
) : DiffUtil.Callback(){

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id && oldList[oldItemPosition].isDone == newList[newItemPosition].isDone
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].isDone == newList[newItemPosition].isDone &&
                oldList[oldItemPosition].recordTitle == newList[newItemPosition].recordTitle

    }

}