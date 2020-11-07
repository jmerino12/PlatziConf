package com.jonathan.platziconf.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.jonathan.platziconf.model.Conference
import com.jonathan.platziconf.model.Speaker
const val CONFERENCES_COLLECTION_NAME = "conferences"
const val SPEAKERS_COLLECTION_NAME = "speakers"
class FirestoreService {
    val firestoreFirestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()
    init {
        firestoreFirestore.firestoreSettings = settings
    }
    fun getSpeakers(callback :Callback<List<Speaker>>){
        firestoreFirestore.collection(SPEAKERS_COLLECTION_NAME).orderBy("category").get().addOnSuccessListener { result ->
            for (doc in result){
                val list = result.toObjects(Speaker::class.java)
                callback.onSuccess(list)
                break
            }
        }

    }

    fun getSchedule(callback :Callback<List<Conference>>){
        firestoreFirestore.collection(CONFERENCES_COLLECTION_NAME).get().addOnSuccessListener { result ->
            for (doc in result){
                val list = result.toObjects(Conference::class.java)
                callback.onSuccess(list)
                break
            }
        }
    }
}