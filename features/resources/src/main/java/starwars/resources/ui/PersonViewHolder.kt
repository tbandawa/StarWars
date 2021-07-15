package starwars.resources.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import starwars.data.model.Person
import starwars.resources.databinding.ItemPersonBinding

class PersonViewHolder(private val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        person: Person,
        resourcesClickListener: ResourcesClickListener
    ) {
        binding.person = person
        binding.resourcesClickListener = resourcesClickListener
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): PersonViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemPersonBinding.inflate(inflater)
            return PersonViewHolder(binding)
        }
    }

}