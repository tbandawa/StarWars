package starwars.search.ui

interface ResourcesClickListener {
    fun onNextClick(url: String)
    fun onPrevious(url: String)
    fun onResourceClick(url: String)
}