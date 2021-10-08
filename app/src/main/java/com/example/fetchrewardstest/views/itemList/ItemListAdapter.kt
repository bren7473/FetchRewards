package com.example.fetchrewardstest.views.itemList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardstest.databinding.ItemItemsListBinding
import com.example.fetchrewardstest.domain.ItemListItem
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class ItemsListAdapter @Inject constructor(val clickListener: ClickListener) :
    ListAdapter<ItemListItem, ItemsListAdapter.ViewHolder>(ItemsListDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemItemsListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemListItem, clickListener: ClickListener) {
            binding.data = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemItemsListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ItemsListDiffCallback : DiffUtil.ItemCallback<ItemListItem>() {

    override fun areItemsTheSame(oldItem: ItemListItem, newItem: ItemListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemListItem, newItem: ItemListItem): Boolean {
        return oldItem == newItem
    }

}

class ClickListener @Inject constructor() {

    var onItemClick: ((ItemListItem) -> Unit)? = null

    fun onClick(data: ItemListItem) {
        onItemClick?.invoke(data)
    }
}