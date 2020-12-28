package edu.uoc.pac4.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uoc.pac4.data.oauth.AuthenticationRepository
import kotlinx.coroutines.launch

/**
 * Created by alex on 11/21/20.
 */

// This is a simple ViewModel example,
// you can connect to it from the LaunchActivity and use it
// or just remove it
class LaunchViewModel(
    private val repository: AuthenticationRepository
) : ViewModel() {

    // Live Data
    val isUserAvailable = MutableLiveData<Boolean>()


    // Public function that can be called from the view (Activity)
    fun getUserAvailability() {
        // Get Availability from Repository and post result
        viewModelScope.launch {
            isUserAvailable.postValue(repository.isUserAvailable())
        }
    }
}