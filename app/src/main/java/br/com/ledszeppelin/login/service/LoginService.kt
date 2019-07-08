package br.com.ledszeppelin.login.service

import br.com.ledszeppelin.login.service.model.LoginRequestModel
import br.com.ledszeppelin.login.service.model.LoginResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginService {

    @POST("login/")
    fun login(@Body Login: LoginRequestModel): Call<LoginResponseModel>
}