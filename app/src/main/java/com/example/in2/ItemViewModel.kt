package com.example.in2.viewmodel

import androidx.lifecycle.*
import com.example.in2.data.Item
import com.example.in2.data.ItemRepository
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {

    val allItems: LiveData<List<Item>> = repository.allItems.asLiveData()

    fun insert(item: Item) = viewModelScope.launch {
        repository.insert(item)
    }

    fun delete(item: Item) = viewModelScope.launch {
        repository.delete(item)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}

class ItemViewModelFactory(private val repository: ItemRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
