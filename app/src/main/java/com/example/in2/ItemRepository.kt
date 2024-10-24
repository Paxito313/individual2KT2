package com.example.in2.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemRepository(private val itemDao: ItemDao) {

    val allItems: Flow<List<Item>> = itemDao.getAllItems()

    suspend fun insert(item: Item) {
        withContext(Dispatchers.IO) {
            itemDao.insertItem(item)
        }
    }

    suspend fun delete(item: Item) {
        withContext(Dispatchers.IO) {
            itemDao.deleteItem(item)
        }
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            itemDao.deleteAllItems()
        }
    }
}
