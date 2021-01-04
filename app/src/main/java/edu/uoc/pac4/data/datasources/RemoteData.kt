package edu.uoc.pac4.data.datasources

import edu.uoc.pac4.data.oauth.OAuthTokensResponse
import edu.uoc.pac4.data.streams.StreamsResponse
import edu.uoc.pac4.data.user.User

interface RemoteData {

    suspend fun getTokens(authorizationCode: String): OAuthTokensResponse?

    suspend fun getStreams(cursor: String? = null): StreamsResponse?

    suspend fun getUser(): User?

    suspend fun updateUserDescription(description: String): User?
}