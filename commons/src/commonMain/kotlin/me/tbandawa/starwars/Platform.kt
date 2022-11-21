package me.tbandawa.starwars

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform