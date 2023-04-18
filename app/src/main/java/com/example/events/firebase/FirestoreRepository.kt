package com.example.events.firebase

import android.util.Log
import com.example.events.model.Event
import com.example.events.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val TAG = "FirestoreRepository"
interface FirestoreRepository {
    fun retrieveEvents(): Flow<Resource<List<Event>>>
    suspend fun insertNewEvent(event: Event)
    suspend fun insertEvents(events: List<Event>)
    suspend fun deleteEvent(event: Event)
}

class FirestoreRepositoryImpl @Inject constructor(
    //change to di
    private val firestore: FirebaseFirestore
) : FirestoreRepository {
    override fun retrieveEvents(): Flow<Resource<List<Event>>> = flow {
        emit(Resource.Loading())
        try {
            val response = firestore.collection("eventCollection")
                .get()
                .await().map {
                    it.toObject(Event::class.java)
                }
            emit(Resource.Success(response))
        } catch (e: FirebaseFirestoreException) {
            emit(Resource.Error(e.message.toString(), emptyList()))
        }
    }


    override suspend fun insertNewEvent(event: Event) {
        firestore.collection("eventCollection")
            .document(event.title)
            .set(event)
            .addOnSuccessListener {
                Log.d(TAG, "insertNewEvent: new event inserted success")
            }
            .addOnFailureListener {
                Log.e(TAG, "insertNewEvent: new event error failure", it)
            }
    }

    override suspend fun insertEvents(events: List<Event>) {
        events.forEach {
            firestore.collection("eventCollection")
                .document(it.title)
                .set(it)
                .addOnSuccessListener {
                    Log.d(TAG, "insertEvents: events inserted success")
                }
                .addOnFailureListener {
                    Log.d(TAG, "insertEvents: events inserted failure", it)
                }
        }
    }

    override suspend fun deleteEvent(event: Event) {
        firestore.collection("eventCollection")
            .document(event.title)
            .delete()
    }
}