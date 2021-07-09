package me.tbandawa.android.commons.extensions

import android.net.UrlQuerySanitizer
import java.util.*

fun String.capitaliseFirst() : String {
    return this.replaceFirstChar {
        if (it.isLowerCase())
            it.titlecase(Locale.getDefault())
        else
            it.toString()
    }
}

fun String.getPageNumber() : Int {
    val sanitizer = UrlQuerySanitizer(this)
    return sanitizer.getValue("page").toInt()
}

fun String.getResourceId() : Int {
    return this.replace(Regex("[^0-9]"), "").toInt()
}