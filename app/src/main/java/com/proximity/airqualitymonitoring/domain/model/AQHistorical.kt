package com.proximity.airqualitymonitoring.domain.model

import com.proximity.airqualitymonitoring.data.models.AQHistoricalDB
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

open class AQHistorical():Comparable<AQHistorical> {
    @PrimaryKey
    lateinit var _id:String
    var cityName:String?=null
    var cityId:String?=null
    var aqi:Double?=null
    var timeStamp:String?=null

    constructor(aqHistoricalDB: AQHistoricalDB):this(){
        this._id = aqHistoricalDB._id
        this.aqi = aqHistoricalDB.aqi
        this.cityId = aqHistoricalDB.cityId
        this.cityName = aqHistoricalDB.cityName
        this.timeStamp = aqHistoricalDB.timeStamp
    }

    override fun hashCode(): Int {
        return _id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return this._id.equals((other as AQHistorical)._id)
    }

    override fun compareTo(other: AQHistorical): Int {
        return this._id.compareTo(other._id)
    }
}