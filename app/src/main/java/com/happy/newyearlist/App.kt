package com.happy.newyearlist

import android.app.Application
import androidx.room.Room
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.happy.newyearlist.db.NewYearListDb


class App : Application(){

    private var database: NewYearListDb? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, NewYearListDb::class.java!!, "database")
            .build()
    }


    fun getDatabase(): NewYearListDb? {
        return database
    }

    companion object {
        lateinit var instance: App
            private set
    }
}