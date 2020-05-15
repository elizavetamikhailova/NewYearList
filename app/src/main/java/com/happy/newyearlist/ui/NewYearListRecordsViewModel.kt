package com.happy.newyearlist.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.happy.newyearlist.models.Record
import com.happy.newyearlist.repositories.RecordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewYearListRecordsViewModel () : ViewModel(),  CoroutineScope by CoroutineScope (Dispatchers.IO) {

    lateinit var recordRepository: RecordRepository

    val records = MutableLiveData<List<Record>>()

    val newRecordTitle = ObservableField<String>()

    val callbacks =

    object : Callbacks {
        override fun insertRecord(record: Record) {
            insert(record)
        }

        override fun done(record: Record) {
            update(record)
        }

        override fun delete(record: Record) {
            deleteRecord(record)
        }
    }

    fun setRecordTitle(title: CharSequence){
        println("record title $title")
        if(title.toString() != "") newRecordTitle.set("$title")
    }

    fun insert(){
        launch {
            newRecordTitle.get()?.let{
                val id = recordRepository.insert(record =
                Record(
                    recordTitle = it,
                    isDone = false
                )
                )

                println("record id is $id")
                if (id != -1L){
                    newRecordTitle.set("")
                }
            }

        }
    }

    fun insert(record: Record){
        launch {
                val id = recordRepository.insert(record =
                Record(
                    recordTitle = record.recordTitle,
                    isDone = false
                )
                )

                println("record id is $id")
                if (id != -1L){
                    newRecordTitle.set("")
                }
            }
        }

    fun update(record: Record){
        launch {
            recordRepository.update(record = record)
        }
    }

    fun deleteRecord(record : Record){
        launch {
            recordRepository.delete(record)
        }
    }

    fun loadRecords(){
        launch {
            recordRepository.loadRecords().collect {
                records.postValue(it)
            }
        }
    }

}

interface Callbacks{
    fun done(record: Record)
    fun delete(record: Record)
    fun insertRecord(record: Record)
}