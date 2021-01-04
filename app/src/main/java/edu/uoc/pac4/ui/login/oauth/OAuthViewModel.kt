package edu.uoc.pac4.ui.login.oauth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uoc.pac4.data.oauth.AuthenticationRepository
import kotlinx.coroutines.launch

class OAuthViewModel (private val repository: AuthenticationRepository
) : ViewModel() {

    // Live Data
    val login = MutableLiveData<Boolean>()

    fun login(authorizationCode: String) {
        viewModelScope.launch {
            login.postValue(repository.login(authorizationCode))
        }
    }
}