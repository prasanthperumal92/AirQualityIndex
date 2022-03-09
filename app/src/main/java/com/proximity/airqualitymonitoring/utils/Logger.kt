package com.proximity.airqualitymonitoring.utils

import android.os.Build
import android.util.Log
import com.getkeepsafe.relinker.BuildConfig
import java.lang.Exception

class Logger {
//check condition if the build is debug when moving to production
    companion object{
        private  val TAG = "API"

        fun e(value:String){
            Log.e(TAG,value)
        }
        fun e(value:Exception){

            if (value.message!=null) {
                Log.e(TAG,value.message!!)
            }
            value.printStackTrace()

        }

        fun d(value: String)
        {

                Log.e(TAG,value)

        }

        fun i(value: String)
        {
            Log.i(TAG,value)
        }
    }


}