package de.fuchsch.ws2812mirrorcontrol.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import de.fuchsch.ws2812mirrorcontrol.model.Repository
import de.mannodermaus.rxbonjour.RxBonjour
import de.mannodermaus.rxbonjour.drivers.jmdns.JmDNSDriver
import de.mannodermaus.rxbonjour.platforms.android.AndroidPlatform
import javax.inject.Singleton

@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Singleton
    internal fun provideRepository(): Repository {
        return Repository()
    }

    @Provides
    @Singleton
    internal fun provideBonjourInterface(context: Context): RxBonjour {
        return RxBonjour.Builder()
            .platform(AndroidPlatform.create(context))
            .driver(JmDNSDriver.create())
            .create()
    }

}