package starwars.home.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import starwars.data.model.Item

class ItemAdapter(
    private var itemList: List<Item>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(itemList[position], itemClickListener)
    }

    override fun getItemCount(): Int = itemList.size
}