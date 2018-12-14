package de.fuchsch.ws2812mirrorcontrol.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import de.fuchsch.ws2812mirrorcontrol.base.BaseViewModel
import de.fuchsch.ws2812mirrorcontrol.network.WS2812Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WS2812ViewModel(application: Application): BaseViewModel(application) {

    @Inject
    lateinit var wS2812Api: WS2812Api

    var disposable: Disposable? = null

    val availableEffects: MutableLiveData<List<String>> = MutableLiveData()
    val currentEffectPosition: MutableLiveData<Int> = MutableLiveData()

    fun getAvailableEffects() {
        disposable = wS2812Api.getEffects()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    availableEffects.postValue(result.Effects)
                    currentEffectPosition.postValue(result.Effects.indexOf(result.CurrentEffect))
                },
                {}
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}