package com.umutcansahin.bluetooth.di

import android.content.Context
import com.umutcansahin.bluetooth.data.BluetoothControllerImpl
import com.umutcansahin.bluetooth.domain.BluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BluetoothModule {

    @Provides
    @Singleton
    fun provideBluetoothController(@ApplicationContext context: Context): BluetoothController {
        return BluetoothControllerImpl(context)
    }
}