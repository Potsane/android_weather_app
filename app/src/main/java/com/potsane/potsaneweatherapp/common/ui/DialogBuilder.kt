package com.potsane.potsaneweatherapp.common.ui

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

fun showDialog(
    dialogTitle: String,
    dialogMessage: String,
    dialogPositiveButtonText: String,
    context: Context,
    onClickListener: DialogInterface.OnClickListener
) {
    val builder = AlertDialog.Builder(context)
    builder.setMessage(dialogMessage)
        .setTitle(dialogTitle)
    builder.setNegativeButton("Cancel", null)
    builder.setPositiveButton(dialogPositiveButtonText, onClickListener)

    val dialog: AlertDialog = builder.create()
    dialog.show()
}