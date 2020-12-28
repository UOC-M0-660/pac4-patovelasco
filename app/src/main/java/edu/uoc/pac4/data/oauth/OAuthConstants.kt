package edu.uoc.pac4.data.oauth

import java.util.*

/**
 * Created by alex on 07/09/2020.
 */
object OAuthConstants {

    // OAuth2 Variables
    const val clientID = "efwo35z4mgyiyhje8bbp73b98oyavf"
    const val redirectUri = "http://localhost"
    val scopes = listOf("user:read:email user:edit")
    val uniqueState = UUID.randomUUID().toString()
    const val clientSecret = "7fl44yqjm5tjdx73z45dd9ybwuuiez"

}