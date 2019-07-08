package br.com.ledszeppelin.login.service.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginRequestModel(val username: String,
                             val password: String) : Parcelable
