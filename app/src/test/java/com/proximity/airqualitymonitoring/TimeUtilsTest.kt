package com.proximity.airqualitymonitoring

import com.proximity.airqualitymonitoring.data.socketevents.AirQualitySockEvent
import com.proximity.airqualitymonitoring.utils.TimeUtils
import org.junit.Test

import org.junit.Assert.*
import java.time.Year
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TimeUtilsTest {

    @Test
    fun checkgetTimeDateInUTC() {
        val instance = Calendar.getInstance()
        instance.set(Calendar.YEAR,2022)
        instance.set(Calendar.MONTH,8)
        instance.set(Calendar.DAY_OF_MONTH,13)
        instance.set(Calendar.HOUR,0)
        instance.set(Calendar.MINUTE,0)
        instance.set(Calendar.SECOND,0)
        val date = instance.time
        val timeDateInUTC = TimeUtils.getTimeDateInUTC(date)
        System.out.println(timeDateInUTC)
        //"MM/dd/yyyy KK:mm:ss a Z"
        assert(timeDateInUTC.equals("09/13/2022 00:00:00 AM +0530"))
    }


    @Test
    fun checkgetIsSecondsApart() {

        assert(TimeUtils.is30SecondsApart("09/13/2022 00:08:50 AM +0530","09/13/2022 00:10:00 AM +0530"))
    }
}