package edu.uoc.pac4.data.oauth

import java.util.*

/**
 * Created by alex on 07/09/2020.
 */
object OAuthConstants {

    // OAuth2 Variables
    const val clientID = "34kci721ny8qjh5frim73lwfafgdgs"
    const val redirectUri = "http://localhost"
    val scopes = listOf("user:read:email user:edit")
    val uniqueState = UUID.randomUUID().toString()
    const val clientSecret = "1kwz7viwytt91cwwzt1m64dzqp9cut"

}