package de.fuchsch.ws2812mirrorcontrol.model

import de.fuchsch.ws2812mirrorcontrol.injection.DaggerRepositoryInjector
import de.fuchsch.ws2812mirrorcontrol.injection.RepositoryInjector
import de.fuchsch.ws2812mirrorcontrol.injection.RepositoryModule
import de.fuchsch.ws2812mirrorcontrol.network.BaseUrlHolder
import de.fuchsch.ws2812mirrorcontrol.network.WS2812Api
import io.reactivex.Observable
import javax.inject.Inject

class Repository {

    @Inject
    lateinit var ws2812Api: WS2812Api

    @Inject
    lateinit var baseUrlHolder: BaseUrlHolder

    private val injector: RepositoryInjector = DaggerRepositoryInjector
        .builder()
        .repositoryModule(RepositoryModule)
        .build()

    init {
        injector.inject(this)
    }

    fun changeHost(host: Host) {
        baseUrlHolder.baseUrl = "http://" + host.ip
        injector.inject(this)
    }

    companion object {

        private val injector: RepositoryInjector = DaggerRepositoryInjector
            .builder()
            .repositoryModule(RepositoryModule)
            .build()

    }

}