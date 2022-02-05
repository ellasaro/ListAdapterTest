package com.example.listadaptertest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listadaptertest.databinding.AdapterSimpleItemBinding

class SimpleItemAdapter(private val itemClick: (item: SimpleItem) -> Unit) :
    ListAdapter<SimpleItem, SimpleItemViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SimpleItem>() {
            override fun areItemsTheSame(oldItem: SimpleItem, newItem: SimpleItem): Boolean {
                return oldItem.itemId == newItem.itemId
            }

            /**
             * If you would place a breakpoint here, originally, after modifying a item on the list,
             * your oldItem and newItem would be the same! Even if it was the first time submitting
             * the new value.
             */
            override fun areContentsTheSame(oldItem: SimpleItem, newItem: SimpleItem): Boolean {
                return oldItem == newItem
            }
        }

    }

    private val itemClickedAt: (Int) -> Unit = { position ->
        itemClick.invoke(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleItemViewHolder {
        val binding = AdapterSimpleItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return SimpleItemViewHolder(binding, itemClickedAt)
    }

    override fun onBindViewHolder(holder: SimpleItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class SimpleItemViewHolder(
    private val binding: AdapterSimpleItemBinding,
    private val itemClick: (position: Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            itemClick.invoke(adapterPosition)
        }
    }

    fun bind(item: SimpleItem) {
        binding.text1.text = item.itemId.toString()
        binding.text2.text = item.itemClickCount.toString()
    }
}