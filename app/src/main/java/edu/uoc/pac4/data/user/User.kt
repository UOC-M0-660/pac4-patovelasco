package edu.uoc.pac4.data.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by alex on 07/09/2020.
 */


@Serializable
data class User(
    @SerialName("id") val id: String,
    @SerialName("login") val loginName: String? = null,
    @SerialName("display_name") val userName: String? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("profile_image_url") val profileImageUrl: String? = null,
    @SerialName("offline_image_url") val offlineImageUrl: String? = null,
    @SerialName("view_count") val viewCount: Int = 0,
    @SerialName("email") val email: String? = null,
) {
    fun getSizedImage(imageUrl: String, width: Int, height: Int): String {
        return imageUrl
            .replace("{width}", width.toString())
            .replace("{height}", height.toString())
    }
}

@Serializable
data class UsersResponse(
    @SerialName("data") val data: List<User>? = null,
)
