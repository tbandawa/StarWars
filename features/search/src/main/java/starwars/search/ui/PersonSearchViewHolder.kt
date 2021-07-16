package starwars.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import starwars.data.model.Person
import starwars.search.databinding.ItemSearchPersonBinding

class PersonSearchViewHolder(private val binding: ItemSearchPersonBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        person: Person,
        resourcesClickListener: ResourcesClickListener
    ) {
        binding.person = person
        binding.resourcesClickListener = resourcesClickListener
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): PersonSearchViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSearchPersonBinding.inflate(inflater)
            return PersonSearchViewHolder(binding)
        }
    }

}