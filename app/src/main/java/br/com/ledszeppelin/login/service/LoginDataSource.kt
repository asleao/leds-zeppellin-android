package br.com.ledszeppelin.login.service

import androidx.lifecycle.LiveData
import br.com.ledszeppelin.data.model.Resource
import br.com.ledszeppelin.login.service.model.LoginRequestModel
import br.com.ledszeppelin.login.service.model.LoginResponseModel


interface LoginDataSource {
    fun login(loginRequestModel: LoginRequestModel): LiveData<Resource<LoginResponseModel>>
}