package starwars.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import starwars.data.model.Film
import starwars.search.databinding.ItemSearchFilmBinding

class FilmSearchViewHolder(private val binding: ItemSearchFilmBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        film: Film,
        resourcesClickListener: ResourcesClickListener
    ) {
        binding.film = film
        binding.resourcesClickListener = resourcesClickListener
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): FilmSearchViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSearchFilmBinding.inflate(inflater)
            return FilmSearchViewHolder(binding)
        }
    }

}