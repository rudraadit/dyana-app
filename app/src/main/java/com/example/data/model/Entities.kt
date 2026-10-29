package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sadhana_logs")
data class SadhanaLog(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val meditationMinutes: Int = 0,
    val yogaMinutes: Int = 0,
    val swadhyayaMinutes: Int = 0,
    val japaBeads: Int = 0,
    val completedDisciplines: String = ""
)

@Entity(tableName = "schedule_items")
data class MindfulnessScheduleItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val timeSlot: String,
    val durationMinutes: Int,
    val category: String,
    val isCompleted: Boolean = false,
    val notes: String = ""
)

@Entity(tableName = "introspection_entries")
data class IntrospectionEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val time: String,
    val promptTitle: String,
    val reflectionText: String,
    val emotionalState: String,
    val gratitudeNote: String
)

@Entity(tableName = "seva_tasks")
data class SevaTaskItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val hours: Double,
    val isCompleted: Boolean = false,
    val date: String
)
