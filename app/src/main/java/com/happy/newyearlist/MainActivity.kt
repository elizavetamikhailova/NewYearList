package com.happy.newyearlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.happy.newyearlist.ui.NewYearListRecordsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_content, NewYearListRecordsFragment())
            .commit()
    }
}
