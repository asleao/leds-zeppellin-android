package br.com.ledszeppelin.login.service.source

import android.util.Log
import br.com.ledszeppelin.data.GENERIC_ERROR_CODE
import br.com.ledszeppelin.data.NETWORK_ERROR_CODE
import br.com.ledszeppelin.data.model.ErrorResponse
import br.com.ledszeppelin.data.model.Resource
import br.com.ledszeppelin.login.CONNECTION_MSG_ERROR
import br.com.ledszeppelin.login.GENERIC_MSG_ERROR_MESSAGE
import br.com.ledszeppelin.login.SERVER_MSG_ERROR
import com.squareup.moshi.Moshi
import retrofit2.Response
import java.io.IOException

class ApiResponse<T>(private val logTag: String) {

    fun getApiOnResponse(response: Response<T>): Resource<T> {
        response.errorBody()?.let { errorBody ->
            val error: ErrorResponse = (if (response.code() in 400 until 500) {
                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter(ErrorResponse::class.java)
                try {
                    jsonAdapter.fromJson(errorBody.string())
                } catch (e: IOException) {
                    Log.e(logTag, e.message)
                }
            } else {
                ErrorResponse(
                    response.code(),
                    SERVER_MSG_ERROR
                )
            }) as ErrorResponse
            return Resource.error(error)
        }

        response.body()?.let { body ->
            return if (response.isSuccessful) {
                Resource.success(body)
            } else {
                Resource.error(
                    ErrorResponse(
                        response.code(),
                        SERVER_MSG_ERROR
                    )
                )
            }
        }
        return Resource.error(
            ErrorResponse(
                response.code(),
                SERVER_MSG_ERROR
            )
        )
    }

    fun getApiOnFailure(t: Throwable): Resource<T> {
        val error: ErrorResponse = if (t is IOException) {
            ErrorResponse(
                NETWORK_ERROR_CODE,
                CONNECTION_MSG_ERROR
            )
        } else {
            ErrorResponse(
                GENERIC_ERROR_CODE,
                GENERIC_MSG_ERROR_MESSAGE
            )
        }
        return Resource.error(error)
    }
}