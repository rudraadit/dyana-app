package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.ActiveModal
import com.example.ui.AppTab
import com.example.ui.SadhanaViewModel
import com.example.ui.components.BottomSadhanaNav
import com.example.ui.components.TopStudentHeader
import com.example.ui.screens.AppModalHost
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LibraryScreen
import com.example.ui.screens.PlannerScreen
import com.example.ui.screens.PracticeScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.SevaScreen
import com.example.ui.theme.SadhanaTrackerTheme
import com.example.ui.theme.Surface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SadhanaTrackerTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp(
    viewModel: SadhanaViewModel = viewModel()
) {
    val currentTab by viewModel.currentTab.collectAsState()
    val activeModal by viewModel.activeModal.collectAsState()
    val analytics by viewModel.analytics.collectAsState()

    // Handle system back navigation to return to Home or close active modal
    BackHandler(enabled = activeModal !is ActiveModal.None || currentTab != AppTab.HOME) {
        if (activeModal !is ActiveModal.None) {
            viewModel.closeModal()
        } else {
            viewModel.selectTab(AppTab.HOME)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Surface,
        topBar = {
            TopStudentHeader(
                studentName = viewModel.studentName,
                studentClass = viewModel.studentClass,
                greeting = viewModel.studentGreeting,
                streakDays = analytics.currentStreakDays,
                onProfileClick = { viewModel.selectTab(AppTab.PROFILE) }
            )
        },
        bottomBar = {
            BottomSadhanaNav(
                currentTab = currentTab,
                onTabSelected = { tab -> viewModel.selectTab(tab) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            AnimatedContent(
                targetState = currentTab,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "tab_transition"
            ) { targetTab ->
                when (targetTab) {
                    AppTab.HOME -> HomeScreen(viewModel = viewModel)
                    AppTab.PRACTICE -> PracticeScreen(viewModel = viewModel)
                    AppTab.LIBRARY -> LibraryScreen(viewModel = viewModel)
                    AppTab.PLANNER -> PlannerScreen(viewModel = viewModel)
                    AppTab.SEVA -> SevaScreen(viewModel = viewModel)
                    AppTab.PROFILE -> ProfileScreen(viewModel = viewModel)
                }
            }

            // Modal dialogs and runners
            AppModalHost(activeModal = activeModal, viewModel = viewModel)
        }
    }
}
