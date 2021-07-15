package starwars.resources.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import starwars.data.model.Species
import starwars.resources.databinding.ItemSpeciesBinding

class SpeciesViewHolder(private val binding: ItemSpeciesBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        species: Species,
        resourceClickListener: ResourcesClickListener
    ) {
        binding.species = species
        binding.resourceClickListener = resourceClickListener
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): SpeciesViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSpeciesBinding.inflate(inflater)
            return SpeciesViewHolder(binding)
        }
    }

}