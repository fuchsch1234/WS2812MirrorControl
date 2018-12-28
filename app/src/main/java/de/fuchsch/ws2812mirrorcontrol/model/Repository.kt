package de.fuchsch.ws2812mirrorcontrol.model

import de.fuchsch.ws2812mirrorcontrol.injection.DaggerRepositoryInjector
import de.fuchsch.ws2812mirrorcontrol.injection.RepositoryInjector
import de.fuchsch.ws2812mirrorcontrol.injection.RepositoryModule
import de.fuchsch.ws2812mirrorcontrol.network.BaseUrlHolder
import de.fuchsch.ws2812mirrorcontrol.network.WS2812Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.Observable
import java.util.logging.Logger
import javax.inject.Inject

class Repository {

    @Inject
    lateinit var ws2812Api: WS2812Api

    @Inject
    lateinit var baseUrlHolder: BaseUrlHolder

    val effects: BehaviorSubject<EffectList> = BehaviorSubject.create()

    private var disposable: Disposable? = null

    private val logger: Logger = Logger.getLogger(Repository::class.java.canonicalName)

    init {
        // Set a default error handler that swallows all error messages
        // Necessary to use effects observable without an attached observer
        RxJavaPlugins.setErrorHandler { }
        injector.inject(this)
        retrieveEffectList()
    }

    fun changeHost(host: Host) {
        baseUrlHolder.baseUrl = "http://${host.ip}/api/v1/"
        logger.info("Host changed to ${baseUrlHolder.baseUrl}")
        injector.inject(this)
        retrieveEffectList()
    }

    fun restartHost(): Observable<Result> = ws2812Api.restart()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun setEffect(effect: String): Observable<Result> = ws2812Api.setEffect(Effect(effect))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    private fun retrieveEffectList() {
        disposable?.dispose()
        disposable = ws2812Api.getEffects()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { effects.onNext(it) },
                {}
            )
    }

    companion object {

        private val injector: RepositoryInjector = DaggerRepositoryInjector
            .builder()
            .repositoryModule(RepositoryModule)
            .build()

    }

}