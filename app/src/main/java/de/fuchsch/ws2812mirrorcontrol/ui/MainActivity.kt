package de.fuchsch.ws2812mirrorcontrol.ui

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import de.fuchsch.ws2812mirrorcontrol.R

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
