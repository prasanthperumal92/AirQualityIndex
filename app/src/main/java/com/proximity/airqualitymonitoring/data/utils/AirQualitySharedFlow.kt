package com.proximity.airqualitymonitoring.data.utils

import com.proximity.airqualitymonitoring.data.models.CityAQDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

object AirQualitySharedFlow {
    private val mSharedFlow = MutableSharedFlow<List<CityAQDB>>()

    fun updateValue(dat:List<CityAQDB>){
        CoroutineScope(Dispatchers.IO).launch {
            mSharedFlow.emit(dat)

        }
    }

    fun getSharedFlow(): Flow<List<CityAQDB>>
    {
        return mSharedFlow
    }

}