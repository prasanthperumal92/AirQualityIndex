package com.proximity.airqualitymonitoring.data.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static io.realm.Realm.getApplicationContext;

public class AppSharedPref {
    public static AppSharedPref sharedPrefUtil;
    SharedPreferences pref;
    private static String PREF_NAME = "AQM";
    public static void init(Context context){
        sharedPrefUtil = new AppSharedPref();
        sharedPrefUtil.pref = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
    }
    public static AppSharedPref getInstance()
    {
      return sharedPrefUtil;

    }


    public  void save(String key,String value)
    {
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(key,value);
        edit.apply();
    }

    public  void save(String key,int value)
    {
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt(key,value);
        edit.apply();
    }
    public  void save(String key,Boolean value)
    {
        SharedPreferences.Editor edit = pref.edit();
        edit.putBoolean(key,value);
        edit.apply();
    }
    public Boolean getBoolean(String key,Boolean defaultValue)
    {
        return pref.getBoolean(key,defaultValue);
    }

    public void deleteAll()
    {
        SharedPreferences.Editor edit = pref.edit();
        edit.clear();
        edit.apply();
    }
}