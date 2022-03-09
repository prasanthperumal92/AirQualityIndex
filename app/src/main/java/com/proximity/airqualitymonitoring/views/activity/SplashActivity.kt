package com.proximity.airqualitymonitoring.views.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.proximity.airqualitymonitoring.R
import com.proximity.airqualitymonitoring.domain.model.CityAQ
import com.proximity.airqualitymonitoring.viewmodel.AQViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    var aqData: LiveData<List<CityAQ>>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var viewModel = ViewModelProvider(this).get(AQViewModel::class.java)
        /**
         * check if data is already populated and move to next screen
         * if not observe for change in db
         * **/
        if (viewModel.getAQDATACount()>0)
        {
            lifecycleScope.launch {
                delay(500)
               moveToNextScreen()
            }

        }else {
            aqData = viewModel.getAQData()
            aqData?.observe(this, {
                if (it.size > 0) {
                  moveToNextScreen()
                }
            })
        }
    }

    fun moveToNextScreen()
    {
        aqData?.removeObservers(this)
        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }

    override fun onStop() {
        super.onStop()
        aqData?.removeObservers(this)
    }
}