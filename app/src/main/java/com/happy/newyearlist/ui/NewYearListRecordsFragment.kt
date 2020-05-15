package com.happy.newyearlist.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.happy.newyearlist.App

import com.happy.newyearlist.R
import com.happy.newyearlist.databinding.NewYearListRecordsFragmentBinding
import com.happy.newyearlist.db.RecordImpl
import com.happy.newyearlist.repositories.RecordRepository
import com.happy.newyearlist.ui.common.SwipeToDeleteCallback

class NewYearListRecordsFragment : Fragment() {

    lateinit var adapter  : RecordAdapter

    lateinit var binding : NewYearListRecordsFragmentBinding


    private lateinit var viewModel: NewYearListRecordsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.new_year_list_records_fragment,
            container,
            false
        )

        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewYearListRecordsViewModel::class.java)
        App.instance.getDatabase()?.recordDao()?.let {
            viewModel.recordRepository = RecordRepository(RecordImpl(it))
        }


        adapter = RecordAdapter(callback = viewModel.callbacks)

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(adapter))
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        binding.recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)

        binding.recyclerView.adapter = adapter

        binding.viewModel = viewModel
        viewModel.loadRecords()

        viewModel.records.observe(viewLifecycleOwner, Observer {
            it?.let {
                println("record is $it")
                adapter.replaceData(it)
            }
        })
    }

}
