package com.calculator.app.helpers

import android.content.Context
import android.content.SharedPreferences

class LocalSharedPreference(context: Context) {
    private var preference_name = "mads_calculator"
    private var sharedPreferences: SharedPreferences? = null
    private var settings: SharedPreferences? = null

    // variable to hold context
    private val context: Context = context

    /*   *******************************************************************************
                                 Store data in shared preference
         ******************************************************************************** */
    /*
     *  Save string data in local shared preference
     */
    fun saveSharedPreferences(key: String?, value: String?) {
        sharedPreferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.putString(key, value)
        editor.apply()
    }

    /*
     *  Save int data in local shared preference
     */
    fun saveSharedPreferences(key: String?, value: Int) {
        sharedPreferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    /*
     *  Save float data in local shared preference
     */
    fun saveSharedPreferences(key: String?, value: Float) {
        sharedPreferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    /*
     *  Save float data in local shared preference
     */
    fun saveSharedPreferences(key: String?, value: Long) {
        sharedPreferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    /*
     *  Save boolean data in local shared preference
     */
    fun saveSharedPreferences(key: String?, value: Boolean) {
        sharedPreferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    /*   *******************************************************************************
                               Get data in shared preference
       ******************************************************************************** */
    /*
     *  return string data from local shared preference
     */
    fun getSharedPreferences(key: String?): String? {
        settings = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE)
        return settings!!.getString(key, null)
    }

    /*
     *  return Int data from local shared preference
     */
    fun getSharedPreferencesInt(key: String?): Int {
        settings = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE)
        return settings!!.getInt(key, 0)
    }

    /*
     *  return float data from local shared preference
     */
    fun getSharedPreferencesFloat(key: String?): Float {
        settings = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE)
        return settings!!.getFloat(key, 0f)
    }

    /*
     *  return Long data from local shared preference
     */
    fun getSharedPreferencesLong(key: String?): Long {
        settings = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE)
        return settings!!.getLong(key, 0)
    }

    /*
     *  return boolean data from local shared preference
     */
    fun getSharedPreferencesBool(key: String?): Boolean {
        settings = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE)
        return settings!!.getBoolean(key, false)
    }

    /*   *******************************************************************************
                                 Clear data in shared preference
         ******************************************************************************** */
    fun clearSharedPreferences() {
        sharedPreferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.clear()
        editor.apply()
    }

    /*
     *  clear the selected key from local
     */

    fun clearSelectedSharedPreference(key: String?) {
        sharedPreferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.remove(key)
        editor.apply()

    }


}