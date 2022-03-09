package com.proximity.airqualitymonitoring.data.dao

import com.proximity.airqualitymonitoring.data.models.CityAQDB

class AirQualityDAO:BaseDAO<CityAQDB>(CityAQDB::class.java,"_id") {
}