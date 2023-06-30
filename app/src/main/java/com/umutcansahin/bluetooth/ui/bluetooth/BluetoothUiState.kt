package com.umutcansahin.bluetooth.ui.bluetooth

import com.umutcansahin.bluetooth.domain.model.BluetoothDevice

data class BluetoothUiState(
    val scannedDevices: List<BluetoothDevice> = emptyList(),
    val pairedDevices: List<BluetoothDevice> = emptyList()
)
