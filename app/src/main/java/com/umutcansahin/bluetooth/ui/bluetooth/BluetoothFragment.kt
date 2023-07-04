package com.umutcansahin.bluetooth.ui.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.umutcansahin.bluetooth.base.BaseFragment
import com.umutcansahin.bluetooth.common.showToast
import com.umutcansahin.bluetooth.databinding.FragmentBluetoothBinding
import com.umutcansahin.bluetooth.domain.model.BluetoothDeviceDomain
import com.umutcansahin.bluetooth.ui.bluetooth.adapter.PairedAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BluetoothFragment :
    BaseFragment<FragmentBluetoothBinding>(FragmentBluetoothBinding::inflate) {

    private val bluetoothManager by lazy {
        requireContext().getSystemService(BluetoothManager::class.java)
    }

    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private val isBluetoothEnabled: Boolean
        get() = bluetoothAdapter?.isEnabled == true

    private val viewModel by viewModels<BluetoothViewModel>()

    private val pairedAdapter by lazy {
        PairedAdapter(::connectToDevice)
    }

    private val scannedAdapter by lazy {
        PairedAdapter(::connectToDevice)
    }
    val enableBluetoothLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { }

    val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val canEnableBluetooth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissions[Manifest.permission.BLUETOOTH_CONNECT] == true
        } else {
            //bu izin sadece api 31 ve üzerinde almamız gerekiyor api 31 ve altında bu izini
            //almadan kullanıma izin veriliyor.
            true
        }
        if (canEnableBluetooth and isBluetoothEnabled.not()) {
            enableBluetoothLauncher.launch(
                Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            )
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissionLauncher.launch(
                arrayOf(Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT)
            )
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect {
                    it.pairedDevices.forEach {
                        Log.d("UmutcanSahin",it.address)
                    }
                    it.scannedDevices.forEach {
                        Log.d("UmutcanSahin",it.address)
                    }
                    pairedAdapter.submitList(it.pairedDevices)
                    scannedAdapter.submitList(it.scannedDevices)
                    when {
                        it.isConnecting -> {
                            //progres göster
                        }

                        it.isConnected -> {
                            requireContext().showToast("You are connected!")
                        }

                        else -> {

                        }
                    }

                    it.errorMessage?.let { message ->
                        requireContext().showToast(message)
                    }
                }
        }
    }

    private fun initView() {
        binding.recyclerViewPaired.adapter = pairedAdapter
        binding.recyclerViewScanned.adapter = scannedAdapter
        binding.buttonStartScan.setOnClickListener {
            viewModel.startScan()
        }
        binding.buttonStopScan.setOnClickListener {
            viewModel.stopScan()
        }
        binding.buttonStartServer.setOnClickListener {
            viewModel.waitForIncomingConnections()
        }
    }

    private fun connectToDevice(device: BluetoothDeviceDomain) {
        viewModel.connectToDevice(device)
        Log.d("UmutcanSahinClick","click->${device.address}")
    }

}