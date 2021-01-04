package edu.uoc.pac4.data.oauth

import android.util.Log
import edu.uoc.pac4.data.datasources.LocalData
import edu.uoc.pac4.data.datasources.RemoteData

/**
 * Created by alex on 11/21/20.
 */
class OAuthAuthenticationRepository(
    // TODO: Add any datasources you may need
        private val localData: LocalData,
        private val remoteData: RemoteData
) : AuthenticationRepository {

    private val tag = "OAuthAuthenticationRepository"

    override suspend fun isUserAvailable(): Boolean {
        // TODO("Not yet implemented")
        return localData.isUserAvailable()
    }

    override suspend fun login(authorizationCode: String): Boolean {
        // TODO("Not yet implemented")
        remoteData.getTokens(authorizationCode)?.let { response ->

            Log.d(tag, "Access token => ${response.accessToken}")
            localData.saveAccessToken(response.accessToken)
            response.refreshToken?.let {
                localData.saveRefreshToken(it)
            }
            return true
        }
        return false
    }

    override suspend fun logout() {
        // TODO("Not yet implemented")
        localData.clearAccessToken()
        localData.clearRefreshToken()
    }
}