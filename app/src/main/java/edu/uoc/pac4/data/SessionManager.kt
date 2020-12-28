package edu.uoc.pac4.data

import android.content.Context

/**
 * Created by alex on 06/09/2020.
 */

class SessionManager(context: Context) {

    private val sharedPreferencesName = "sessionPreferences"
    private val sharedPreferences =
        context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

    private val accessTokenKey = "accessTokeKey"
    private val refreshTokenKey = "refreshTokenKey"

    fun isUserAvailable(): Boolean {
        return getAccessToken() != null
    }

    fun getAccessToken(): String? {
        return sharedPreferences.getString(accessTokenKey, null)
    }

    fun saveAccessToken(accessToken: String) {
        val editor = sharedPreferences.edit()
        editor.putString(accessTokenKey, accessToken)
        editor.apply()
    }

    fun clearAccessToken() {
        val editor = sharedPreferences.edit()
        editor.remove(accessTokenKey)
        editor.apply()
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString(refreshTokenKey, null)
    }

    fun saveRefreshToken(refreshToken: String) {
        val editor = sharedPreferences.edit()
        editor.putString(refreshTokenKey, refreshToken)
        editor.apply()
    }

    fun clearRefreshToken() {
        val editor = sharedPreferences.edit()
        editor.remove(refreshTokenKey)
        editor.apply()
    }

}