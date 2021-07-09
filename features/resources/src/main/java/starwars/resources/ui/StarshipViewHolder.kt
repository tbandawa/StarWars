package starwars.resources.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import starwars.data.model.Starship
import starwars.resources.databinding.ItemStarshipBinding

class StarshipViewHolder(private val binding: ItemStarshipBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        starship: Starship,
        resourceClickListener: ResourceClickListener
    ) {
        binding.starship = starship
        binding.resourceClickListener = resourceClickListener
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): StarshipViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemStarshipBinding.inflate(inflater)
            return StarshipViewHolder(binding)
        }
    }

}