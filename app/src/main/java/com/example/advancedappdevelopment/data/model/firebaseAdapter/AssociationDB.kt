package com.example.advancedappdevelopment.data.model.firebaseAdapter

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.advancedappdevelopment.data.model.dataClass.Association
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


val associations: MutableList<Association> = mutableListOf()

class AssociationDBModel{
    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    fun loadAssociationsFromDB(): MutableList<Association>{
        val db = Firebase.firestore

        db.collection("Association")
            .get()
            .addOnSuccessListener { result ->
                associations.clear()
                for (doc in result){
                    associations.add(
                        Association(
                            address = doc["address"] as String,
                            id = doc["id"] as String,
                            name = doc["name"] as String,
                            phone = (doc["phone"] as Number).toInt(),
                            property_manager = doc["property_manager"] as String,
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
                Log.d(ContentValues.TAG, "Error getting documents: associations", exception)}
        return associations
    }
}