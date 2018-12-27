package de.fuchsch.ws2812mirrorcontrol.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter

import de.fuchsch.ws2812mirrorcontrol.R
import de.fuchsch.ws2812mirrorcontrol.viewmodel.WS2812ViewModel
import kotlinx.android.synthetic.main.fragment_ws2812.*

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
