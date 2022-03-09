package com.proximity.airqualitymonitoring.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import com.proximity.airqualitymonitoring.R
import com.proximity.airqualitymonitoring.viewmodel.SettingViewModel

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        val findViewById = findViewById<Toolbar>(R.id.toolbar)
        val switchCompat = findViewById<SwitchCompat>(R.id.switchLatestTop)
        setSupportActionBar(findViewById)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val settingViewModel = SettingViewModel()
        switchCompat.isChecked = settingViewModel.shouldBringLatestToTop()

        switchCompat.setOnCheckedChangeListener { compoundButton, b ->
            settingViewModel.setShouldBringLatestTop(b)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
        {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}