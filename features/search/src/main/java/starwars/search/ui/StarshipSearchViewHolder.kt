package starwars.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import starwars.data.model.Starship
import starwars.search.databinding.ItemSearchStarshipBinding

class StarshipSearchViewHolder(private val binding: ItemSearchStarshipBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        starship: Starship,
        resourceClickListener: ResourceClickListener
    ) {
        binding.starship = starship
        binding.resourceClickListener = resourceClickListener
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): StarshipSearchViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSearchStarshipBinding.inflate(inflater)
            return StarshipSearchViewHolder(binding)
        }
    }

}