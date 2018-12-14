package de.fuchsch.ws2812mirrorcontrol.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable
import de.fuchsch.ws2812mirrorcontrol.network.WS2812Api
import de.mannodermaus.rxbonjour.RxBonjour
import de.mannodermaus.rxbonjour.drivers.jmdns.JmDNSDriver
import de.mannodermaus.rxbonjour.platforms.android.AndroidPlatform
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    internal fun provideWS2812Api(retrofit: Retrofit): WS2812Api = retrofit.create(WS2812Api::class.java)

    @Provides
    @Reusable
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("test")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
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