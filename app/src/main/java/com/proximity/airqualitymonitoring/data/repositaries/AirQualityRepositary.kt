package com.proximity.airqualitymonitoring.data.repositaries

import com.proximity.airqualitymonitoring.data.dao.AQHistoryDAO
import com.proximity.airqualitymonitoring.data.dao.AirQualityDAO
import com.proximity.airqualitymonitoring.data.models.AQHistoricalDB
import com.proximity.airqualitymonitoring.data.models.CityAQDB
import com.proximity.airqualitymonitoring.data.utils.AirQualitySharedFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

open class AirQualityRepositary {
    /**
     * merge the flows using flattenConcat
     *
     * **/
    @FlowPreview
    fun getAirQuality(): Flow<List<CityAQDB>>
    {
        val flow = flow<List<CityAQDB>> {
            AirQualityDAO().getAll()?.let { emit(it) }
        }

       return flowOf(flow,AirQualitySharedFlow.getSharedFlow()).flattenConcat()
    }

    /**
     * merge the flows using flattenConcat
     *
     * **/
    fun getAirQualityHistory(id:String): Flow<List<AQHistoricalDB>>
    {
        val flow = flow<List<AQHistoricalDB>> {
            while (true){
                AQHistoryDAO().getHistoryOfCity(id)?.let {
                    emit(it)
                }
                delay(10000)
            }

        }

        return flow
    }


    fun getAQCount():Long{
        return AQHistoryDAO().getCount()
    }


    open fun getAirQualityData():List<CityAQDB>?
    {
        return AirQualityDAO().getAll()
    }

    open fun saveAirQuality(value:List<CityAQDB>)
    {
        AirQualitySharedFlow.updateValue(value)
        AirQualityDAO().save(value)
    }

    open fun saveHistoricalAirQuality(value:List<AQHistoricalDB>)
    {

        AQHistoryDAO().save(value)
    }

    fun getFirstCity():CityAQDB?
    {
        return AirQualityDAO().getFirst()
    }
}