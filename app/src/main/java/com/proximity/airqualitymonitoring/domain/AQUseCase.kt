package com.proximity.airqualitymonitoring.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.proximity.airqualitymonitoring.data.models.CityAQDB
import com.proximity.airqualitymonitoring.data.repositaries.AirQualityRepositary
import com.proximity.airqualitymonitoring.domain.model.AQHistorical
import com.proximity.airqualitymonitoring.domain.model.CityAQ
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashSet

class AQUseCase(coroutineScope: CoroutineScope) {
/**
 * we are using this mutablestateflow for the sake of converting the model and to merge new data
 * it will be collected as soon the viewmodel is collected
 *
 * **/

private var airQualityResult:MutableStateFlow<List<CityAQ>> = MutableStateFlow(ArrayList())
private var aqHistoricalResult:MutableStateFlow<List<AQHistorical>> = MutableStateFlow(ArrayList())
@FlowPreview
fun getAirQuality(): Flow<List<CityAQ>> {
    historyCoroutineScope?.launch {
        AirQualityRepositary().getAirQuality().collect {
            var value = airQualityResult.value
            val temp:LinkedHashSet<CityAQ> = LinkedHashSet()
            temp.addAll(it.map { CityAQ(it) })
            temp.addAll(value)
            value = ArrayList(temp)
            value.sortWith(Comparator { p0, p1 -> p1.lastUpdatedAt.compareTo(p0.lastUpdatedAt) })
            airQualityResult.emit(value)
        }
    }

   return airQualityResult
}


    var historyCoroutineScope:CoroutineScope? = coroutineScope

    fun getHistory(id:String?=null):Flow<List<AQHistorical>>{
        var  mId = id
        historyCoroutineScope?.launch {
            try {
                if (mId==null)
                    mId = AirQualityRepositary().getFirstCity()?._id
                if (mId!=null) {
                    AirQualityRepositary().getAirQualityHistory(mId!!).collect { aqHistoricalDB ->
                        val value = aqHistoricalDB.map { AQHistorical(it) }
                        aqHistoricalResult.emit(value)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return aqHistoricalResult
    }


    fun getAQCount():Long{
        return AirQualityRepositary().getAQCount()
    }




}