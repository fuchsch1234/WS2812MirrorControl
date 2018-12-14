package de.fuchsch.ws2812mirrorcontrol.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import de.fuchsch.ws2812mirrorcontrol.base.BaseViewModel
import de.fuchsch.ws2812mirrorcontrol.model.Host
import de.mannodermaus.rxbonjour.BonjourEvent
import de.mannodermaus.rxbonjour.RxBonjour
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SelectionViewModel(application: Application): BaseViewModel(application) {

    val hostList: LiveData<List<Host>>
        get() = mutableHostList
    private val mutableHostList = MutableLiveData<List<Host>>()

    val refreshing: LiveData<Boolean>
        get() = mutableRefreshing
    private val mutableRefreshing = MutableLiveData<Boolean>()

    private var disposable: Disposable? = null

    @Inject
    lateinit var rxBonjour: RxBonjour

    init {
        startDiscovery()
    }

    fun startDiscovery() {
        disposable?.dispose()
        disposable = rxBonjour.newDiscovery("_blinky._tcp")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { event -> onDiscoveryEvent(event) },
                { error -> onDiscoveryError(error) }
            )
    }

    private fun onDiscoveryEvent(event: BonjourEvent) {
        when (event) {
            is BonjourEvent.Added -> {
                val list: MutableList<Host> = mutableHostList.value?.toMutableList() ?: mutableListOf()
                list.add(Host(event.service.name, event.service.v4Host?.hostAddress ?: ""))
                mutableHostList.value = list
            }
            is BonjourEvent.Removed -> {
                val list: MutableList<Host> = mutableHostList.value?.toMutableList() ?: mutableListOf()
                list.remove(Host(event.service.name, event.service.v4Host?.hostAddress ?: ""))
                mutableHostList.value = list
            }
        }
    }

    private fun onDiscoveryError(error: Throwable) {

    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

}