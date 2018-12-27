package de.fuchsch.ws2812mirrorcontrol.injection

import dagger.Module
import dagger.Provides
import de.fuchsch.ws2812mirrorcontrol.network.BaseUrlHolder
import de.fuchsch.ws2812mirrorcontrol.network.WS2812Api
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@Suppress("unused")
object RepositoryModule {

    @Provides
    internal fun provideWS2812Api(retrofit: Retrofit): WS2812Api = retrofit.create(WS2812Api::class.java)

    @Provides
    internal fun provideRetrofitInterface(baseUrlHolder: BaseUrlHolder): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrlHolder.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @Singleton
    internal fun provideBaseUrlHolder(): BaseUrlHolder {
        return BaseUrlHolder()
    }

}