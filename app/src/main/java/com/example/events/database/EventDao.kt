package com.example.events.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.events.model.Event

@Dao
interface EventDao {
    //in charge of all the queries to the database
    @Insert(
        onConflict = OnConflictStrategy.REPLACE,
        entity = Event::class
    )
    suspend fun insertNewEvent(event: Event)

    @Query("SELECT * FROM event_table")
    suspend fun getAllEvents(): List<Event>

    @Query("SELECT * FROM event_table WHERE title Like :sTitle")
    suspend fun getEventByTitle(sTitle: String): Event

    @Delete(
        entity = Event::class
    )
    suspend fun deleteEvent(event: Event)
}