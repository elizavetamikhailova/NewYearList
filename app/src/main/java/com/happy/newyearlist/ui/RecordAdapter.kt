package com.happy.newyearlist.ui

import android.content.Context
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.happy.newyearlist.databinding.RecordItemBinding
import com.happy.newyearlist.models.Record
import com.happy.newyearlist.ui.common.OnSwipeListener
import com.happy.newyearlist.ui.common.RecordDiffUtilsCallbacks
import com.google.android.material.snackbar.Snackbar



class RecordAdapter(val callback : Callbacks) : RecyclerView.Adapter<RecordAdapter.ViewHolder>() {

    var items = listOf<Record>()
    lateinit var context : Context

    lateinit var viewForSnackBar : View

    lateinit var recentlyDeletedItem : Record


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        viewForSnackBar = binding.root
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], callback)


    fun replaceData(list: List<Record>) {
        val diffUtils = RecordDiffUtilsCallbacks(items, list)
        val calcDiffResult = DiffUtil.calculateDiff(diffUtils)
        items = list
        calcDiffResult.dispatchUpdatesTo(this)
    }

    fun showUndoSnackBar(){
        val snackbar = Snackbar.make(
            viewForSnackBar, "Record deleted",
            Snackbar.LENGTH_LONG
        )
        snackbar.setAction("Cancel", { v -> callback.insertRecord(record = recentlyDeletedItem) })
        snackbar.show()
    }


    class ViewHolder(private val binding : ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(record : Record, callback: Callbacks){
            when(binding){
                is RecordItemBinding -> binding.run {
                    this.record = record
                    checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                        callback.done(record.copy(isDone = isChecked))
                    }

//                    mainLayout.setOnTouchListener(
//                        object : OnSwipeListener(){
//                            override fun onSwipeLeft() {
//                                super.onSwipeLeft()
//                                callback.delete(record)
//                            }
//                        }
//                    )
                }
            }
            binding.executePendingBindings()
        }
    }
}