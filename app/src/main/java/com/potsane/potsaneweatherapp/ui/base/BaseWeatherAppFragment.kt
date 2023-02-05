package com.potsane.potsaneweatherapp.ui.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.potsane.potsaneweatherapp.BR
import com.potsane.potsaneweatherapp.common.ui.SnackBarBuilder
import kotlin.system.exitProcess


abstract class BaseWeatherAppFragment<VM : BaseWeatherAppViewModel, VDB : ViewDataBinding> :
    Fragment() {

    protected lateinit var binding: VDB
    protected val viewModel by lazy { createViewModel() }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            container,
            false
        )
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)

        viewModel.uiEvents.observe(viewLifecycleOwner, Observer(::onUiEvents))
    }

    @CallSuper
    protected open fun onUiEvents(event: Any) {
        when (event) {
            is ShowProgressBar -> handleProgressBar(true)
            is HideProgressBar -> handleProgressBar(false)
            is ShowSnackBar -> displaySnackBar(event.message)
        }
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun createViewModel(): VM

    fun handleProgressBar(visibility: Boolean) {
        (requireActivity() as MainActivity).handleProgressBar(visibility)
    }

    protected fun displaySnackBar(message: String) {
        SnackBarBuilder.showSnackBar(binding.root, message)
    }

    protected fun openPermissions(){
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", requireContext().packageName, null)
        intent.data = uri
        startActivity(intent)
    }

    protected fun closeApp(){
        val homeIntent = Intent(Intent.ACTION_MAIN)
        homeIntent.addCategory(Intent.CATEGORY_HOME)
        homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(homeIntent)
        exitProcess(1)
    }
}