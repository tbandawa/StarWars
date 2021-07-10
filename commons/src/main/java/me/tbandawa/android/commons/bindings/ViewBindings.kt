package me.tbandawa.android.commons.bindings

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("resourceName")
fun resourceName(view: TextView, type: String) {
    view.text = type.replaceFirstChar {
        if (it.isLowerCase())
            it.titlecase(Locale.getDefault())
        else it.toString()
    }
}

@BindingAdapter("pageNavigation")
fun pageNavigation(view: Button, url: String?) {
    url?.let {
        view.visibility = View.VISIBLE
    } ?: run {
        view.visibility = View.GONE
    }
}

@BindingAdapter("formatDate")
fun formatDate(view: TextView, time: String?) {
    time?.let {
        val inFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val outFormat = SimpleDateFormat("dd MMM yyyy")
        view.text = outFormat.format(inFormat.parse(it))
    }
}