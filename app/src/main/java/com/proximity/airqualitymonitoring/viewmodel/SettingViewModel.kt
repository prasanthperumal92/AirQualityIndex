package com.proximity.airqualitymonitoring.viewmodel

import com.proximity.airqualitymonitoring.data.repositaries.SettingRepositary
import com.proximity.airqualitymonitoring.domain.SettingUseCase

class SettingViewModel {
    fun setShouldBringLatestTop(value:Boolean)
    {
        SettingUseCase().setShouldBringLatestTop(value)
    }
    fun shouldBringLatestToTop():Boolean
    {
        return  SettingRepositary().shouldBringLatestToTop()
    }
}