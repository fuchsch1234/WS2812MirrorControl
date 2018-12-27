package de.fuchsch.ws2812mirrorcontrol.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import de.fuchsch.ws2812mirrorcontrol.injection.DaggerViewModelInjector
import de.fuchsch.ws2812mirrorcontrol.injection.NetworkModule
import de.fuchsch.ws2812mirrorcontrol.injection.ViewModelInjector
import de.fuchsch.ws2812mirrorcontrol.viewmodel.SelectedHostViewModel
import de.fuchsch.ws2812mirrorcontrol.viewmodel.WS2812ViewModel
import de.fuchsch.ws2812mirrorcontrol.viewmodel.SelectionViewModel

abstract class BaseViewModel(application: Application): AndroidViewModel(application) {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .context(getApplication())
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is WS2812ViewModel -> injector.inject(this)
            is SelectionViewModel -> injector.inject(this)
            is SelectedHostViewModel -> injector.inject(this)
        }
    }

}
