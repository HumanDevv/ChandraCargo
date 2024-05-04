package com.rdd.rdd.utils.sharedPrefrence

import android.content.Context
import android.content.SharedPreferences

class AppPreferences private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "MyAppPreferences"

        // Singleton instance
        @Volatile
        private var instance: AppPreferences? = null

        fun getInstance(context: Context): AppPreferences {
            return instance ?: synchronized(this) {
                instance ?: AppPreferences(context).also { instance = it }
            }
        }
    }

    // Sample methods for storing and retrieving data
    fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).commit()
    }
  fun saveInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).commit()
    }

    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue) ?: defaultValue
    }


    // Clear all preferences
    fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
    }

    // Remove a specific preference
    fun removePreference(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}