package com.happy.newyearlist.db

import com.happy.newyearlist.db.converters.toRecord
import com.happy.newyearlist.db.converters.toRecordEntity
import com.happy.newyearlist.models.Record
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecordImpl(val dao : RecordDao) {

    suspend fun insert(record : Record) : Long{
        return dao.insert(record.toRecordEntity())
    }

    suspend fun update(record: Record) : Int{
        return dao.update(record.toRecordEntity())
    }

    suspend fun delete(recordId : Long) {
        dao.delete(recordId)
    }

    fun loadRecords() : Flow<List<Record>>{
        return dao.loadRecords().map {
            it.map {
                it.toRecord()
            }
        }
    }
}