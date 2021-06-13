package com.potsane.potsaneweatherapp.ui.location

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.potsane.potsaneweatherapp.R
import com.potsane.potsaneweatherapp.common.showDialog
import com.potsane.potsaneweatherapp.common.ui.showSnackBar
import com.potsane.potsaneweatherapp.databinding.FragmentLocationsBinding
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PermissionsUtil.REQUEST_LOCATION_PERMISSION) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val place = Autocomplete.getPlaceFromIntent(data!!)
                  /*  showLocationWeatherDetails(
                        LocationViewItem(
                            "${place.latLng?.latitude},${place.latLng?.longitude}",
                            place.name!!
                        )
                    )*/
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    val status: Status = Autocomplete.getStatusFromIntent(data!!)
                    showSnackBar(binding.root, "An error has occurred, please try again")
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
            //findNavController().navigate(R.id.action_LocationFragment_to_WeatherFragment)
            true
        }
    }

    private fun onSearchCalled() {
        val fields: List<Place.Field> = listOf(Place.Field.NAME, Place.Field.LAT_LNG)
        val intent = Autocomplete.IntentBuilder(
            AutocompleteActivityMode.FULLSCREEN, fields
        ).build(requireContext())
        startActivityForResult(intent, PermissionsUtil.REQUEST_LOCATION_PERMISSION)
    }

    private fun showDeleteLocationDialog(/*userLocationEntity: UserLocationEntity*/) {
        showDialog(
            getString(R.string.dialog_title_delete_location),
            getString(R.string.dialog_message_delete_location),
            getString(R.string.dialog_positive_button_delete_location),
            requireContext(),
            DialogInterface.OnClickListener { _, _ ->
                //viewModel.deleteLocation(userLocationEntity)
            }
        )
    }


}