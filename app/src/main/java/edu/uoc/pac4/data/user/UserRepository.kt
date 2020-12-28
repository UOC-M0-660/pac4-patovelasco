package edu.uoc.pac4.data.user

/**
 * Created by alex on 11/21/20.
 */
interface UserRepository {

    suspend fun getUser(): User?

    suspend fun updateUser(description: String): User?

}