package starwars.home.ui

import starwars.data.model.Item

interface ItemClickListener {
    fun onClick(item: Item)
}