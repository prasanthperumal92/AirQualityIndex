package com.proximity.airqualitymonitoring.data.dao

import com.proximity.airqualitymonitoring.data.utils.AppDatabase
import io.realm.RealmModel

//this is a reusable base class which we can use for other projects as well
open class BaseDAO<T: RealmModel> constructor(clazz: Class<T>, primaryKey:String) {
    var mClazz = clazz
    var mPrimaryKey =primaryKey

    fun save(t:T)
    {
        AppDatabase.getDB().executeTransaction{ realm ->
            val copyToRealm = realm.copyToRealmOrUpdate(t)
            realm.insertOrUpdate(copyToRealm)
        }
    }


    fun save(t:List<T>)
    {
        AppDatabase.getDB().executeTransaction { realm ->
            val copyToRealm = realm.copyToRealmOrUpdate(t)
            realm.insertOrUpdate(copyToRealm)
        }
    }

    fun getByPrimaryKey(value:String):T?{
        AppDatabase.getDB().use {
        val findFirst = AppDatabase.getDB().where(mClazz).equalTo(mPrimaryKey, value).findFirst()
        if (findFirst != null) {
            return it.copyFromRealm(findFirst)
        }
        }
        return null
    }

    fun getFirst():T?{
        AppDatabase.getDB().use {
            val findFirst = it.where(mClazz).findFirst()
            if (findFirst != null) {
                return it.copyFromRealm(findFirst)
            }
        }
        return null
    }

    fun getAll():List<T>?{
        AppDatabase.getDB().use {
            val findFirst = it.where(mClazz).findAll()
            if (findFirst != null) {
                return it.copyFromRealm(findFirst)
            }
        }
        return null
    }
    fun getCount():Long{
        return AppDatabase.getDB().where(mClazz).count()
    }

    fun clearAll()
    {
        AppDatabase.getDB().executeTransaction{
            val findAll = it.where(mClazz).findAll()
            findAll.deleteAllFromRealm()
        }
    }
}