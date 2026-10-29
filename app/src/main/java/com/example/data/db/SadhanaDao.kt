package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.IntrospectionEntry
import com.example.data.model.MindfulnessScheduleItem
import com.example.data.model.SadhanaLog
import com.example.data.model.SevaTaskItem
import kotlinx.coroutines.flow.Flow

@Dao
interface SadhanaDao {
    // Sadhana Logs
    @Query("SELECT * FROM sadhana_logs ORDER BY id DESC")
    fun getAllLogs(): Flow<List<SadhanaLog>>

    @Query("SELECT * FROM sadhana_logs WHERE date = :date LIMIT 1")
    suspend fun getLogByDate(date: String): SadhanaLog?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateLog(log: SadhanaLog)

    // Schedule Items
    @Query("SELECT * FROM schedule_items ORDER BY id ASC")
    fun getAllScheduleItems(): Flow<List<MindfulnessScheduleItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScheduleItem(item: MindfulnessScheduleItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScheduleItems(items: List<MindfulnessScheduleItem>)

    @Update
    suspend fun updateScheduleItem(item: MindfulnessScheduleItem)

    @Query("UPDATE schedule_items SET isCompleted = :completed WHERE id = :id")
    suspend fun toggleScheduleItemCompleted(id: Int, completed: Boolean)

    @Query("DELETE FROM schedule_items WHERE id = :id")
    suspend fun deleteScheduleItem(id: Int)

    // Introspection Entries
    @Query("SELECT * FROM introspection_entries ORDER BY id DESC")
    fun getAllIntrospections(): Flow<List<IntrospectionEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntrospection(entry: IntrospectionEntry)

    // Seva Tasks
    @Query("SELECT * FROM seva_tasks ORDER BY id ASC")
    fun getAllSevaTasks(): Flow<List<SevaTaskItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSevaTasks(tasks: List<SevaTaskItem>)

    @Update
    suspend fun updateSevaTask(task: SevaTaskItem)

    @Query("UPDATE seva_tasks SET isCompleted = :completed WHERE id = :id")
    suspend fun toggleSevaTaskCompleted(id: Int, completed: Boolean)
}
