package br.com.ledszeppelin.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.ledszeppelin.data.model.ErrorResponse
import br.com.ledszeppelin.data.model.Resource
import br.com.ledszeppelin.login.service.LoginRepository
import br.com.ledszeppelin.login.service.model.LoginRequestModel
import br.com.ledszeppelin.login.service.model.LoginResponseModel

class LoginViewModel(val loginRepository: LoginRepository) : ViewModel() {
    val username: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    private val _loginRequest: MutableLiveData<LoginRequestModel> = MutableLiveData()
    private val loginResponse: LiveData<Resource<LoginResponseModel>>
    val login: LiveData<LoginResponseModel>
    val error: LiveData<ErrorResponse>

    init {
        loginResponse = Transformations.switchMap(_loginRequest) { loginRequest ->
            if (loginRequest == null) {
                null
            } else {
                loginRepository.login(loginRequest)
            }
        }
        login = Transformations.map(loginResponse) { response ->
            response.data
        }
        error = Transformations.map(loginResponse) { response ->
            response.error
        }
    }

    fun authenticate() {
        username.value?.let { username ->
            password.value?.let { password ->
                val loginRequestModel = LoginRequestModel(username, password)
                _loginRequest.postValue(loginRequestModel)
            }
        }
    }
}
