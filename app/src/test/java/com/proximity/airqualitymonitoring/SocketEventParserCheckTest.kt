package com.proximity.airqualitymonitoring

import com.proximity.airqualitymonitoring.data.models.AQHistoricalDB
import com.proximity.airqualitymonitoring.data.models.CityAQDB
import com.proximity.airqualitymonitoring.data.repositaries.AirQualityRepositary
import com.proximity.airqualitymonitoring.data.socketevents.AirQualitySockEvent
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SocketEventParserCheckTest {
    val data= "[{\"city\":\"New Delhi\",\"aqi\":180.507648789899},{\"city\":\"Bengaluru\",\"aqi\":189.03877589865982},{\"city\":\"Delhi\",\"aqi\":303.8291574067463},{\"city\":\"Pune\",\"aqi\":223.92541610509514},{\"city\":\"Hyderabad\",\"aqi\":201.27195136247718},{\"city\":\"Indore\",\"aqi\":52.01676454069233},{\"city\":\"Chandigarh\",\"aqi\":46.41250922172234},{\"city\":\"Lucknow\",\"aqi\":78.36048116807255}]\n"
    //check if the data from the above json is parsed
    @Test
    fun checkAQSocketParsing() {
       var airQualityDB =  AirQualitySockEvent(data).parseAndSave(MockAirQualityDB())
        val _id = airQualityDB.get(0).cityAQDB?._id
        assert(_id.equals("delhi"))
        val _id1 = airQualityDB.get(0).aqHistoricalDB?._id
        System.out.println(_id1)
        _id1?.startsWith("delhi_")?.let { assert(it) }

    }

    class MockAirQualityDB:AirQualityRepositary(){
        override fun saveAirQuality(value:List<CityAQDB>)
        {}

        override fun saveHistoricalAirQuality(value:List<AQHistoricalDB>)
        {}
        override fun getAirQualityData():List<CityAQDB>?
        {

            return ArrayList()
        }

    }
}