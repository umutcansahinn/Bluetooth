package com.umutcansahin.bluetooth.domain

import com.umutcansahin.bluetooth.domain.model.BluetoothDeviceDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {
    val isConnected: StateFlow<Boolean>
    val scannedDevices: StateFlow<List<BluetoothDeviceDomain>>
    val pairedDevices: StateFlow<List<BluetoothDeviceDomain>>
    val errors: SharedFlow<String>
    fun startDiscovery()
    fun stopDiscovery()
    fun release()

    fun startBluetoothServer(): Flow<ConnectionResult>

    fun connectToDevice(device: BluetoothDeviceDomain): Flow<ConnectionResult>

    fun closeConnection()
}