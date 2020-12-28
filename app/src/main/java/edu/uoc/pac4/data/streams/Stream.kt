package edu.uoc.pac4.data.streams

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by alex on 07/09/2020.
 */

@Serializable
data class Stream(
    @SerialName("id") val id: String,
    @SerialName("user_id") val userId: String? = null,
    @SerialName("user_name") val userName: String? = null,
    @SerialName("game_id") val gameId: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("viewer_count") val viewerCount: Int = 0,
    @SerialName("started_at") val startedAtString: String? = null,
    @SerialName("language") val language: String? = null,
    @SerialName("thumbnail_url") val thumbnailUrl: String? = null,
)

@Serializable
data class StreamsResponse(
    @SerialName("data") val data: List<Stream>? = null,
    @SerialName("pagination") val pagination: Pagination? = null,
)

@Serializable
data class Pagination(
    @SerialName("cursor") val cursor: String? = null,
)