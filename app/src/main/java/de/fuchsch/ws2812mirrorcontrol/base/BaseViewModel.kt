package de.fuchsch.ws2812mirrorcontrol.base

import android.arch.lifecycle.ViewModel
import de.fuchsch.ws2812mirrorcontrol.injection.DaggerViewModelInjector
import de.fuchsch.ws2812mirrorcontrol.injection.NetworkModule
import de.fuchsch.ws2812mirrorcontrol.injection.ViewModelInjector
import de.fuchsch.ws2812mirrorcontrol.viewmodel.WS2812ViewModel

abstract class BaseViewModel: ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is WS2812ViewModel -> injector.inject(this)
        }
    }

}
