package com.example.events.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.events.usecases.firestoreUseCase.EventsListener
import com.example.events.usecases.firestoreUseCase.FirestoreUseCase
import com.example.events.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventUseCase: FirestoreUseCase
    ): ViewModel() {

    private val _events: MutableState<List<Event>> = mutableStateOf(emptyList())
    val events: State<List<Event>> get() = _events

    private val _event: MutableState<Event?> = mutableStateOf(null)
    val event: State<Event?> get() = _event


    init {
        getAllEvents()
    }

    private fun getAllEvents() {
        viewModelScope.launch {
            eventUseCase.getEvents(object :EventsListener{

                override fun onEventsRetrieve(events: List<Event>) {
                    _events.value = events
                }

                override fun onEventSet(isAdded: Boolean) {
                    // no operation needed
                }
            })
        }
    }

    fun setEvent(event: Event) {
        viewModelScope.launch {
            eventUseCase.setEvent(event, object :EventsListener{
                override fun onEventsRetrieve(events: List<Event>) {
                    // no operation needed
                }


                override fun onEventSet(isAdded: Boolean) {
                    _event.value = event
                }
            })
        }
    }

}