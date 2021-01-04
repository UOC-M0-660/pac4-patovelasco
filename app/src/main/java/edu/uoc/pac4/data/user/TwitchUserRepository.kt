package edu.uoc.pac4.data.user

import edu.uoc.pac4.data.datasources.LocalData
import edu.uoc.pac4.data.datasources.RemoteData

/**
 * Created by alex on 11/21/20.
 */

class TwitchUserRepository(
    // TODO: Add any datasources you may need
        private val localData: LocalData,
        private val remoteData: RemoteData
) : UserRepository {

    override suspend fun getUser(): User? {
        // TODO("Not yet implemented")
        return remoteData.getUser()
    }

    override suspend fun updateUser(description: String): User? {
        // TODO("Not yet implemented")
        return remoteData.updateUserDescription(description)
    }

    override fun clearDataForUnauthorized() {
        localData.clearAccessToken()
    }

    override fun clearDataForLogout() {
        localData.clearAccessToken()
        localData.clearRefreshToken()
    }
}