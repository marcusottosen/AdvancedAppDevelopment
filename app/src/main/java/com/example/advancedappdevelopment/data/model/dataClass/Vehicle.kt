package com.example.advancedappdevelopment.data.model.dataClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vehicle(
    val association: String = "",
    val carNum: Byte = 0,
    val carName: String = "",
    val battery: Byte = 0,
    val plate: String = "",
    val range: Short = 0,
    val bootSpace: Short = 0,
    val seats: Short = 0,
) : Parcelable

/* Idea to time management:

    var BookingStart: MutableList<com.google.firebase.Timestamp> = mutableListOf(),
    var BookingEnd: MutableList<com.google.firebase.Timestamp> = mutableListOf(),

    BookingStart and bookingEnd should always be exact same length

    So..
    BookingStart {12:00, 15:00, 19:00}
    BookingEnd {13:00, 17:00, 23:00}
    Means that the car will be booked from 12-13, 15-17 and 19-23

    ^^Will include dates too etc
 */