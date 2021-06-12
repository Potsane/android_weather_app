package com.potsane.potsaneweatherapp.ui.weather

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.provider.Settings
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.potsane.potsaneweatherapp.R
import com.potsane.potsaneweatherapp.common.showDialog
import com.potsane.potsaneweatherapp.databinding.FragmentWeatherBinding
import com.potsane.potsaneweatherapp.injection.Injector
import com.potsane.potsaneweatherapp.ui.base.BaseWeatherAppFragment
import com.potsane.potsaneweatherapp.util.LocationUtils
import com.potsane.potsaneweatherapp.util.LocationUtils.isOnline
import com.potsane.potsaneweatherapp.util.PermissionsUtil
import kotlinx.coroutines.launch

class WeatherFragment :
    BaseWeatherAppFragment<WeatherViewModel, FragmentWeatherBinding>() {

    override fun getLayoutId() = R.layout.fragment_weather

    override fun createViewModel(): WeatherViewModel {
        return ViewModelProvider(this, WeatherViewModel.Factory()).get(
            WeatherViewModel::class.java
        )
    }

    override fun onResume() {
        super.onResume()
        init()
    }

    private fun init() {
        if (isOnline(requireActivity())) {
            getLocation()
        } else {
            showDialog(
                getString(R.string.dialog_title_no_network),
                getString(R.string.dialog_message_no_network),
                getString(R.string.dialog_positive_button_no_network),
                requireContext(),
                DialogInterface.OnClickListener { _, _ ->
                    val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                    startActivity(intent)
                }
            )
        }
    }

    private fun getLocation() {
        if (!PermissionsUtil.hasLocationPermissions(requireContext())) {
            PermissionsUtil.requestLocationPermissions(
                requireActivity(),
                PermissionsUtil.REQUEST_LOCATION_PERMISSION
            )
            return
        } else {
            getLocationName()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocationName() {
        lifecycleScope.launch {
            Injector.fusedLocationClient.lastLocation
                .addOnSuccessListener {
                    it.let {
                        val address = LocationUtils.getLocationName(
                            it.latitude,
                            it.longitude,
                            requireContext()
                        )
                        println(address)
                    }
                }
        }
    }
}