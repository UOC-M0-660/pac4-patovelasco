package edu.uoc.pac4.data.datasources

import android.util.Log
import edu.uoc.pac4.data.network.Endpoints
import edu.uoc.pac4.data.network.UnauthorizedException
import edu.uoc.pac4.data.oauth.OAuthConstants
import edu.uoc.pac4.data.oauth.OAuthTokensResponse
import edu.uoc.pac4.data.streams.StreamsResponse
import edu.uoc.pac4.data.user.User
import edu.uoc.pac4.data.user.UsersResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.request.get

class RemoteDataSource(private val httpClient: HttpClient) : RemoteData {

    private val tag = "RemoteDataSource"

    /// Gets Access and Refresh Tokens on Twitch
    override suspend fun getTokens(authorizationCode: String): OAuthTokensResponse? {
        // Get Tokens from Twitch
        return try {
            httpClient
                    .post<OAuthTokensResponse>(Endpoints.tokenUrl) {
                        parameter("client_id", OAuthConstants.clientID)
                        parameter("client_secret", OAuthConstants.clientSecret)
                        parameter("code", authorizationCode)
                        parameter("grant_type", "authorization_code")
                        parameter("redirect_uri", OAuthConstants.redirectUri)
                    }

        } catch (t: Throwable) {
            Log.w(tag, "Error Getting Access token", t)
            null
        }
    }

    /// Gets Streams on Twitch
    @Throws(UnauthorizedException::class)
    override suspend fun getStreams(cursor: String?): StreamsResponse? {
        try {
            return httpClient
                    .get<StreamsResponse>(Endpoints.streamsUrl) {
                        cursor?.let { parameter("after", it) }
                    }
        } catch (t: Throwable) {
            Log.w(tag, "Error getting streams", t)
            // Try to handle error
            return when (t) {
                is ClientRequestException -> {
                    // Check if it's a 401 Unauthorized
                    if (t.response?.status?.value == 401) {
                        throw UnauthorizedException
                    }
                    null
                }
                else -> null
            }
        }
    }

    /// Gets Current Authorized User on Twitch
    @Throws(UnauthorizedException::class)
    override suspend fun getUser(): User? {
        try {
            val response = httpClient
                    .get<UsersResponse>(Endpoints.usersUrl)

            return response.data?.firstOrNull()
        } catch (t: Throwable) {
            Log.w(tag, "Error getting user", t)
            // Try to handle error
            return when (t) {
                is ClientRequestException -> {
                    // Check if it's a 401 Unauthorized
                    if (t.response?.status?.value == 401) {
                        throw UnauthorizedException
                    }
                    null
                }
                else -> null
            }
        }
    }

    /// Gets Current Authorized User on Twitch
    @Throws(UnauthorizedException::class)
    override suspend fun updateUserDescription(description: String): User? {
        try {
            val response = httpClient
                    .put<UsersResponse>(Endpoints.usersUrl) {
                        parameter("description", description)
                    }

            return response.data?.firstOrNull()
        } catch (t: Throwable) {
            Log.w(tag, "Error updating user description", t)
            // Try to handle error
            return when (t) {
                is ClientRequestException -> {
                    // Check if it's a 401 Unauthorized
                    if (t.response?.status?.value == 401) {
                        throw UnauthorizedException
                    }
                    null
                }
                else -> null
            }
        }
    }
}