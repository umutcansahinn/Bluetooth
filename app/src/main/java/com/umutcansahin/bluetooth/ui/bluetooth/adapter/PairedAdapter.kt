package com.umutcansahin.bluetooth.ui.bluetooth.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.umutcansahin.bluetooth.databinding.AdapterItemBinding
import com.umutcansahin.bluetooth.domain.model.BluetoothDevice
import com.umutcansahin.bluetooth.domain.model.BluetoothDeviceDomain

class PairedAdapter(private val onClick: (BluetoothDeviceDomain) -> Unit) :
    ListAdapter<BluetoothDevice, PairedViewHolder>(DiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PairedViewHolder {
        return PairedViewHolder(
            AdapterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PairedViewHolder, position: Int) {
        holder.bind(getItem(position),onClick)
        Log.d("UmutcanSahinAdapter",getItem(position).address)
    }
}