package com.example.advancedappdevelopment.data.model.dataClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Association(
    val address: String = "",
    val id: String = "",
    val name: String = "",
    val phone: Int = 0,
    val property_manager: String = "",
) : Parcelable