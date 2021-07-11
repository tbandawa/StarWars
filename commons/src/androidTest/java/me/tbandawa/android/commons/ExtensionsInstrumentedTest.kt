package me.tbandawa.android.commons

import androidx.test.ext.junit.runners.AndroidJUnit4
import me.tbandawa.android.commons.extensions.getPageNumber

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExtensionsInstrumentedTest {

    @Test
    fun pageNumber_isCorrect() {
        val url = "https://swapi.dev/api/people/?page=2"
        val pageNumber = url.getPageNumber()
        println(pageNumber)
    }

}