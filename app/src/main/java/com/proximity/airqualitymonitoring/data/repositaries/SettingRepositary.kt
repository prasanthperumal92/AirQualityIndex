package com.proximity.airqualitymonitoring.data.repositaries

import com.proximity.airqualitymonitoring.data.utils.AppSharedPref

class SettingRepositary {
    companion object{
        var shouldBringLatestToTop:Boolean? = null
    }
    fun shouldBringLatestToTop():Boolean
    {
        if (shouldBringLatestToTop==null)
           shouldBringLatestToTop = AppSharedPref.getInstance().getBoolean("shouldBringLatestToTop",true)

        return shouldBringLatestToTop!!
    }

    fun setShouldBringLatestTop(value:Boolean)
    {
        shouldBringLatestToTop = value
        AppSharedPref.getInstance().save("shouldBringLatestToTop",value)

    }
}