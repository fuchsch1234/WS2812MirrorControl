package de.fuchsch.ws2812mirrorcontrol.injection

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import de.fuchsch.ws2812mirrorcontrol.viewmodel.SelectionViewModel
import de.fuchsch.ws2812mirrorcontrol.viewmodel.WS2812ViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(wS2812ViewModel: WS2812ViewModel)

    fun inject(selectionViewModel: SelectionViewModel)

    @Component.Builder
    interface Builder {

        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder

        @BindsInstance
        fun context(context: Context): Builder
    }
}