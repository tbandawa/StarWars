package me.tbandawa.android.commons.bindings

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.util.*

@BindingAdapter("resourceName")
fun resourceName(view: TextView, type: String) {
    view.text = type.replaceFirstChar {
        if (it.isLowerCase())
            it.titlecase(Locale.getDefault())
        else it.toString()
    }
}