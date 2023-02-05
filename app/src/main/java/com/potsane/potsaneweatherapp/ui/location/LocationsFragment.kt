package com.potsane.potsaneweatherapp.ui.location

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.potsane.potsaneweatherapp.R
import com.potsane.potsaneweatherapp.common.ui.DialogBuilder
import com.potsane.potsaneweatherapp.databinding.FragmentLocationsBinding
import com.potsane.potsaneweatherapp.entity.view.LocationInfo
import com.potsane.potsaneweatherapp.ui.base.BaseWeatherAppFragment
import com.potsane.potsaneweatherapp.ui.base.MainActivity
import com.potsane.potsaneweatherapp.util.PermissionsUtil
import kotlinx.android.synthetic.main.fragment_locations.*

class LocationsFragment :
    BaseWeatherAppFragment<LocationsViewModel, FragmentLocationsBinding>() {

    override fun getLayoutId() = R.layout.fragment_locations

    override fun createViewModel(): LocationsViewModel {
        return ViewModelProvider(this, LocationsViewModel.Factory())
            .get(LocationsViewModel::class.java)
    }

    override fun onUiEvents(event: Any) {
        when (event) {
            is ShowWeatherInfoForLocation -> showLocationWeatherDetails(event.locationInfo)
            is DeleteWeatherInfoForLocation -> showDeleteLocationDialog(event.locationInfo)
            else -> super.onUiEvents(event)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PermissionsUtil.PLACES_REQUEST) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val place = Autocomplete.getPlaceFromIntent(data!!)
                    showLocationWeatherDetails(
                        LocationInfo(
                            //Making a safe bet that places will return value
                            place.name!!,
                            place.latLng?.latitude!!,
                            place.latLng?.longitude!!
                        )
                    )
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    displaySnackBar("An error has occurred, please try again")
                }
                Activity.RESULT_CANCELED -> {
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_location_search, menu)
    }

    override fun onStart() {
        super.onStart()
        toolbar.title = "Search location"
        (activity as MainActivity?)!!.setSupportActionBar(toolbar)
        (activity as MainActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_search) {
            onSearchCalled()
            true
        } else {
            findNavController().navigate(R.id.action_locationFragment_to_weatherFragment)
            true
        }
    }

    private fun onSearchCalled() {
        val fields: List<Place.Field> = listOf(Place.Field.NAME, Place.Field.LAT_LNG)
        val intent = Autocomplete.IntentBuilder(
            AutocompleteActivityMode.FULLSCREEN, fields
        ).build(requireContext())
        startActivityForResult(intent, PermissionsUtil.PLACES_REQUEST)
    }

    private fun showDeleteLocationDialog(locationInfo: LocationInfo) {
        DialogBuilder.showDeleteLocationDialog(
            requireContext(),
            DialogInterface.OnClickListener { _, _ ->
                viewModel.deleteLocation(locationInfo)
            }
        )
    }

    private fun showLocationWeatherDetails(locationInfo: LocationInfo) {
        val bundle = bundleOf("locationInfo" to locationInfo)
        findNavController().navigate(R.id.action_locationFragment_to_weatherFragment, bundle)
    }
}