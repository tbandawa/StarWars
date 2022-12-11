package starwars.data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform