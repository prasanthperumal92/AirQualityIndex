package com.proximity.airqualitymonitoring.data.utils

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration


public class AppDatabase  {



    companion object {
        private const val DATABASE_NAME = "AQM"
        fun getDB(): Realm {
            return Realm.getDefaultInstance()
        }

       fun init (context:Context){
           Realm.init(context)
            val config = RealmConfiguration.Builder().name(DATABASE_NAME).deleteRealmIfMigrationNeeded().build()
            Realm.setDefaultConfiguration(config)

        }
    }


}