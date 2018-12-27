package de.fuchsch.ws2812mirrorcontrol.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import de.fuchsch.ws2812mirrorcontrol.injection.DaggerViewModelInjector
import de.fuchsch.ws2812mirrorcontrol.injection.NetworkModule
import de.fuchsch.ws2812mirrorcontrol.injection.ViewModelInjector
import de.fuchsch.ws2812mirrorcontrol.viewmodel.SelectedHostViewModel
import de.fuchsch.ws2812mirrorcontrol.viewmodel.WS2812ViewModel
import de.fuchsch.ws2812mirrorcontrol.viewmodel.SelectionViewModel
import java.lang.RuntimeException

abstract class BaseViewModel(application: Application): AndroidViewModel(application) {

    init {
        if (context == null) {
            context = getApplication()
        }
        inject()
    }

    private fun inject() {
        when (this) {
            is WS2812ViewModel -> injector.inject(this)
            is SelectionViewModel -> injector.inject(this)
            is SelectedHostViewModel -> injector.inject(this)
        }
    }

    companion object {

        private var context: Context? = null

        private val injector: ViewModelInjector by lazy {
            DaggerViewModelInjector
                .builder()
                .networkModule(NetworkModule)
                .context(context ?: throw RuntimeException("ViewModel initialization error: Missing context."))
                .build()
        }

    }

}
