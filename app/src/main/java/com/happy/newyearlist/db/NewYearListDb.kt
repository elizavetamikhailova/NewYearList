package com.happy.newyearlist.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    RecordEntity::class
], version = 1, exportSchema = true)
abstract class NewYearListDb : RoomDatabase(){
    abstract fun recordDao() : RecordDao
}