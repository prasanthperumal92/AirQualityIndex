package com.proximity.airqualitymonitoring

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.proximity.airqualitymonitoring.data.utils.AppDatabase
import com.proximity.airqualitymonitoring.data.utils.AppSharedPref
import com.proximity.airqualitymonitoring.data.utils.AppSocketClient

class AQMApplication: Application(),Application.ActivityLifecycleCallbacks {
    var activityCount = 0
    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
        AppSharedPref.init(this)
        registerActivityLifecycleCallbacks(this)
        activityCount = 0
    }
    /**
     * The socket is connected in the first screen created
     * which can be either a splashscreen or another screen
     * and disconnected when the last screen is killed
     * **/
    override fun onActivityCreated(p0: Activity, p1: Bundle?) {

        activityCount++
        if (activityCount==1)
            AppSocketClient.getSocketClient()
    }

    override fun onActivityStarted(p0: Activity) {

    }

    override fun onActivityResumed(p0: Activity) {
    }

    override fun onActivityPaused(p0: Activity) {
    }

    override fun onActivityStopped(p0: Activity) {
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityDestroyed(p0: Activity) {
        activityCount--
        if (activityCount==0)
            AppSocketClient.getSocketClient().closeSocket()
    }





}