package com.proximity.airqualitymonitoring.domain.model

import com.proximity.airqualitymonitoring.data.models.CityAQDB
import io.realm.RealmModel
import io.realm.annotations.RealmClass


open class CityAQ() {
    constructor(cityAQDB: CityAQDB):this(){
        this.cityName = cityAQDB.cityName
        this._id = cityAQDB._id
        this.aqi = cityAQDB.aqi
        this.lastUpdatedAt = cityAQDB.lastUpdatedAt
    }
    lateinit var _id:String
    var cityName:String?=null
    var aqi:Double?=null
    lateinit var lastUpdatedAt:String

    override fun hashCode(): Int {
        return _id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return this._id.equals((other as CityAQ)._id)
    }
}