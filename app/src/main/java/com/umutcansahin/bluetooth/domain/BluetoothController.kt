package com.umutcansahin.bluetooth.domain

import com.umutcansahin.bluetooth.domain.model.BluetoothDeviceDomain
import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {
    val scannedDevices: StateFlow<List<BluetoothDeviceDomain>>
    val pairedDevices: StateFlow<List<BluetoothDeviceDomain>>

    fun startDiscovery()
    fun stopDiscovery()
    fun release()
}