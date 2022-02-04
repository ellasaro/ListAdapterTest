package com.example.listadaptertest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _items = MutableLiveData<List<SimpleItem>>()
    val items: LiveData<List<SimpleItem>>
        get() = _items

    init {
        _items.value = ItemsRepo.getItems()
    }

    fun onItemClick(itemId: Int) {
        ItemsRepo.addItemCount(itemId)
        _items.value = ItemsRepo.getItems()
    }
}