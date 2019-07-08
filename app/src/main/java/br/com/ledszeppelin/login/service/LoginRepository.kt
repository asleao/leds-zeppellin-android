package br.com.ledszeppelin.login.service

import androidx.lifecycle.LiveData
import br.com.ledszeppelin.data.model.Resource
import br.com.ledszeppelin.login.service.model.LoginRequestModel
import br.com.ledszeppelin.login.service.model.LoginResponseModel
import br.com.ledszeppelin.login.service.remote.LoginRemoteDataSource

class LoginRepository(val loginRemoteDataSource: LoginRemoteDataSource) : LoginDataSource {
    override fun login(loginRequestModel: LoginRequestModel): LiveData<Resource<LoginResponseModel>> {
        return loginRemoteDataSource.login(loginRequestModel)
    }

    companion object {
        private var INSTANCE: LoginRepository? = null

        @JvmStatic
        fun getInstance(loginRemoteDataSource: LoginRemoteDataSource): LoginRepository =
            INSTANCE ?: synchronized(LoginRepository::class.java) {
                INSTANCE ?: LoginRepository(loginRemoteDataSource)
                    .also { INSTANCE = it }
            }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}