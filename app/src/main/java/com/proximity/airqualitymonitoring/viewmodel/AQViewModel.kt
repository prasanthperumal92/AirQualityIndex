package com.proximity.airqualitymonitoring.viewmodel

import androidx.lifecycle.*
import com.proximity.airqualitymonitoring.data.models.CityAQDB
import com.proximity.airqualitymonitoring.domain.AQUseCase
import com.proximity.airqualitymonitoring.domain.model.AQHistorical
import com.proximity.airqualitymonitoring.domain.model.CityAQ
import kotlinx.coroutines.FlowPreview

class AQViewModel:ViewModel() {
    @FlowPreview
    fun getAQData():LiveData<List<CityAQ>>{
       return AQUseCase(viewModelScope).getAirQuality().asLiveData(viewModelScope.coroutineContext)
    }

    fun getAQDataHistory(id:String?=null):LiveData<List<AQHistorical>>{
        return AQUseCase(viewModelScope).getHistory(id).asLiveData(viewModelScope.coroutineContext)
    }

    fun getAQDATACount():Long
    {
        return AQUseCase(viewModelScope).getAQCount()
    }
}