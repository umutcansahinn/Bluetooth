package com.umutcansahin.bluetooth.ui.bluetooth.adapter

import androidx.recyclerview.widget.DiffUtil
import com.umutcansahin.bluetooth.domain.model.BluetoothDevice

class DiffUtils: DiffUtil.ItemCallback<BluetoothDevice>() {
    override fun areItemsTheSame(oldItem: BluetoothDevice, newItem: BluetoothDevice): Boolean {
        return oldItem.address == newItem.address
    }

    override fun areContentsTheSame(oldItem: BluetoothDevice, newItem: BluetoothDevice): Boolean {
        return oldItem == newItem
    }
}