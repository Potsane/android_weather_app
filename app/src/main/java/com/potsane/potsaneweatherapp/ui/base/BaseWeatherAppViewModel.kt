package com.potsane.potsaneweatherapp.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseWeatherAppViewModel : ViewModel() {

    private val _uiEvents: MutableLiveData<Any> = MutableLiveData()
    val uiEvents: LiveData<Any> = _uiEvents

    protected fun postUiEvent(event: Any) {
        _uiEvents.postValue(event)
    }
}