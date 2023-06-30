package com.umutcansahin.bluetooth.ui.bluetooth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutcansahin.bluetooth.domain.BluetoothController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BluetoothViewModel @Inject constructor(
    private val bluetoothController: BluetoothController
) : ViewModel() {

    private val _state = MutableStateFlow(BluetoothUiState())
    val state = combine(
        bluetoothController.pairedDevices,
        bluetoothController.scannedDevices,
        _state
    ) { pairedDevices, scannedDevices, state ->
        state.copy(
            pairedDevices = pairedDevices,
            scannedDevices = scannedDevices
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), _state.value)


    fun startScan() {
        bluetoothController.startDiscovery()
    }

    fun stopScan() {
        bluetoothController.stopDiscovery()
    }
}