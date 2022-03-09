package com.proximity.airqualitymonitoring.domain.model

import android.graphics.Color

object AQIColorCode {
    fun getColorCode(value:Double):Int
    {
        if (value<51&&value>0)
        {
            return Color.GREEN
        }else if(value>=51&&value<101){
            return Color.parseColor("#ffff00")
        }else if(value>=101&&value<151){
            return Color.parseColor("#ff8000")
        }else if(value>=151&&value<201){
            return Color.parseColor("#ff0000")
        }else if(value>=201&&value<251){
            return Color.parseColor("#903f97")
        }else if(value>=251){
            return Color.parseColor("#7d0023")
        }else{
            return Color.BLACK
        }
    }
}