package com.umutcansahin.bluetooth.ui.bluetooth.adapter

import androidx.recyclerview.widget.RecyclerView
import com.umutcansahin.bluetooth.databinding.AdapterItemBinding
import com.umutcansahin.bluetooth.domain.model.BluetoothDevice

class PairedViewHolder(
    private val binding: AdapterItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(bluetoothDevice: BluetoothDevice) {
        binding.textViewName.text = bluetoothDevice.name ?: "no name"
        binding.textViewAddress.text = bluetoothDevice.address
    }
}