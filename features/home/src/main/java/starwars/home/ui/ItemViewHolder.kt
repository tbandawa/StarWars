package starwars.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import starwars.data.model.Item
import starwars.home.databinding.ItemResourceBinding

class ItemViewHolder(private val binding: ItemResourceBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: Item,
        itemClickListener: ItemClickListener
    ) {
        binding.item = item
        binding.itemClickListener = itemClickListener
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): ItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemResourceBinding.inflate(inflater)
            return ItemViewHolder(binding)
        }
    }

}