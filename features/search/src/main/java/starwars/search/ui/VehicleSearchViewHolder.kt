package starwars.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import starwars.data.model.Vehicle
import starwars.search.databinding.ItemSearchVehicleBinding

class VehicleSearchViewHolder(private val binding: ItemSearchVehicleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        vehicle: Vehicle,
        resourceClickListener: ResourceClickListener
    ) {
        binding.vehicle = vehicle
        binding.resourceClickListener = resourceClickListener
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): VehicleSearchViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSearchVehicleBinding.inflate(inflater)
            return VehicleSearchViewHolder(binding)
        }
    }

}