package com.example.events.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.events.model.Event

@Database(
    entities = [
        Event::class
    ],
    version = 1
)
abstract class Database: RoomDatabase() {
    abstract fun  getEventsDao(): EventDao
}