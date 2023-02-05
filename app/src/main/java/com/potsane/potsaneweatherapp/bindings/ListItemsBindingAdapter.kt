package com.potsane.potsaneweatherapp.bindings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.potsane.potsaneweatherapp.common.listener.OnLocationItemClickListener
import com.potsane.potsaneweatherapp.BR

@BindingAdapter(
    value = [
        "listItems",
        "listItemsLayout",
        "itemsClickListener"
    ],
    requireAll = false
)
fun <T> setForecastItems(
    viewGroup: ViewGroup?,
    entries: List<T>?,
    layoutId: Int?,
    onLocationItemClickListener: OnLocationItemClickListener? = null
) {
    viewGroup?.removeAllViews()
    if (entries.isNullOrEmpty()) {
        return
    }
    val inflater = LayoutInflater.from(viewGroup?.context)
    for (i in entries.indices) {
        val item = entries[i]
        val binding = layoutId?.let {
            DataBindingUtil.inflate<ViewDataBinding>(
                inflater,
                it,
                viewGroup,
                true
            )
        }
        onLocationItemClickListener?.let {
            binding?.setVariable(BR.clickListener, it)
        }
        binding?.setVariable(BR.item, item)
        binding?.executePendingBindings()
    }
}
