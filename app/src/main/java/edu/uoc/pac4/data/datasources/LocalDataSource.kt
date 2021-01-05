package edu.uoc.pac4.data.datasources

import android.content.Context

class LocalDataSource(context: Context) : LocalData {

    private val sharedPreferencesName = "sessionPreferences"
    private val sharedPreferences =
        context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

    private val accessTokenKey = "accessTokeKey"
    private val refreshTokenKey = "refreshTokenKey"

    override fun isUserAvailable(): Boolean {
        return getAccessToken() != null
    }

    override fun getAccessToken(): String? {
        return sharedPreferences.getString(accessTokenKey, null)
    }

    override fun saveAccessToken(accessToken: String) {
        val editor = sharedPreferences.edit()
        editor.putString(accessTokenKey, accessToken)
        editor.apply()
    }

    override fun clearAccessToken() {
        val editor = sharedPreferences.edit()
        editor.remove(accessTokenKey)
        editor.apply()
    }

    override fun getRefreshToken(): String? {
        return sharedPreferences.getString(refreshTokenKey, null)
    }

    override fun saveRefreshToken(refreshToken: String) {
        val editor = sharedPreferences.edit()
        editor.putString(refreshTokenKey, refreshToken)
        editor.apply()
    }

    override fun clearRefreshToken() {
        val editor = sharedPreferences.edit()
        editor.remove(refreshTokenKey)
        editor.apply()
    }
}