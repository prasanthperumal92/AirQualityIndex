package com.proximity.airqualitymonitoring.data.models

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class CityAQDB: RealmModel,Comparable<CityAQDB> {
    @PrimaryKey
    lateinit var _id:String
    var cityName:String?=null
    var aqi:Double?=null
    lateinit var lastUpdatedAt:String
    lateinit var lastTimeHistoryupdated:String

    override fun hashCode(): Int {
        return _id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return this._id.equals((other as CityAQDB)._id)
    }

    override fun compareTo(other: CityAQDB): Int {
        return this._id.compareTo(other._id)
    }
}