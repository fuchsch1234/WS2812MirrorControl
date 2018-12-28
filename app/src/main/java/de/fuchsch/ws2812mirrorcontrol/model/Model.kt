package de.fuchsch.ws2812mirrorcontrol.model

import com.google.gson.annotations.SerializedName

data class Result(val Success: String, val Message: String)

data class Effect(val effect: String)

data class EffectList(
    val Effects: List<String>,
    val CurrentEffect: String)

data class EffectOptions(
    val Velocity: Int,
    val PrimaryColor: Color
)

data class Color(
    @SerializedName("R") val Red: Int,
    @SerializedName("G") val Green: Int,
    @SerializedName("B") val Blue: Int
)