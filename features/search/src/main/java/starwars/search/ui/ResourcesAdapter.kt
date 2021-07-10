package starwars.search.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import starwars.data.model.*

class ResourcesAdapter(
    private var resourceType: String,
    private var resourceList: List<Any>,
    private val resourceClickListener: ResourceClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (resourceType) {
            "people" -> {
                PersonSearchViewHolder.create(parent)
            }
            "planets" -> {
                PlanetSearchViewHolder.create(parent)
            }
            "films" -> {
                FilmSearchViewHolder.create(parent)
            }
            "species" -> {
                SpeciesSearchViewHolder.create(parent)
            }
            "vehicles" -> {
                VehicleSearchViewHolder.create(parent)
            }
            "starships" -> {
                StarshipSearchViewHolder.create(parent)
            }
            else -> {
                PersonSearchViewHolder.create(parent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (resourceType == "people")
            (holder as PersonSearchViewHolder).bind(resourceList[position] as Person, resourceClickListener)
        if (resourceType == "planets")
            (holder as PlanetSearchViewHolder).bind(resourceList[position] as Planet, resourceClickListener)
        if (resourceType == "films")
            (holder as FilmSearchViewHolder).bind(resourceList[position] as Film, resourceClickListener)
        if (resourceType == "species")
            (holder as SpeciesSearchViewHolder).bind(resourceList[position] as Species, resourceClickListener)
        if (resourceType == "vehicles")
            (holder as VehicleSearchViewHolder).bind(resourceList[position] as Vehicle, resourceClickListener)
        if (resourceType == "starships")
            (holder as StarshipSearchViewHolder).bind(resourceList[position] as Starship, resourceClickListener)
    }

    override fun getItemCount(): Int = resourceList.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int =  position

}

class ResourceClickListener(val resourceClickListener: (url: String) -> Unit) {
    fun onClick(url: String) = resourceClickListener(url)
}
