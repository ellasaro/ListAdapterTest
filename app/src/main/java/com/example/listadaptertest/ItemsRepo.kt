package com.example.listadaptertest

object ItemsRepo {

    private var items = mutableListOf(
        SimpleItem(1),
        SimpleItem(2),
        SimpleItem(3),
        SimpleItem(4),
        SimpleItem(5)
    )

    fun getItems(): List<SimpleItem> {
        return items
    }

    fun addItemCount(itemId: Int) {
        items.find { it.itemId == itemId }?.let {
            it.itemClickCount += 1
        }
    }

}