package com.example.events.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_table")
data class Event (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var category: String = "",
    var date: String = "",
    var location: String = "",
    var description: String = ""
)
