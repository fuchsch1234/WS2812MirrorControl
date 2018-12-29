package de.fuchsch.ws2812mirrorcontrol.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import de.fuchsch.ws2812mirrorcontrol.base.BaseViewModel
import de.fuchsch.ws2812mirrorcontrol.model.Color
import de.fuchsch.ws2812mirrorcontrol.model.Repository
import io.reactivex.disposables.Disposable
import java.lang.Exception
import javax.inject.Inject

class WS2812ViewModel(application: Application): BaseViewModel(application) {

    @Inject
    lateinit var repository: Repository

    var disposable: Disposable? = null

    val availableEffects: MutableLiveData<List<String>> = MutableLiveData()
    val currentEffectPosition: MutableLiveData<Int> = MutableLiveData()
    val velocity = MutableLiveData<Int>()
    val color = MutableLiveData<Color>()

    val success: LiveData<String>
        get() = mutableSuccess
    private val mutableSuccess = MutableLiveData<String>()

    val error: LiveData<Throwable>
        get() = mutableError
    private val mutableError = MutableLiveData<Throwable>()

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

    fun restart() {
        disposable?.dispose()
        disposable = repository.restartHost()
            .subscribe(
                { mutableSuccess.postValue("Successfully restarted device.") },
                { mutableError.postValue(it) }
            )
    }

    fun sendConfiguration() {
        disposable?.dispose()
        val effects = availableEffects.value
        val currentEffectPosition = currentEffectPosition.value
        if (effects == null || currentEffectPosition == null) {
            mutableError.value = Exception("No effect chosen.")
            return
        }
        disposable = repository.setEffect(effects[currentEffectPosition])
            .flatMap { repository.setEffectOptions((velocity.value ?: 1) * 100, color.value ?: Color(255, 0, 0)) }
            .subscribe(
                { mutableSuccess.postValue("Configuration changed successfully.") },
                { mutableError.postValue(it) }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}