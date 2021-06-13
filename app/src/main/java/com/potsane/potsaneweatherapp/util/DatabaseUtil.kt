package com.potsane.potsaneweatherapp.util

fun needsToRefresh(lastSuccessfulPersistence: Long?): Boolean {
    val twoHours = 120 * 60 * 1000
    lastSuccessfulPersistence?.let {
        return System.currentTimeMillis() - it > twoHours
    } ?: run { return false }
}