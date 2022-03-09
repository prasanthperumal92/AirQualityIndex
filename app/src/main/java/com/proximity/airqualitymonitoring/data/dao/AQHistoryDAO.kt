package com.proximity.airqualitymonitoring.data.dao

import com.proximity.airqualitymonitoring.data.models.AQHistoricalDB
import com.proximity.airqualitymonitoring.data.models.CityAQDB
import com.proximity.airqualitymonitoring.data.utils.AppDatabase
import io.realm.Sort

class AQHistoryDAO:BaseDAO<AQHistoricalDB>(AQHistoricalDB::class.java,"_id") {

    fun getHistoryOfCity(value:String):List<AQHistoricalDB>?
    {
        AppDatabase.getDB().use {
            val findFirst = AppDatabase.getDB()
                .where(mClazz)
                .equalTo("cityId", value)
                .sort("timeStamp",Sort.DESCENDING)
                .findAll()
            if (findFirst != null) {
                return it.copyFromRealm(findFirst)
            }
        }
        return null
    }
}