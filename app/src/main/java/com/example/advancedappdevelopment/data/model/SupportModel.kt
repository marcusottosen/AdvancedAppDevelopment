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

data class Help(
    val question: String,
    val answer: String,
    val number: Int,
)

val hiws = mutableListOf<Hiw>()
val helps = mutableListOf<Help>()

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
fun updateHelp() {
    Firebase.firestore.collection("help").get().addOnSuccessListener { result ->
        helps.clear()
        for (help in result) {
            helps.add(Help(
                question = help["question"] as String,
                answer = help["answer"] as String,
                number = (help["helpNum"] as Number).toInt()
            ))
        }
    }
}