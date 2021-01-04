package edu.uoc.pac4.data.datasources

interface LocalData {

    fun isUserAvailable(): Boolean

    fun getAccessToken(): String?

    fun saveAccessToken(accessToken: String)

    fun clearAccessToken()

    fun getRefreshToken(): String?

    fun saveRefreshToken(refreshToken: String)

    fun clearRefreshToken()

}