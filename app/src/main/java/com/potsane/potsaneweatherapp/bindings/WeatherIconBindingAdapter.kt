package com.potsane.potsaneweatherapp.bindings

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.potsane.potsaneweatherapp.entity.api.Weather
import com.potsane.potsaneweatherapp.util.getConditionIcon

@BindingAdapter("weatherIcon")
fun weatherIcon(imageView: ImageView, weatherItems: List<Weather>?) {

    val currentWeatherItem = weatherItems?.firstOrNull()
    if (currentWeatherItem == null) {
        imageView.visibility = View.GONE
    } else {
        imageView.visibility = View.VISIBLE
        imageView.setBackgroundResource(getConditionIcon(currentWeatherItem.icon))
    }
}

@BindingAdapter("drawableIcon")
fun drawableIcon(imageView: ImageView, iconResId: Int) {
    if (iconResId == 0) {
        imageView.visibility = View.GONE
    } else {
        imageView.visibility = View.VISIBLE
        imageView.setBackgroundResource(iconResId)
    }
}