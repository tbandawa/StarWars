package starwars.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import starwars.data.model.Species
import starwars.search.databinding.ItemSearchSpeciesBinding

class SpeciesSearchViewHolder(private val binding: ItemSearchSpeciesBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        species: Species,
        resourceClickListener: ResourceClickListener
    ) {
        binding.species = species
        binding.resourceClickListener = resourceClickListener
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): SpeciesSearchViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSearchSpeciesBinding.inflate(inflater)
            return SpeciesSearchViewHolder(binding)
        }
    }

}