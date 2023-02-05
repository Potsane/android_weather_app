package com.potsane.potsaneweatherapp.common.threading

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun io(): CoroutineDispatcher

    fun main(): CoroutineDispatcher

    fun computation(): CoroutineDispatcher
}