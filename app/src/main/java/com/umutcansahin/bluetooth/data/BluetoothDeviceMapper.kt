package com.umutcansahin.bluetooth.data

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.umutcansahin.bluetooth.domain.model.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain(): BluetoothDeviceDomain {
    return BluetoothDeviceDomain(
        name = name,
        address = address
    )
}