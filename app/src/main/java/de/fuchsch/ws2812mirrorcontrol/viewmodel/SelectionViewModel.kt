package de.fuchsch.ws2812mirrorcontrol.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import de.fuchsch.ws2812mirrorcontrol.base.BaseViewModel
import de.fuchsch.ws2812mirrorcontrol.model.Host

class SelectionViewModel: BaseViewModel() {

    val hostList: LiveData<List<Host>>
        get() = mutableHostList
    private val mutableHostList = MutableLiveData<List<Host>>()

}