package com.happy.newyearlist.repositories

import com.happy.newyearlist.db.RecordImpl
import com.happy.newyearlist.models.Record
import kotlinx.coroutines.flow.Flow

class RecordRepository (val recordImpl: RecordImpl){
    suspend fun insert(record: Record) : Long{
        return recordImpl.insert(record)
    }

    suspend fun update(record: Record) : Int{
        return recordImpl.update(record)
    }

    suspend fun delete(record: Record){
        record.id?.let {
            recordImpl.delete(record.id)
        }
    }

    fun loadRecords() : Flow<List<Record>>{
        return recordImpl.loadRecords()
    }
}