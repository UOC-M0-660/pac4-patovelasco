package edu.uoc.pac4.data.streams

/**
 * Created by alex on 12/09/2020.
 */

interface StreamsRepository {
    /// Returns a Pair object containing
    /// first: Pagination cursor
    /// second: List of Streams
    suspend fun getStreams(cursor: String? = null): Pair<String?, List<Stream>>
}