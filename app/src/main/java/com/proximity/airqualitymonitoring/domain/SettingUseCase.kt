package com.proximity.airqualitymonitoring.domain

import com.proximity.airqualitymonitoring.data.repositaries.SettingRepositary

class SettingUseCase {
    fun setShouldBringLatestTop(value:Boolean)
    {
        SettingRepositary().setShouldBringLatestTop(value)
    }

    fun shouldBringLatestToTop():Boolean
    {
      return  SettingRepositary().shouldBringLatestToTop()
    }
}