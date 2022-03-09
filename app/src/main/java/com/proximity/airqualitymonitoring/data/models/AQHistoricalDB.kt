package com.proximity.airqualitymonitoring.data.models

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class AQHistoricalDB:RealmModel,Comparable<AQHistoricalDB> {
    @PrimaryKey
    lateinit var _id:String
    var cityName:String?=null
    var cityId:String?=null
    var aqi:Double?=null
    var timeStamp:String?=null


    override fun hashCode(): Int {
        return _id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return this._id.equals((other as AQHistoricalDB)._id)
    }

    override fun compareTo(other: AQHistoricalDB): Int {
        return this._id.compareTo(other._id)
    }
}