package edu.uoc.pac4.data.network

/**
 * Created by alex on 24/10/2020.
 */

sealed class OAuthException : Throwable()

object UnauthorizedException : OAuthException()