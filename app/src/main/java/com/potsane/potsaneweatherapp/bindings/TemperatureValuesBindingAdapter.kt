package com.potsane.potsaneweatherapp.bindings

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.math.RoundingMode
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("degreeToText")
fun degreeToText(
    textView: TextView,
    value: Double?
) {
    value?.let {
        val rounded = it.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
        textView.text = rounded.toString() + "\u00B0"
    } ?: run {
        textView.text = ""
    }
}


@BindingAdapter("dayNameFromDate")
fun getDayNameFromDate(
    textView: TextView,
    value: Long
) {
    val calendar = Calendar.getInstance()
    val format = SimpleDateFormat("yyyy-MM-dd")

    try {
        val date: Date = format.parse((value * 1000).toString())
        calendar.time = date
        val nameDay =
            calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
        textView.text = nameDay
    } catch (e: ParseException) {
        textView.text = "Day"
    }
}