package com.potsane.potsaneweatherapp.common.ui

import android.view.Gravity
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.potsane.potsaneweatherapp.R

fun showSnackBar(view: View, text: String) {
    val snackBar = Snackbar.make(
        view,
        text,
        Snackbar.LENGTH_INDEFINITE
    )
    snackBar.setAction("Got it") {
        snackBar.dismiss()
    }
    val layoutParams = snackBar.view.layoutParams as CoordinatorLayout.LayoutParams
    layoutParams.anchorId = R.id.bottom_nav
    layoutParams.anchorGravity = Gravity.TOP
    layoutParams.gravity = Gravity.TOP
    layoutParams.gravity = Gravity.TOP
    snackBar.view.layoutParams = layoutParams
    snackBar.show()
}