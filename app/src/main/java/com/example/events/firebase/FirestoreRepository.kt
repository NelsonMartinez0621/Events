package com.example.events.firebase

import com.example.events.model.Event
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

interface FirestoreRepository {
    fun retrieveEvents()
    fun insertNewEvent(event: Event)
    fun insertEvents(events: List<Event>)
    fun deleteEvent(event: Event)
}

class FirestoreRepositoryImpl @Inject constructor(
    //change to di
    private val firestore: FirebaseFirestore = Firebase.firestore
) : FirestoreRepository {
    override fun retrieveEvents() {
        firestore.collection("eventCollection")
            .get()
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }

    override fun insertNewEvent(event: Event) {
        firestore.collection("eventCollection")
            .document(event.title)
            .set(event)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }

    override fun insertEvents(events: List<Event>) {
        events.forEach {
            firestore.collection("eventCollection")
                .document(it.title)
                .set(it)
                .addOnSuccessListener {  }
                .addOnFailureListener {  }
        }
    }

    override fun deleteEvent(event: Event) {
        firestore.collection("eventCollection")
            .document(event.title)
            .delete()
    }
}