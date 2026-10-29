package com.example.data.repository

import com.example.data.db.SadhanaDao
import com.example.data.model.IntrospectionEntry
import com.example.data.model.MindfulnessScheduleItem
import com.example.data.model.SadhanaLog
import com.example.data.model.SevaTaskItem
import kotlinx.coroutines.flow.Flow

class SadhanaRepository(private val dao: SadhanaDao) {
    val allLogs: Flow<List<SadhanaLog>> = dao.getAllLogs()
    val allScheduleItems: Flow<List<MindfulnessScheduleItem>> = dao.getAllScheduleItems()
    val allIntrospections: Flow<List<IntrospectionEntry>> = dao.getAllIntrospections()
    val allSevaTasks: Flow<List<SevaTaskItem>> = dao.getAllSevaTasks()

    suspend fun getTodayLog(date: String): SadhanaLog? {
        return dao.getLogByDate(date)
    }

    suspend fun logSession(
        date: String,
        meditationMin: Int = 0,
        yogaMin: Int = 0,
        swadhyayaMin: Int = 0,
        japaBeads: Int = 0,
        disciplineName: String
    ) {
        val existing = dao.getLogByDate(date)
        if (existing == null) {
            dao.insertOrUpdateLog(
                SadhanaLog(
                    date = date,
                    meditationMinutes = meditationMin,
                    yogaMinutes = yogaMin,
                    swadhyayaMinutes = swadhyayaMin,
                    japaBeads = japaBeads,
                    completedDisciplines = disciplineName
                )
            )
        } else {
            val updatedDisciplines = if (existing.completedDisciplines.contains(disciplineName)) {
                existing.completedDisciplines
            } else {
                "${existing.completedDisciplines},$disciplineName".trim(',')
            }
            dao.insertOrUpdateLog(
                existing.copy(
                    meditationMinutes = existing.meditationMinutes + meditationMin,
                    yogaMinutes = existing.yogaMinutes + yogaMin,
                    swadhyayaMinutes = existing.swadhyayaMinutes + swadhyayaMin,
                    japaBeads = existing.japaBeads + japaBeads,
                    completedDisciplines = updatedDisciplines
                )
            )
        }
    }

    suspend fun toggleScheduleItem(id: Int, isCompleted: Boolean) {
        dao.toggleScheduleItemCompleted(id, isCompleted)
    }

    suspend fun addScheduleItem(item: MindfulnessScheduleItem) {
        dao.insertScheduleItem(item)
    }

    suspend fun deleteScheduleItem(id: Int) {
        dao.deleteScheduleItem(id)
    }

    suspend fun addIntrospection(entry: IntrospectionEntry) {
        dao.insertIntrospection(entry)
    }

    suspend fun toggleSevaTask(id: Int, isCompleted: Boolean) {
        dao.toggleSevaTaskCompleted(id, isCompleted)
    }
}
