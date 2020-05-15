package com.happy.newyearlist.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record")
data class RecordEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Long?,
    @ColumnInfo(name = "record_title")
    val recordTitle : String,
    @ColumnInfo(name = "is_done")
    val isDone : Boolean
)