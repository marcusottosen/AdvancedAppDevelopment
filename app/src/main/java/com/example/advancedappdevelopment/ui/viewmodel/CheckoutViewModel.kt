package com.example.advancedappdevelopment.ui.viewmodel

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.advancedappdevelopment.data.model.dataClass.TempVehicle
import com.example.advancedappdevelopment.data.model.firebaseAdapter.updateVehicleDB
import com.example.bkskjold.data.util.getTimeFromInt
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat

class CheckoutViewModel(private val tempVehicle: TempVehicle): ViewModel(){
	// Update Vehicle to have new information from TempVehicle
	fun updateVehicle(){
		// Adds the chosen hours and date the vehicle object as timeStamps
		for (hour in tempVehicle.chosenHours) {
			val start = toTimeStamp(tempVehicle.chosenDate, getTimeFromInt(hour))
			val end = toTimeStamp(tempVehicle.chosenDate, getTimeFromInt(hour + 1))
			if (start != null && end != null) {
				tempVehicle.vehicle.bookingStart.add(start)
				tempVehicle.vehicle.bookingEnd.add(end)
			}
			println(start)
			updateVehicleDB(tempVehicle.vehicle)
		}
	}

	private fun toTimeStamp(date: String, hour: String): Timestamp? {
		val myDate = SimpleDateFormat("yyyy-MM-dd-k:m").parse("$date-$hour")
		return myDate?.let { Timestamp(it) }
	}
}