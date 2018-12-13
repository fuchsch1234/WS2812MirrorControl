package de.fuchsch.ws2812mirrorcontrol.model

data class Result(val Success: String, val Message: String)

data class Effect(val effect: String)

data class EffectList(
    val Effects: List<String>,
    val CurrentEffect: String)
