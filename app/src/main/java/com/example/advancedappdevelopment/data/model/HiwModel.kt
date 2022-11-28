package com.example.advancedappdevelopment.data.model
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * Taget fra NemSport FaqModel.kt klassen fra 3. semesters projekt
 */
data class Hiw(
    val question: String,
    val answer: String,
    val number: Int
)

val hiws = mutableListOf<Hiw>()

fun updateHIW() {
    Firebase.firestore.collection("hiws").get().addOnSuccessListener { result ->
        hiws.clear()
        for (hiw in result) {
            hiws.add(Hiw(
                question = hiw["question"] as String,
                answer = hiw["answer"] as String,
                number = (hiw["hiwNum"] as Number).toInt()
            ))
        }
    }
}