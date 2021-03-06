package de.fuchsch.ws2812mirrorcontrol.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast

import de.fuchsch.ws2812mirrorcontrol.R
import de.fuchsch.ws2812mirrorcontrol.viewmodel.WS2812ViewModel
import kotlinx.android.synthetic.main.fragment_ws2812.*
import top.defaults.colorpicker.ColorPickerPopup

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [WS2812Fragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [WS2812Fragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class WS2812Fragment : Fragment() {

    private lateinit var viewModel: WS2812ViewModel

    private val effectAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter<String>(context, android.R.layout.simple_spinner_item)
    }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            viewModel = ViewModelProviders.of(this).get(WS2812ViewModel::class.java)
            effectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? = inflater.inflate(R.layout.fragment_ws2812, container, false)

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            viewModel.availableEffects.observe(this, Observer{
                effectAdapter.clear()
                effectAdapter.addAll(it)
            })
            effectSpinner.adapter = effectAdapter
            viewModel.currentEffectPosition.observe(this, Observer{
                if (it != null) {
                    effectSpinner.setSelection(it)
                }
            })
            effectSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    viewModel.currentEffectPosition.value = position
                }
            }

            velocitySeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekbar: SeekBar?, value: Int, p2: Boolean) {
                    viewModel.velocity.value = value
                }

                override fun onStartTrackingTouch(seekbar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekbar: SeekBar?) {
                }

            })

            pickedColor.setOnClickListener{
                val color = viewModel.color.value
                val displayColor = if (color != null) {
                    Color.rgb(color.Red, color.Green, color.Blue)
                } else {
                    Color.RED
                }
                ColorPickerPopup.Builder(context)
                    .initialColor(displayColor)
                    .enableBrightness(true)
                    .okTitle("Choose")
                    .cancelTitle("Cancel")
                    .showIndicator(true)
                    .showValue(false)
                    .build()
                    .show(it, object: ColorPickerPopup.ColorPickerObserver {
                        override fun onColorPicked(color: Int) {
                            val color = de.fuchsch.ws2812mirrorcontrol.model.Color(
                                Color.red(color),
                                Color.green(color),
                                Color.blue(color)
                            )
                            viewModel.color.value = color
                        }

                        override fun onColor(color: Int, fromUser: Boolean) {
                        }

                    })
            }

            viewModel.color.observe(this, Observer {
                pickedColor.setBackgroundColor(
                    if (it != null) {
                        Color.rgb(it.Red, it.Green, it.Blue)
                    } else {
                        Color.RED
                })
            })

            restartButton.setOnClickListener{ viewModel.restart() }
            sendButton.setOnClickListener{ viewModel.sendConfiguration() }

            viewModel.error.observe(this, Observer { Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show() })
            viewModel.success.observe(this, Observer { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() })
        }

        companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment WS2812Fragment.
         */
        @JvmStatic
        fun newInstance() = WS2812Fragment()
    }
}
