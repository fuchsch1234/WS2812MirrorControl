package de.fuchsch.ws2812mirrorcontrol.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import de.fuchsch.ws2812mirrorcontrol.R
import de.fuchsch.ws2812mirrorcontrol.viewmodel.SelectedHostViewModel

class WS2812Activity : AppCompatActivity() {

    private lateinit var selectedHostViewModel: SelectedHostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish()
        }
        setContentView(R.layout.activity_ws2812)
        selectedHostViewModel = ViewModelProviders.of(this).get(SelectedHostViewModel::class.java)
        selectedHostViewModel.selectedHost.value = intent.getParcelableExtra(MainActivity.HOST)
    }
}
