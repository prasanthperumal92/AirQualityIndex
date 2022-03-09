package com.proximity.airqualitymonitoring.data.models

class AQDWrapper() {
    constructor(aqHistoricalDB: AQHistoricalDB,cityAQDB: CityAQDB):this()
    {
        this.aqHistoricalDB = aqHistoricalDB
        this.cityAQDB = cityAQDB
    }
    var aqHistoricalDB:AQHistoricalDB?=null
    var cityAQDB:CityAQDB?=null
}