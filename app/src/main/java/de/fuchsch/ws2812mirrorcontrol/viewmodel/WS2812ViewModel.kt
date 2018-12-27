package de.fuchsch.ws2812mirrorcontrol.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import de.fuchsch.ws2812mirrorcontrol.base.BaseViewModel
import de.fuchsch.ws2812mirrorcontrol.model.Repository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class WS2812ViewModel(application: Application): BaseViewModel(application) {

    @Inject
    lateinit var repository: Repository

    var disposable: Disposable? = null

    val availableEffects: MutableLiveData<List<String>> = MutableLiveData()
    val currentEffectPosition: MutableLiveData<Int> = MutableLiveData()

    init {
        disposable = repository.effects
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