package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.model.IntrospectionEntry
import com.example.data.model.MindfulnessScheduleItem
import com.example.data.model.SadhanaLog
import com.example.data.model.SevaTaskItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        SadhanaLog::class,
        MindfulnessScheduleItem::class,
        IntrospectionEntry::class,
        SevaTaskItem::class
    ],
    version = 1,
    exportSchema = false
)
abstract class SadhanaDatabase : RoomDatabase() {
    abstract fun sadhanaDao(): SadhanaDao

    companion object {
        @Volatile
        private var INSTANCE: SadhanaDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): SadhanaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SadhanaDatabase::class.java,
                    "sadhana_database.db"
                )
                    .addCallback(DatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateInitialData(database.sadhanaDao())
                    }
                }
            }
        }

        suspend fun populateInitialData(dao: SadhanaDao) {
            // Seed 14 days of realistic streak and practice logs
            val initialLogs = listOf(
                SadhanaLog(date = "2026-10-29", meditationMinutes = 20, yogaMinutes = 25, swadhyayaMinutes = 20, japaBeads = 108, completedDisciplines = "Japa,Pranayama,SuryaNamaskar,GitaStudy"),
                SadhanaLog(date = "2026-10-28", meditationMinutes = 20, yogaMinutes = 30, swadhyayaMinutes = 25, japaBeads = 216, completedDisciplines = "Japa,MemoriseYoga,Swadhyaya"),
                SadhanaLog(date = "2026-10-27", meditationMinutes = 15, yogaMinutes = 20, swadhyayaMinutes = 20, japaBeads = 108, completedDisciplines = "Japa,Pranayama"),
                SadhanaLog(date = "2026-10-26", meditationMinutes = 25, yogaMinutes = 25, swadhyayaMinutes = 30, japaBeads = 108, completedDisciplines = "Japa,SuryaNamaskar,MindMastery"),
                SadhanaLog(date = "2026-10-25", meditationMinutes = 20, yogaMinutes = 20, swadhyayaMinutes = 15, japaBeads = 108, completedDisciplines = "Japa,Introspection"),
                SadhanaLog(date = "2026-10-24", meditationMinutes = 30, yogaMinutes = 35, swadhyayaMinutes = 25, japaBeads = 324, completedDisciplines = "Japa,SuryaNamaskar,HutaYoga,GitaStudy"),
                SadhanaLog(date = "2026-10-23", meditationMinutes = 20, yogaMinutes = 20, swadhyayaMinutes = 20, japaBeads = 108, completedDisciplines = "Japa,Pranayama")
            )
            initialLogs.forEach { dao.insertOrUpdateLog(it) }

            // Seed Personalized Mindfulness Schedule
            val initialSchedule = listOf(
                MindfulnessScheduleItem(
                    title = "Brahma Muhurta Pranayama & Gayatri Japa",
                    timeSlot = "05:30 AM",
                    durationMinutes = 25,
                    category = "PRANAYAMA",
                    isCompleted = true,
                    notes = "Anulom Vilom (9 cycles) followed by 108 Gayatri Mantra beads."
                ),
                MindfulnessScheduleItem(
                    title = "Surya Namaskar & Mind Fresh Flow",
                    timeSlot = "06:15 AM",
                    durationMinutes = 20,
                    category = "YOGA",
                    isCompleted = true,
                    notes = "12 cycles facing east, steady rhythmic breathing."
                ),
                MindfulnessScheduleItem(
                    title = "Afternoon Centering & Introspection",
                    timeSlot = "01:30 PM",
                    durationMinutes = 10,
                    category = "SANYAMA",
                    isCompleted = true,
                    notes = "Thought trigger observation & 5 Om chants before afternoon study."
                ),
                MindfulnessScheduleItem(
                    title = "Bhagavad Gita & Swadhyaya Study",
                    timeSlot = "05:00 PM",
                    durationMinutes = 20,
                    category = "SWADHYAYA",
                    isCompleted = false,
                    notes = "Chapter 2 verses 55-60: Stitha Prajna attributes."
                ),
                MindfulnessScheduleItem(
                    title = "Evening Dhyana Silence & Peace Circle",
                    timeSlot = "07:30 PM",
                    durationMinutes = 15,
                    category = "MEDITATION",
                    isCompleted = false,
                    notes = "Inner light focus, silent breath awareness, sacred gratitude."
                ),
                MindfulnessScheduleItem(
                    title = "Night Reflection & Yoga Nidra",
                    timeSlot = "09:45 PM",
                    durationMinutes = 15,
                    category = "MEDITATION",
                    isCompleted = false,
                    notes = "Review student conduct, surrender today's actions, restful transition."
                )
            )
            dao.insertScheduleItems(initialSchedule)

            // Seed Introspections
            dao.insertIntrospection(
                IntrospectionEntry(
                    date = "2026-10-29",
                    time = "01:45 PM",
                    promptTitle = "Impulse & Distraction Log",
                    reflectionText = "Noticed urge to scroll phone during study session. Paused, took 5 conscious deep breaths, refocused attention on mathematics problem.",
                    emotionalState = "Calm & Centered",
                    gratitudeNote = "Grateful for peaceful morning silence and mental clarity."
                )
            )

            // Seed Seva Tasks
            dao.insertSevaTasks(
                listOf(
                    SevaTaskItem(
                        title = "Study Hall Meditation Space Prep",
                        description = "Arranging asana mats and incense lighting for peer meditation group.",
                        hours = 1.0,
                        isCompleted = true,
                        date = "2026-10-29"
                    ),
                    SevaTaskItem(
                        title = "Vedic Study Circle Peer Support",
                        description = "Helping junior students with Bhagavad Gita Sanskrit pronunciation.",
                        hours = 1.5,
                        isCompleted = false,
                        date = "2026-10-29"
                    ),
                    SevaTaskItem(
                        title = "Hostel Sattvic Food Counter Volunteer",
                        description = "Serving pure sattvic meals during Ekadashi observance.",
                        hours = 2.0,
                        isCompleted = false,
                        date = "2026-10-30"
                    )
                )
            )
        }
    }
}
