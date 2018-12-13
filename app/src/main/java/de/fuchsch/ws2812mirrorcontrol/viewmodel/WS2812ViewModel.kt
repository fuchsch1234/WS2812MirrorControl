package de.fuchsch.ws2812mirrorcontrol.viewmodel

import de.fuchsch.ws2812mirrorcontrol.base.BaseViewModel
import de.fuchsch.ws2812mirrorcontrol.network.WS2812Api
import javax.inject.Inject

class WS2812ViewModel: BaseViewModel() {

    @Inject
    lateinit var wS2812Api: WS2812Api
}