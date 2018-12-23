package de.fuchsch.ws2812mirrorcontrol.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import de.fuchsch.ws2812mirrorcontrol.R
import de.fuchsch.ws2812mirrorcontrol.viewmodel.SelectedHostViewModel

class MainActivity : FragmentActivity() {

    private lateinit var selectedHostViewModel: SelectedHostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selectedHostViewModel = ViewModelProviders.of(this).get(SelectedHostViewModel::class.java)
    }
}
