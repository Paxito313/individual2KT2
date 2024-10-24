package com.example.in2.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert
    suspend fun insertItem(item: Item): Long

    @Delete
    suspend fun deleteItem(item: Item): Int

    @Query("DELETE FROM items")
    suspend fun deleteAllItems(): Int

    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<Item>>
}
