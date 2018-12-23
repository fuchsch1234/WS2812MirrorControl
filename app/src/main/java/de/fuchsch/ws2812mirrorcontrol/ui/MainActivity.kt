package de.fuchsch.ws2812mirrorcontrol.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.fuchsch.ws2812mirrorcontrol.R
import de.fuchsch.ws2812mirrorcontrol.model.Host
import de.fuchsch.ws2812mirrorcontrol.viewmodel.SelectedHostViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var selectedHostViewModel: SelectedHostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selectedHostViewModel = ViewModelProviders.of(this).get(SelectedHostViewModel::class.java)
        selectedHostViewModel.selectedHost.observe(this, Observer { if (it != null) displayHost(it) })
    }

    private fun displayHost(host: Host) {
        val intent = Intent(this, WS2812Activity::class.java).apply {
            putExtra(HOST, host)
        }
        startActivity(intent)
    }

    companion object {
        const val HOST = "de.fuchsch.ws2812mirrorcontrol.HOST"
    }
}
