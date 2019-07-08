package br.com.ledszeppelin.login.service.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.ledszeppelin.data.model.Resource
import br.com.ledszeppelin.login.service.LoginDataSource
import br.com.ledszeppelin.login.service.LoginService
import br.com.ledszeppelin.login.service.model.LoginRequestModel
import br.com.ledszeppelin.login.service.model.LoginResponseModel
import br.com.ledszeppelin.login.service.source.ApiResponse
import org.jetbrains.annotations.NotNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginRemoteDataSource(val loginService: LoginService) : LoginDataSource {


    override fun login(loginRequestModel: LoginRequestModel): LiveData<Resource<LoginResponseModel>> {
        val call = loginService.login(loginRequestModel)
        val apiResponse = ApiResponse<LoginResponseModel>("")
        val login = MutableLiveData<Resource<LoginResponseModel>>()
        login.value = Resource.loading()
        call.enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(@NotNull call: Call<LoginResponseModel>, @NotNull response: Response<LoginResponseModel>) {
                login.value = apiResponse.getApiOnResponse(response)
            }

            override fun onFailure(@NotNull call: Call<LoginResponseModel>, @NotNull t: Throwable) {
                login.value = apiResponse.getApiOnFailure(t)
            }
        })
        return login
    }

    companion object {
        private var INSTANCE: LoginRemoteDataSource? = null

        @JvmStatic
        fun getInstance(loginService: LoginService): LoginRemoteDataSource =
            INSTANCE ?: synchronized(LoginRemoteDataSource::class.java) {
                INSTANCE ?: LoginRemoteDataSource(loginService)
                    .also { INSTANCE = it }
            }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}