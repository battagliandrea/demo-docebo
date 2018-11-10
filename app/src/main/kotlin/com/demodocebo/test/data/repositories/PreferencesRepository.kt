package com.demodocebo.test.data.repositories

import android.content.SharedPreferences

import javax.inject.Inject

/**
 * Created by andrea on 23/04/17.
 */
class PreferencesRepository @Inject constructor(sp: SharedPreferences) {

    private var mSharedPreferences : SharedPreferences = sp

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //                                                  STRING
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun put(key: String, value: String) {
        mSharedPreferences.edit().putString(key, value).apply()
    }

    private fun get(key: String, defaultValue: String): String? {
        return mSharedPreferences.getString(key, defaultValue)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //                                                  INTEGER
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun put(key: String, value: Int) {
        mSharedPreferences.edit().putInt(key, value).apply()
    }

    fun get(key: String, defaultValue: Int): Int? {
        return mSharedPreferences.getInt(key, defaultValue)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //                                                  FLOAT
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun put(key: String, value: Float) {
        mSharedPreferences.edit().putFloat(key, value).apply()
    }

    private fun get(key: String, defaultValue: Float): Float? {
        return mSharedPreferences.getFloat(key, defaultValue)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //                                                  BOOLEAN
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun put(key: String, value: Boolean) {
        mSharedPreferences.edit().putBoolean(key, value).apply()
    }

    private fun get(key: String, defaultValue: Boolean): Boolean? {
        return mSharedPreferences.getBoolean(key, defaultValue)
    }

    private fun deleteSavedData(key: String) {
        mSharedPreferences.edit().remove(key).apply()
    }

}