package de.fuchsch.ws2812mirrorcontrol.network

import de.fuchsch.ws2812mirrorcontrol.model.Effect
import de.fuchsch.ws2812mirrorcontrol.model.EffectList
import de.fuchsch.ws2812mirrorcontrol.model.EffectOptions
import de.fuchsch.ws2812mirrorcontrol.model.Result
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WS2812Api {

    @POST("restart")
    fun restart(): Observable<Result>

    @GET("effect")
    fun getEffects(): Observable<EffectList>

    @POST("effect")
    fun setEffect(@Body effect: Effect): Observable<Result>

    @POST("options")
    fun setEffectOptions(@Body effectOptions: EffectOptions): Observable<Result>

}