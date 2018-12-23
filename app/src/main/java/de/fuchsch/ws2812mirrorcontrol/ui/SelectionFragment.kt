package de.fuchsch.ws2812mirrorcontrol.ui


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_selection, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectionRecyclerView.adapter = hostListAdapter
        selectionRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        viewModel.refreshing.observe(this, Observer { refreshLayout.isRefreshing = it!! })
        refreshLayout.setOnRefreshListener { viewModel.refresh() }

        viewModel.hostList.observe(this, Observer { hostListAdapter.submitList(it) })
        viewModel.refresh()
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
