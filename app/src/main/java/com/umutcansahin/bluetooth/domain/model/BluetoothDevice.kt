package com.umutcansahin.bluetooth.domain.model

//takma isim verildi "bluetoothDevice" a
typealias BluetoothDeviceDomain = BluetoothDevice

data class BluetoothDevice(
    val name: String?,
    val address: String
)
