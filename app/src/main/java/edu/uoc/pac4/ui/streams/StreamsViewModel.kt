package edu.uoc.pac4.ui.streams

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uoc.pac4.data.streams.Stream
import edu.uoc.pac4.data.streams.StreamsRepository
import kotlinx.coroutines.launch

class StreamsViewModel (
        private val repository: StreamsRepository
) : ViewModel() {

    // Live Data
    val streams = MutableLiveData<Pair<String?, List<Stream>>>()

    fun getStreams(cursor: String? = null) {
        viewModelScope.launch {
            streams.postValue(repository.getStreams(cursor))
        }
    }

    fun clearAccessToken(){
        repository.clearAccessToken()
    }
}