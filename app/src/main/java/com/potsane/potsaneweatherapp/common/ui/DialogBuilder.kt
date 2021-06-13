package com.potsane.potsaneweatherapp.common.ui

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

object DialogBuilder {

    fun showNetworkFailureDialog(context: Context, clickListener: DialogInterface.OnClickListener) {
        showDialog(
            "Internet Connection",
            "No network, please ensure that your are connected to the internet",
            "Connect",
            context,
            clickListener
        )
    }

    fun showDeleteLocationDialog(context: Context, clickListener: DialogInterface.OnClickListener) {
        showDialog(
            "Delete locations",
            "Are you sure you want to remove your location?",
            "Delete",
            context,
            clickListener
        )
    }

    fun showMissingPermissionsDialog(
        context: Context,
        clickListener: DialogInterface.OnClickListener,
        onNegativeClickListener: DialogInterface.OnClickListener
    ) {
        showDialog(
            "Permissions",
            "Please grant location permission before proceeding",
            "Grant",
            context,
            clickListener,
            "Exit app",
            onNegativeClickListener
        )
    }

    private fun showDialog(
        dialogTitle: String,
        dialogMessage: String,
        dialogPositiveButtonText: String,
        context: Context,
        onClickListener: DialogInterface.OnClickListener,
        dialogNegativeButtonText: String = "Cancel",
        onNegativeClickListener: DialogInterface.OnClickListener? = null
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(dialogMessage)
            .setTitle(dialogTitle)
        builder.setNegativeButton(dialogNegativeButtonText, onNegativeClickListener)
        builder.setPositiveButton(dialogPositiveButtonText, onClickListener)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}