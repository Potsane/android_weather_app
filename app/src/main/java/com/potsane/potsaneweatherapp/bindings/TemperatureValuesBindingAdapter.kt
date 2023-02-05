package com.potsane.potsaneweatherapp.bindings

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("degreeToText")
fun degreeToText(textView: TextView,value: Double?) {
    value?.let {
        val rounded = it.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
        textView.text = rounded.toString() + "\u00B0"
    } ?: run {
        textView.text = ""
    }
}

@BindingAdapter("dayNameFromDate")
fun getDayNameFromDate(textView: TextView,value: Long) {
    val sdf = SimpleDateFormat("EEEE")
    val dateFormat = Date(value * 1000)
    val weekday = sdf.format(dateFormat)

    val label = if (!isToday(value)) weekday else "Today"
    textView.text = label
}

fun isToday(date: Long): Boolean {
    val epochInMillis: Long = date * 1000
    val now = Calendar.getInstance()
    val timeToCheck = Calendar.getInstance()
    timeToCheck.timeInMillis = epochInMillis
    if (now[Calendar.YEAR] === timeToCheck[Calendar.YEAR]) {
        if (now[Calendar.DAY_OF_YEAR] === timeToCheck[Calendar.DAY_OF_YEAR]) {
            return true
        }
    }
    return false
}