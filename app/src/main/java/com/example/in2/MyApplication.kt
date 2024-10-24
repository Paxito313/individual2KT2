package com.example.in2

import android.app.Application
import androidx.room.Room
import com.example.in2.data.AppDatabase
import com.example.in2.data.ItemRepository

class MyApplication : Application() {

    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    val repository: ItemRepository by lazy {
        ItemRepository(database.itemDao())
    }
}
