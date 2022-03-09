package com.proximity.airqualitymonitoring.data.socketevents

import org.json.JSONObject

open class BaseSockEvent(data:String) {

   private var mData:String = data


    fun getData():String
    {
        return mData
    }
}