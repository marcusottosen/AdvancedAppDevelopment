package com.example.advancedappdevelopment.data.model.dataClass

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val name: String,
    val email: String,
    val signUpDate: Timestamp
) : Parcelable

object CurrentUser {
    lateinit var id: String
    lateinit var name: String
    lateinit var email: String
    lateinit var signUpDate: Timestamp
}