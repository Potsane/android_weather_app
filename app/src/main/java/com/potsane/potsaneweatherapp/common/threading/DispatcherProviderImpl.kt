package com.potsane.potsaneweatherapp.common.threading

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatcherProviderImpl : DispatcherProvider {
    override fun io(): CoroutineDispatcher = Dispatchers.IO

    override fun main(): CoroutineDispatcher = Dispatchers.Main

    override fun computation(): CoroutineDispatcher = Dispatchers.Default
}