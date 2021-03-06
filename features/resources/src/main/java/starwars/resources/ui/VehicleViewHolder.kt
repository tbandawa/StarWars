package starwars.resources.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import starwars.data.model.Vehicle
import starwars.resources.databinding.ItemVehicleBinding

class VehicleViewHolder(private val binding: ItemVehicleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        vehicle: Vehicle,
        resourcesClickListener: ResourcesClickListener
    ) {
        binding.vehicle = vehicle
        binding.resourcesClickListener = resourcesClickListener
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): VehicleViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemVehicleBinding.inflate(inflater)
            return VehicleViewHolder(binding)
        }
    }

}