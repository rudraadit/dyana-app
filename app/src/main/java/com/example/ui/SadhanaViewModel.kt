package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.content.ScriptureContent
import com.example.data.content.SpiritualBook
import com.example.data.content.YogaContent
import com.example.data.content.YogaFlow
import com.example.data.db.SadhanaDatabase
import com.example.data.model.IntrospectionEntry
import com.example.data.model.MindfulnessScheduleItem
import com.example.data.model.SadhanaLog
import com.example.data.model.SevaTaskItem
import com.example.data.repository.SadhanaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class AppTab(val label: String, val iconName: String) {
    HOME("Home", "wb_sunny"),
    PRACTICE("Practice", "self_improvement"),
    LIBRARY("Library", "menu_book"),
    PLANNER("Planner", "calendar_month"),
    SEVA("Seva", "volunteer_activism"),
    PROFILE("Profile", "account_circle")
}

sealed class ActiveModal {
    data object None : ActiveModal()
    data class YogaFlowRunner(val flow: YogaFlow) : ActiveModal()
    data object SuryaNamaskarRunner : ActiveModal()
    data object PranayamaStation : ActiveModal()
    data object JapaMalaStation : ActiveModal()
    data object MeditationTimer : ActiveModal()
    data class ScriptureReader(val book: SpiritualBook) : ActiveModal()
    data object SanyamaSolutionFinder : ActiveModal()
    data object IntrospectionLogger : ActiveModal()
    data object AddScheduleItem : ActiveModal()
    data object VolunteerAttendanceCheckIn : ActiveModal()
}

data class AnalyticsSummary(
    val currentStreakDays: Int = 14,
    val totalMinutesToday: Int = 65,
    val totalMinutesThisWeek: Int = 410,
    val consistencyScore: Int = 94,
    val japaBeadsToday: Int = 108,
    val totalKarmaHours: Double = 4.5,
    val dailyBreakdown: List<DayStat> = emptyList()
)

data class DayStat(
    val dayLabel: String,
    val minutes: Int,
    val targetMinutes: Int = 60
)

class SadhanaViewModel(application: Application) : AndroidViewModel(application) {
    private val database = SadhanaDatabase.getDatabase(application, viewModelScope)
    private val repository = SadhanaRepository(database.sadhanaDao())

    val scheduleItems: StateFlow<List<MindfulnessScheduleItem>> = repository.allScheduleItems
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val sadhanaLogs: StateFlow<List<SadhanaLog>> = repository.allLogs
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val introspections: StateFlow<List<IntrospectionEntry>> = repository.allIntrospections
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val sevaTasks: StateFlow<List<SevaTaskItem>> = repository.allSevaTasks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _currentTab = MutableStateFlow(AppTab.HOME)
    val currentTab: StateFlow<AppTab> = _currentTab.asStateFlow()

    private val _activeModal = MutableStateFlow<ActiveModal>(ActiveModal.None)
    val activeModal: StateFlow<ActiveModal> = _activeModal.asStateFlow()

    // Japa Counter state
    private val _japaCount = MutableStateFlow(0)
    val japaCount: StateFlow<Int> = _japaCount.asStateFlow()

    private val _japaRounds = MutableStateFlow(0)
    val japaRounds: StateFlow<Int> = _japaRounds.asStateFlow()

    // Pranayama Pacing state
    private val _pranayamaPhase = MutableStateFlow("Inhale")
    val pranayamaPhase: StateFlow<String> = _pranayamaPhase.asStateFlow()

    private val _pranayamaSecondsRemaining = MutableStateFlow(4)
    val pranayamaSecondsRemaining: StateFlow<Int> = _pranayamaSecondsRemaining.asStateFlow()

    private val _pranayamaCycle = MutableStateFlow(1)
    val pranayamaCycle: StateFlow<Int> = _pranayamaCycle.asStateFlow()

    private val _isPranayamaActive = MutableStateFlow(false)
    val isPranayamaActive: StateFlow<Boolean> = _isPranayamaActive.asStateFlow()

    // Student profile info
    val studentName = "ABC KUMAR"
    val studentClass = "Class 1"
    val studentGreeting = "Peaceful Afternoon • Home"

    private val todayString: String
        get() = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    val analytics: StateFlow<AnalyticsSummary> = combine(
        sadhanaLogs,
        sevaTasks
    ) { logs, tasks ->
        val todayLog = logs.firstOrNull { it.date == todayString }
        val minutesToday = todayLog?.let { it.meditationMinutes + it.yogaMinutes + it.swadhyayaMinutes } ?: 65
        val japaToday = todayLog?.japaBeads ?: 108
        val karmaHours = tasks.filter { it.isCompleted }.sumOf { it.hours }

        val weekDays = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        val sampleMinutes = listOf(55, 60, 45, 65, 50, 75, 60)
        val dailyStats = weekDays.mapIndexed { idx, day ->
            DayStat(dayLabel = day, minutes = sampleMinutes[idx], targetMinutes = 60)
        }

        AnalyticsSummary(
            currentStreakDays = 14,
            totalMinutesToday = minutesToday,
            totalMinutesThisWeek = 410,
            consistencyScore = 94,
            japaBeadsToday = japaToday,
            totalKarmaHours = 3.0 + karmaHours,
            dailyBreakdown = dailyStats
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AnalyticsSummary())

    fun selectTab(tab: AppTab) {
        _currentTab.value = tab
    }

    fun openModal(modal: ActiveModal) {
        _activeModal.value = modal
    }

    fun closeModal() {
        _activeModal.value = ActiveModal.None
    }

    fun toggleScheduleItem(id: Int, currentCompleted: Boolean) {
        viewModelScope.launch {
            repository.toggleScheduleItem(id, !currentCompleted)
        }
    }

    fun addCustomScheduleItem(title: String, timeSlot: String, category: String, duration: Int) {
        viewModelScope.launch {
            repository.addScheduleItem(
                MindfulnessScheduleItem(
                    title = title,
                    timeSlot = timeSlot,
                    durationMinutes = duration,
                    category = category,
                    isCompleted = false
                )
            )
        }
    }

    fun incrementJapaBead() {
        val next = _japaCount.value + 1
        if (next >= 108) {
            _japaCount.value = 0
            _japaRounds.value += 1
            logSadhanaActivity(japaBeads = 108, disciplineName = "Japa Mala 108")
        } else {
            _japaCount.value = next
        }
    }

    fun resetJapa() {
        _japaCount.value = 0
        _japaRounds.value = 0
    }

    fun toggleSevaTask(id: Int, currentCompleted: Boolean) {
        viewModelScope.launch {
            repository.toggleSevaTask(id, !currentCompleted)
        }
    }

    fun addIntrospection(title: String, reflection: String, emotion: String, gratitude: String) {
        viewModelScope.launch {
            val time = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
            repository.addIntrospection(
                IntrospectionEntry(
                    date = todayString,
                    time = time,
                    promptTitle = title,
                    reflectionText = reflection,
                    emotionalState = emotion,
                    gratitudeNote = gratitude
                )
            )
            logSadhanaActivity(meditationMin = 10, disciplineName = "Introspection & Sanyama")
        }
    }

    fun logSadhanaActivity(
        meditationMin: Int = 0,
        yogaMin: Int = 0,
        swadhyayaMin: Int = 0,
        japaBeads: Int = 0,
        disciplineName: String
    ) {
        viewModelScope.launch {
            repository.logSession(
                date = todayString,
                meditationMin = meditationMin,
                yogaMin = yogaMin,
                swadhyayaMin = swadhyayaMin,
                japaBeads = japaBeads,
                disciplineName = disciplineName
            )
        }
    }
}
