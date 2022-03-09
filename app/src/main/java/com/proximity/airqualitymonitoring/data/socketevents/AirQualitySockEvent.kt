package com.proximity.airqualitymonitoring.data.socketevents

import com.proximity.airqualitymonitoring.data.models.AQDWrapper
import com.proximity.airqualitymonitoring.data.models.AQHistoricalDB
import com.proximity.airqualitymonitoring.data.models.CityAQDB
import com.proximity.airqualitymonitoring.data.repositaries.AirQualityRepositary
import com.proximity.airqualitymonitoring.utils.Logger
import com.proximity.airqualitymonitoring.utils.TimeUtils
import org.json.JSONArray
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class AirQualitySockEvent(data: String) : BaseSockEvent(data) {
    val CITY = "city"
    val AQI = "aqi"

    fun parseAndSave(repositary: AirQualityRepositary): ArrayList<AQDWrapper> {

        val jsonArray = JSONArray(getData())
        var count = 0;
        val mCityAQDBList: ArrayList<CityAQDB> = ArrayList()
        val mHistoryList: ArrayList<AQHistoricalDB> = ArrayList()
        val mAirQualityList: ArrayList<AQDWrapper> = ArrayList()
        val previousAirQualityData = repositary.getAirQualityData() ?: ArrayList()
        val date = Date()
        val timeDateInUTC = TimeUtils.getTimeDateInUTC(date)

        while (jsonArray.length() > 0 && count < jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(count)
            val city = jsonObject.getString(CITY)
            val idFromCity = getIdFromCity(city)

            val airQualityDB = CityAQDB()
            airQualityDB._id = idFromCity
            airQualityDB.cityName = city
            val aqi = jsonObject.getDouble(AQI)
            airQualityDB.aqi = aqi
            airQualityDB.lastUpdatedAt = timeDateInUTC
            mCityAQDBList.add(airQualityDB)

            val aqHistoricalDB = AQHistoricalDB()
            aqHistoricalDB._id = getHistoricalIdFromCity(city, date)
            aqHistoricalDB.aqi = aqi
            aqHistoricalDB.cityId = idFromCity
            aqHistoricalDB.cityName = city
            aqHistoricalDB.timeStamp = timeDateInUTC

            val indexOf =
                Collections.binarySearch(previousAirQualityData as ArrayList, airQualityDB)
            if (indexOf >= 0) {
                val previousCityDB = previousAirQualityData[indexOf]
                if(aqHistoricalDB.cityName.equals("Mumbai"))
                {
                    Logger.e("mumbai found ${previousCityDB.lastUpdatedAt} , ${airQualityDB.lastUpdatedAt}")
                }
                if (TimeUtils.is30SecondsApart(
                        previousCityDB.lastTimeHistoryupdated,
                        airQualityDB.lastUpdatedAt
                    )
                ){
                    if(aqHistoricalDB.cityName.equals("Mumbai"))
                    {
                        Logger.e("saving mumbai's aqi")
                    }
                    airQualityDB.lastTimeHistoryupdated = timeDateInUTC
                    mHistoryList.add(aqHistoricalDB)
                }else{
                    airQualityDB.lastTimeHistoryupdated = previousCityDB.lastTimeHistoryupdated
                }
            }else{
                if(aqHistoricalDB.cityName.equals("Mumbai"))
                {
                    Logger.e("mumbai found not found")
                }
                airQualityDB.lastTimeHistoryupdated = timeDateInUTC
                mHistoryList.add(aqHistoricalDB)
            }
            mAirQualityList.add(AQDWrapper(aqHistoricalDB, airQualityDB))

            count++
        }

        repositary.saveAirQuality(mCityAQDBList)
        repositary.saveHistoricalAirQuality(mHistoryList)

        return mAirQualityList
    }


    fun getIdFromCity(city: String): String {
        var id = city.lowercase()
        id = id.removePrefix("new ")
        return id;
    }


    fun getHistoricalIdFromCity(city: String, date: Date): String {
        return getIdFromCity(city) + "_" + date.time
    }
}