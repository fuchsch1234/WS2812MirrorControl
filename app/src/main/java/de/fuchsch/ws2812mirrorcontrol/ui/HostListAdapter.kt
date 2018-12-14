package de.fuchsch.ws2812mirrorcontrol.ui

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.fuchsch.ws2812mirrorcontrol.R
import de.fuchsch.ws2812mirrorcontrol.model.Host
import kotlinx.android.synthetic.main.host_view_holder.view.*

class HostListAdapter(): ListAdapter<Host, HostListAdapter.ViewHolder>(HostListAdapter.HostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.host_view_holder, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(host: Host) {
            itemView.ipTextView.text = host.ip
            itemView.hostTextView.text = host.host
        }
    }

    class HostDiffCallback: DiffUtil.ItemCallback<Host>() {
        override fun areItemsTheSame(p0: Host, p1: Host): Boolean = p0 === p1

        override fun areContentsTheSame(p0: Host, p1: Host): Boolean = p0 == p1

    }
}