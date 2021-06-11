package com.potsane.potsaneweatherapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.potsane.potsaneweatherapp.BR

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
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun createViewModel(): VM

    fun handleProgressBar(visibility: Boolean) {
        (requireActivity() as MainActivity).handleProgressBar(visibility)
    }
}