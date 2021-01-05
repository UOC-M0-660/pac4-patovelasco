package edu.uoc.pac4.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uoc.pac4.data.user.User
import edu.uoc.pac4.data.user.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: UserRepository
) : ViewModel() {

    // Live Data
    val user = MutableLiveData<User>()

    fun getUser() {
        viewModelScope.launch {
            user.postValue(repository.getUser())
        }
    }

    fun updateUser(description: String) {
        viewModelScope.launch {
            user.postValue(repository.updateUser(description))
        }
    }

    fun clearDataForUnauthorized() {
        repository.clearDataForUnauthorized()
    }

    fun clearDataForLogout() {
        repository.clearDataForLogout()
    }
}