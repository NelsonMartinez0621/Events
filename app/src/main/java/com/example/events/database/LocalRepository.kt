package com.example.events.database

import com.example.events.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface LocalRepository {
    fun getLocalEvents(): Flow<List<Event>>
    suspend fun insertEvent(event: Event)
    fun getEventByTitle(): Flow<Event>
    suspend fun deleteEvent(event: Event)
}

class LocalRepositoryImpl @Inject constructor(
    //change to di
    private val localDao: EventDao
) : LocalRepository{
    //change this to work with teh UIState
    override fun getLocalEvents(): Flow<List<Event>> = flow {
        try {
            val data = localDao.getAllEvents()
            emit(data)
        } catch (e: Exception){
            emit(emptyList())
        }
    }

    override suspend fun insertEvent(event: Event) {
        TODO("Not yet implemented")
    }

    override fun getEventByTitle(): Flow<Event> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEvent(event: Event) {
        TODO("Not yet implemented")
    }
}