package me.tbandawa.android.commons

import me.tbandawa.android.commons.extensions.capitaliseFirst
import me.tbandawa.android.commons.extensions.getResourceId
import me.tbandawa.android.commons.extensions.getResourceType
import org.junit.Test

import org.junit.Assert.*

class ExtensionsUnitTest {

    @Test
    fun capitaliseFirst_isCorrect() {
        val str = "string"
        val capitalised = str.capitaliseFirst()
        assertEquals("S", capitalised[0].toString())
    }

    @Test
    fun resourceId_isCorrect() {
        val url = "https://swapi.dev/api/people/2"
        val resourceId = url.getResourceId()
        assertEquals(2, resourceId)
    }

    @Test
    fun resourceType_isCorrect() {
        val url = "https://swapi.dev/api/people/2/"
        val resourceType = url.getResourceType()
        assertEquals("people", resourceType)
    }

}