package starwars.resources.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import starwars.data.model.Planet
import starwars.resources.databinding.ItemPlanetBinding

class PlanetViewHolder(private val binding: ItemPlanetBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        planet: Planet,
        resourcesClickListener: ResourcesClickListener
    ) {
        binding.planet = planet
        binding.resourcesClickListener = resourcesClickListener
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): PlanetViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemPlanetBinding.inflate(inflater)
            return PlanetViewHolder(binding)
        }
    }

}