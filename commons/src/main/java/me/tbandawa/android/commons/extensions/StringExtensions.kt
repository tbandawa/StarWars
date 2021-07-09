package me.tbandawa.android.commons.extensions

import java.util.*

fun String.capitaliseFirst() : String {
    return this.replaceFirstChar {
        if (it.isLowerCase())
            it.titlecase(Locale.getDefault())
        else
            it.toString()
    }
}