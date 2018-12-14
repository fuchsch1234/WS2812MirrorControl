package de.fuchsch.ws2812mirrorcontrol.ui


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import de.fuchsch.ws2812mirrorcontrol.R
import de.fuchsch.ws2812mirrorcontrol.viewmodel.SelectionViewModel
import kotlinx.android.synthetic.main.fragment_selection.*

/**
 * A simple [Fragment] subclass.
 * Use the [SelectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SelectionFragment : Fragment() {

    private val hostListAdapter = HostListAdapter()
    private lateinit var viewModel: SelectionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SelectionViewModel::class.java)
        viewModel.hostList.observe(this, Observer { hostListAdapter.submitList(it) })

        selectionRecyclerView.adapter = hostListAdapter
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
        fun newInstance() = SelectionFragment()
    }
}
