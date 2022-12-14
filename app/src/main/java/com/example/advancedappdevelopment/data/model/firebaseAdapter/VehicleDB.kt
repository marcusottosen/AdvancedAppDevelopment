package com.example.advancedappdevelopment.data.model.firebaseAdapter

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.advancedappdevelopment.data.model.dataClass.Vehicle
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val vehicles: MutableList<Vehicle> = mutableListOf()

class VehicleDBModel{
    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    fun loadVehiclesFromDB(): MutableList<Vehicle>{
        val db = Firebase.firestore

        db.collection("vehicles")
            .orderBy("carNum", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result ->
                vehicles.clear()
                for (doc in result){
                    vehicles.add(
                        Vehicle(
                            bookingStart = doc["bookingStart"] as MutableList<Timestamp>,
                            bookingEnd = doc["bookingEnd"] as MutableList<Timestamp>,
                            association = doc["association"] as String,
                            carNum = (doc["carNum"] as Number).toByte(),
                            carName = doc["carName"] as String,
                            battery = (doc["battery"] as Number).toByte(),
                            plate = doc["plate"] as String,
                            range = (doc["range"] as Number).toShort(),
                            bootSpace = (doc["bootSpace"] as Number).toShort(),
                            seats = (doc["seats"] as Number).toShort(),
                            documentId = doc.id
                        )
                    )
                }
            }
            .addOnCompleteListener{
                if (it.isSuccessful){
                    _loading.value = false
                }
            }
            .addOnFailureListener{exception ->
                Log.d(ContentValues.TAG, "Error getting documents: vehicles", exception)}
        return vehicles
    }
}

fun updateVehicleDB(vehicle: Vehicle): Vehicle {
    val db = Firebase.firestore
    db.collection("vehicles")
        .whereEqualTo("plate", vehicle.plate) // Makes a query for the single vehicle that needs data updated
        .get()
        .addOnCompleteListener() {

            //Create a mutable list, so we can add items to it.
            val mutableBookingStart = vehicle.bookingStart
            val mutableBookingEnd = vehicle.bookingEnd

            //map of field to update
            val updatedBookingStart = hashMapOf(
                "bookingStart" to mutableBookingStart,
                "bookingEnd" to mutableBookingEnd
            )

            //Update field
            val updateable = db.collection("vehicles").document(vehicle.documentId)
            updateable.set(updatedBookingStart, SetOptions.merge())
                .addOnSuccessListener {
                    Log.d(
                        ContentValues.TAG,
                        "DocumentSnapshot for vehicles successfully updated!")
                }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error updating vehicle document", e) }
        }
    return vehicle
}