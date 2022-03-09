package com.proximity.airqualitymonitoring.utils

import androidx.core.util.TimeUtils
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*
import android.text.format.DateUtils
import java.util.concurrent.TimeUnit


object TimeUtils {
    val utcSimpleDateFormat = SimpleDateFormat("MM/dd/yyyy KK:mm:ss a Z",Locale.ENGLISH)

    fun getTimeDateInUTC(date: Date):String
    {
        return utcSimpleDateFormat.format(date);
    }

    fun getTimeDiff(value:String):String
    {
        val parse = utcSimpleDateFormat.parse(value)
        val ago = DateUtils.getRelativeTimeSpanString(parse.time, Date().time, DateUtils.SECOND_IN_MILLIS)
        return if (!ago.equals("0 seconds ago")) ago.toString() else "now"
    }


    fun is30SecondsApart(from:String,now:String):Boolean
    {
        try {
            val fromDate = utcSimpleDateFormat.parse(from)
            val toDate = utcSimpleDateFormat.parse(now)
            val diff = toDate.time - fromDate.time
            return (TimeUnit.MILLISECONDS.toSeconds(diff)>29)
        } catch (e: Exception) {
            return false
        }
    }

}