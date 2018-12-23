package de.fuchsch.ws2812mirrorcontrol.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import de.fuchsch.ws2812mirrorcontrol.base.BaseViewModel
import de.fuchsch.ws2812mirrorcontrol.model.Host

class SelectedHostViewModel(application: Application): BaseViewModel(application) {

    val selectedHost = MutableLiveData<Host>()

}