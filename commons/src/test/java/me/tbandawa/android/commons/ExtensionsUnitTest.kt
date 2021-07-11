package me.tbandawa.android.commons

import me.tbandawa.android.commons.extensions.capitaliseFirst
import org.junit.Test

import org.junit.Assert.*

class ExtensionsUnitTest {

    @Test
    fun capitaliseFirst_isCorrect() {
        val str = "string"
        val capitalised = str.capitaliseFirst()
        assertEquals("S", capitalised[0].toString())
    }

}