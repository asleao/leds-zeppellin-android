package br.com.ledszeppelin.login.service.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponseModel(
    @Json(name = "refresh")
    val refresh: String,
    @Json(name = "access")
    val access: String
) : Parcelable
