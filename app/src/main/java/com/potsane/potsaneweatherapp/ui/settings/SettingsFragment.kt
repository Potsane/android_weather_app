package com.potsane.potsaneweatherapp.ui.settings

import androidx.lifecycle.ViewModelProvider
import com.potsane.potsaneweatherapp.R
import com.potsane.potsaneweatherapp.databinding.FragmentSettingsBinding
import com.potsane.potsaneweatherapp.ui.base.BaseWeatherAppFragment

class SettingsFragment : BaseWeatherAppFragment<SettingsViewModel, FragmentSettingsBinding>() {

    override fun getLayoutId() = R.layout.fragment_settings

    override fun createViewModel(): SettingsViewModel {
        return ViewModelProvider(this, SettingsViewModel.Factory())
            .get(SettingsViewModel::class.java)
    }
}