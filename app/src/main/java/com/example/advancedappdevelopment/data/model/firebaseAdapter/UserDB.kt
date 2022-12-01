package com.example.advancedappdevelopment.data.model.firebaseAdapter

import com.example.advancedappdevelopment.data.model.dataClass.CurrentUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


fun updateCurrentUser() {
    Firebase.firestore.collection("users").document(FirebaseAuth.getInstance().currentUser?.uid.toString()).get()
        .addOnSuccessListener { result ->
            CurrentUser.id = FirebaseAuth.getInstance().currentUser?.uid.toString()
            CurrentUser.name = result["name"] as String
            CurrentUser.email = result["email"] as String
            CurrentUser.signUpDate = result["signUpDate"] as com.google.firebase.Timestamp
        }
}
