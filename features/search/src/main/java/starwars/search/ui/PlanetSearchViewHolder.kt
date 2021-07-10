package starwars.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import starwars.data.model.Planet
import starwars.search.databinding.ItemSearchPlanetBinding

class PlanetSearchViewHolder(private val binding: ItemSearchPlanetBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        planet: Planet,
        resourceClickListener: ResourceClickListener
    ) {
        binding.planet = planet
        binding.resourceClickListener = resourceClickListener
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): PlanetSearchViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSearchPlanetBinding.inflate(inflater)
            return PlanetSearchViewHolder(binding)
        }
    }

}