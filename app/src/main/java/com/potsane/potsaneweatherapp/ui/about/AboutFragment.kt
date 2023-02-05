package com.potsane.potsaneweatherapp.ui.about

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import com.potsane.potsaneweatherapp.R
import com.potsane.potsaneweatherapp.databinding.FragmentAboutBinding
import com.potsane.potsaneweatherapp.ui.base.BaseWeatherAppFragment


class AboutFragment : BaseWeatherAppFragment<AboutViewModel, FragmentAboutBinding>() {

    override fun getLayoutId() = R.layout.fragment_about

    override fun onUiEvents(event: Any) {
        when (event) {
            is OpenPrivacyPolicy -> openWebPage("https://openweather.co.uk/privacy-policy")
            is OpenApiLicencePage -> openWebPage("https://opensource.org/licenses/Apache-2.0")
            else -> super.onUiEvents(event)
        }
    }

    private fun openWebPage(url: String?) {
        val browse = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browse)
    }

    override fun createViewModel(): AboutViewModel {
        return ViewModelProvider(this, AboutViewModel.Factory())
            .get(AboutViewModel::class.java)
    }
}