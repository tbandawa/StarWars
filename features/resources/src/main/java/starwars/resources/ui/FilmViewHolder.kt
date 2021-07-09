package starwars.resources.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import starwars.data.model.Film
import starwars.resources.databinding.ItemFilmBinding

class FilmViewHolder(private val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        film: Film
    ) {
        binding.film = film
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): FilmViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemFilmBinding.inflate(inflater)
            return FilmViewHolder(binding)
        }
    }

}