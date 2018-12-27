package de.fuchsch.ws2812mirrorcontrol.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import de.fuchsch.ws2812mirrorcontrol.base.BaseViewModel
import de.fuchsch.ws2812mirrorcontrol.model.Host
import de.fuchsch.ws2812mirrorcontrol.model.Repository
import javax.inject.Inject

class SelectedHostViewModel(application: Application): BaseViewModel(application) {

    @Inject
    lateinit var repository: Repository

    val selectedHost: LiveData<Host>
            get() = mutableSelectedHost
    private val mutableSelectedHost = MutableLiveData<Host>()

    fun changeSelectedHost(host: Host) {
        repository.changeHost(host)
        mutableSelectedHost.value = host
    }

}