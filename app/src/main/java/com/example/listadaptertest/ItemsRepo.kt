package com.example.listadaptertest

object ItemsRepo {

    private var items = mutableListOf(
        SimpleItem(1),
        SimpleItem(2),
        SimpleItem(3),
        SimpleItem(4),
        SimpleItem(5)
    )

    /**
     * Originally this would return the mutable list of items, it was suggested to always
     * submit an immutable list
     */
    fun getItems(): List<SimpleItem> {
        return items.toList()
    }

    /**
     * The original method when SimpleItem's itemClickCount was mutable
     */
//    fun addItemCount(itemId: Int) {
//        items.find { it.itemId == itemId }?.let {
//            it.itemClickCount += 1
//        }
//    }

    /**
     * How the method changed when SimpleItem's itemClickCount became immutable
     */
    fun addItemCount(itemId: Int) {
        val index = items.indexOfFirst { it.itemId == itemId }
        if (index != -1) {
            val oldItem = items[index]
            val newItem = oldItem.copy(itemClickCount = oldItem.itemClickCount + 1)
            items[index] = newItem
        }
    }
}