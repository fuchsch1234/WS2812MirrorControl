package de.fuchsch.ws2812mirrorcontrol.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import de.fuchsch.ws2812mirrorcontrol.R

/**
 * A simple [Fragment] subclass.
 * Use the [SelectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SelectionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selection, container, false)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SelectionFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) = SelectionFragment()
    }
}
