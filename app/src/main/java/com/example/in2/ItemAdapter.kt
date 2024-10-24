package com.example.in2.ui.showitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.in2.data.Item
import com.example.in2.databinding.ItemListContentBinding

class ItemAdapter(private val onItemCheck: (Item, Boolean) -> Unit) :
    ListAdapter<Item, ItemAdapter.ItemViewHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, onItemCheck)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class ItemViewHolder(
        private val binding: ItemListContentBinding,
        private val onItemCheck: (Item, Boolean) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.itemName.text = item.name
            binding.itemPrice.text = item.price.toString()
            binding.itemQuantity.text = item.quantity.toString()
            binding.checkBoxSelect.setOnCheckedChangeListener { _, isChecked ->
                onItemCheck(item, isChecked)
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}
