package com.example.advancedappdevelopment.data.model.firebaseAdapter

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.dataClass.Vehicle
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


val vehicles: MutableList<Vehicle> = mutableListOf()

class VehicleDBModel{
    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    fun loadVehiclesFromDB(navController: NavController): MutableList<Vehicle>{ //db: FirebaseFirestore
        println("loadVehiclesFromDB starting")

        val db = Firebase.firestore

        db.collection("vehicles")
            //.orderBy("carNum", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result ->
                vehicles.clear()
                for (doc in result){
                    vehicles.add(
                        Vehicle(
                            carNum = (doc["carNum"] as Number).toByte(),
                            carName = doc["carName"] as String,
                            battery = (doc["battery"] as Number).toByte(),
                            plate = doc["plate"] as String,
                            range = (doc["range"] as Number).toShort(),
                            bootSpace = (doc["bootSpace"] as Number).toShort(),
                            seats = (doc["seats"] as Number).toShort()
                        )
                    )
                    println(doc["carName"].toString())
                }
            }
            .addOnCompleteListener{
                if (it.isSuccessful){
                    println("Successful. Navigating to home from loadVehiclesFromDB")
                    //navController.navigate(NavigationRoute.CarInfo.route)
                    //_loading.value = false
                }
            }
            .addOnFailureListener{exception ->
                Log.d(ContentValues.TAG, "Error getting documents: vehicles", exception)}
        return vehicles
    }
}