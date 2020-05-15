package com.happy.newyearlist.db

import androidx.room.*
import com.happy.newyearlist.models.Record
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: RecordEntity) : Long

    @Update
    suspend fun update(record: RecordEntity) : Int

    @Query("DELETE FROM record WHERE id = :recordId")
    suspend fun delete (recordId : Long)

    @Query("SELECT * FROM record")
    fun loadRecords() : Flow<List<RecordEntity>>
}