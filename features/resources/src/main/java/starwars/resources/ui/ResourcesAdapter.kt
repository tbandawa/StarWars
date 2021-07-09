package starwars.resources.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import starwars.data.model.*

class OrderAdapter(
    private var resourceType: String,
    private var resourceList: List<Any>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (resourceType) {
            "people" -> {
                PersonViewHolder.create(parent)
            }
            "planets" -> {
                PlanetViewHolder.create(parent)
            }
            "films" -> {
                FilmViewHolder.create(parent)
            }
            "species" -> {
                SpeciesViewHolder.create(parent)
            }
            "vehicles" -> {
                VehicleViewHolder.create(parent)
            }
            "starships" -> {
                StarshipViewHolder.create(parent)
            }
            else -> {
                PersonViewHolder.create(parent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (resourceType == "people")
            (holder as PersonViewHolder).bind(resourceList[position] as Person)
        if (resourceType == "planets")
            (holder as PlanetViewHolder).bind(resourceList[position] as Planet)
        if (resourceType == "films")
            (holder as FilmViewHolder).bind(resourceList[position] as Film)
        if (resourceType == "species")
            (holder as SpeciesViewHolder).bind(resourceList[position] as Species)
        if (resourceType == "vehicles")
            (holder as VehicleViewHolder).bind(resourceList[position] as Vehicle)
        if (resourceType == "starships")
            (holder as StarshipViewHolder).bind(resourceList[position] as Starship)
    }

    override fun getItemCount(): Int = resourceList.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int =  position

}
