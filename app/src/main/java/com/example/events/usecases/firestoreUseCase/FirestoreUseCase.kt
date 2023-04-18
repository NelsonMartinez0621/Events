package com.example.events.usecases.firestoreUseCase

import android.util.Log
import com.example.events.firebase.FirestoreRepository
import com.example.events.firebase.FirestoreRepositoryImpl
import com.example.events.model.Event
import com.example.events.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "FirestoreUseCase"
class FirestoreUseCase @Inject constructor(
    private val firestoreRepository: FirestoreRepository,
    private val scope: CoroutineScope
): CoroutineScope by scope{
     fun getEvents(eventsListener: EventsListener) {
         launch {
             firestoreRepository.retrieveEvents().collect() { result ->
                 when(result) {
                     is Resource.Loading -> {
                         Log.d(TAG, "getEvents: Loading")}

                     is Resource.Success -> {
                         result.data?.let { eventsListener.onEventsRetrieve(it) }
                     }

                     is Resource.Error -> {
                         throw Exception("no event found")
                     }
                 }
             }
        }
    }

    fun setEvent(event: Event, eventsListener: EventsListener) {
        launch {
            firestoreRepository.insertNewEvent(event)
            eventsListener.onEventSet(true)
        }
    }
}

interface EventsListener {
    fun onEventsRetrieve(events: List<Event>)
    fun onEventSet(isAdded: Boolean)
}