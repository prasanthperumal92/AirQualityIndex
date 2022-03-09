package com.proximity.airqualitymonitoring.views.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.proximity.airqualitymonitoring.R
import com.proximity.airqualitymonitoring.databinding.ActivityMainBinding
import com.proximity.airqualitymonitoring.domain.model.CityAQ
import com.proximity.airqualitymonitoring.viewmodel.AQViewModel
import com.proximity.airqualitymonitoring.views.fragments.AQGraphFragment
import com.proximity.airqualitymonitoring.views.fragments.AQIListFragment

class MainActivity : AppCompatActivity() {

    lateinit var  binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =DataBindingUtil.setContentView(this,R.layout.activity_main)
        replaceBottomFragment()
        replaceTopFragment()
        binding.ivHelpSection.setOnClickListener {
            gotoHelp()
        }
    }

    fun gotoHelp(){
        startActivity(Intent(this,HelpActivity::class.java))
    }

    private fun getFragmentTransaction() = supportFragmentManager
        .beginTransaction()

     fun replaceBottomFragment(){

            getFragmentTransaction()
                .replace(binding.fragmentContainerBottom.id, AQIListFragment())
                .commit()


    }

     fun replaceTopFragment(cityAQ: CityAQ?=null){

         val fragmentTransaction = getFragmentTransaction()

         val aqGraphFragment = AQGraphFragment()
        if (cityAQ!=null) {
             val bundle = Bundle()
             bundle.putString("data", Gson().toJson(cityAQ))
            aqGraphFragment.arguments = bundle
         }

         fragmentTransaction
            .replace(binding.fragmentContainerTop.id, aqGraphFragment)
            .commit()


    }


}

fun FragmentActivity.replaceMainTopFragment(cityAQ: CityAQ)
{
    if (this is MainActivity) {
        (this as MainActivity).replaceTopFragment(cityAQ)
    }
}