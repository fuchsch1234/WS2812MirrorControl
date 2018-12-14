package de.fuchsch.ws2812mirrorcontrol.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import de.fuchsch.ws2812mirrorcontrol.R
import de.fuchsch.ws2812mirrorcontrol.databinding.FragmentWs2812Binding
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
    private lateinit var binding: FragmentWs2812Binding
    private val effectSpinnerAdapter by lazy {
        ArrayAdapter<String>(this.context!!, android.R.layout.simple_spinner_dropdown_item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(WS2812ViewModel::class.java)
        viewModel.availableEffects.observe(this, Observer { effects -> effectSpinnerAdapter.addAll(effects!!) })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ws2812, container, false)
        binding.spinnerAdapter = effectSpinnerAdapter
        // Inflate the layout for this fragment
        return binding.root
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
