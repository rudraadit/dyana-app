package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.content.ScriptureVerse
import com.example.data.content.SpiritualBook
import com.example.data.content.YogaContent
import com.example.data.content.YogaFlow
import com.example.ui.ActiveModal
import com.example.ui.SadhanaViewModel
import com.example.ui.components.SacredGlassCard
import com.example.ui.theme.CelestialMidnight
import com.example.ui.theme.FlameGlow
import com.example.ui.theme.LotusLavender
import com.example.ui.theme.OnSurface
import com.example.ui.theme.OnSurfaceVariant
import com.example.ui.theme.PeaceMint
import com.example.ui.theme.Primary
import com.example.ui.theme.PrimaryContainer
import com.example.ui.theme.SacredAmber
import com.example.ui.theme.SadhanaGold
import com.example.ui.theme.Surface
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.TextOnDarkPrimary
import com.example.ui.theme.TwilightDeep
import kotlinx.coroutines.delay

@Composable
fun AppModalHost(
    activeModal: ActiveModal,
    viewModel: SadhanaViewModel
) {
    when (activeModal) {
        is ActiveModal.None -> {}
        is ActiveModal.YogaFlowRunner -> {
            YogaFlowRunnerDialog(
                flow = activeModal.flow,
                onDismiss = { viewModel.closeModal() },
                onComplete = { minutes ->
                    viewModel.logSadhanaActivity(
                        yogaMin = minutes,
                        disciplineName = activeModal.flow.title
                    )
                    viewModel.closeModal()
                }
            )
        }
        is ActiveModal.SuryaNamaskarRunner -> {
            SuryaNamaskarRunnerDialog(
                onDismiss = { viewModel.closeModal() },
                onComplete = {
                    viewModel.logSadhanaActivity(
                        yogaMin = 15,
                        disciplineName = "Surya Namaskar 12 Cycles"
                    )
                    viewModel.closeModal()
                }
            )
        }
        is ActiveModal.ScriptureReader -> {
            ScriptureReaderDialog(
                book = activeModal.book,
                onDismiss = { viewModel.closeModal() },
                onFinished = {
                    viewModel.logSadhanaActivity(
                        swadhyayaMin = 20,
                        disciplineName = activeModal.book.title
                    )
                    viewModel.closeModal()
                }
            )
        }
        is ActiveModal.SanyamaSolutionFinder -> {
            SanyamaSolutionFinderDialog(
                onDismiss = { viewModel.closeModal() },
                onSaveLog = { title, reflection, emotion, gratitude ->
                    viewModel.addIntrospection(title, reflection, emotion, gratitude)
                    viewModel.closeModal()
                }
            )
        }
        is ActiveModal.IntrospectionLogger -> {
            IntrospectionLoggerDialog(
                onDismiss = { viewModel.closeModal() },
                onSave = { title, reflection, emotion, gratitude ->
                    viewModel.addIntrospection(title, reflection, emotion, gratitude)
                    viewModel.closeModal()
                }
            )
        }
        is ActiveModal.AddScheduleItem -> {
            AddScheduleItemDialog(
                onDismiss = { viewModel.closeModal() },
                onAdd = { title, time, category, duration ->
                    viewModel.addCustomScheduleItem(title, time, category, duration)
                    viewModel.closeModal()
                }
            )
        }
        is ActiveModal.VolunteerAttendanceCheckIn -> {
            VolunteerAttendanceCheckInDialog(
                onDismiss = { viewModel.closeModal() },
                onCheckInSuccess = {
                    viewModel.logSadhanaActivity(
                        meditationMin = 0,
                        disciplineName = "Seva Volunteer Attendance"
                    )
                    viewModel.closeModal()
                }
            )
        }
        is ActiveModal.PranayamaStation -> {
            Dialog(
                onDismissRequest = { viewModel.closeModal() },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Surface
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Pranayama Session",
                                style = MaterialTheme.typography.titleMedium,
                                color = SadhanaGold
                            )
                            IconButton(onClick = { viewModel.closeModal() }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = OnSurface
                                )
                            }
                        }
                        InteractivePranayamaMandalaView(viewModel = viewModel)
                    }
                }
            }
        }
        is ActiveModal.JapaMalaStation -> {
            Dialog(
                onDismissRequest = { viewModel.closeModal() },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Surface
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Japa Mala Session",
                                style = MaterialTheme.typography.titleMedium,
                                color = SadhanaGold
                            )
                            IconButton(onClick = { viewModel.closeModal() }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = OnSurface
                                )
                            }
                        }
                        InteractiveJapaMalaBeadView(viewModel = viewModel)
                    }
                }
            }
        }
        is ActiveModal.MeditationTimer -> {
            Dialog(
                onDismissRequest = { viewModel.closeModal() },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Surface
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Dhyana Meditation Session",
                                style = MaterialTheme.typography.titleMedium,
                                color = SadhanaGold
                            )
                            IconButton(onClick = { viewModel.closeModal() }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = OnSurface
                                )
                            }
                        }
                        GuidedMeditationTimerView(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

// 1. Yoga Flow Runner Dialog
@Composable
fun YogaFlowRunnerDialog(
    flow: YogaFlow,
    onDismiss: () -> Unit,
    onComplete: (Int) -> Unit
) {
    var currentStepIndex by remember { mutableIntStateOf(0) }
    val step = flow.steps.getOrNull(currentStepIndex) ?: flow.steps.first()

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = flow.title.uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(flow.badgeColorHex),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Step ${currentStepIndex + 1} of ${flow.steps.size}",
                            style = MaterialTheme.typography.headlineMedium,
                            color = TextOnDarkPrimary
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = OnSurface)
                    }
                }

                // Asana Display Card
                SacredGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    backgroundColor = TwilightDeep.copy(alpha = 0.95f),
                    borderColor = Color(flow.badgeColorHex).copy(alpha = 0.4f)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = step.sanskritName,
                                style = MaterialTheme.typography.titleMedium,
                                color = SadhanaGold
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(SurfaceContainerHighest)
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = step.breathingCue,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = PeaceMint
                                )
                            }
                        }

                        Text(
                            text = step.name,
                            style = MaterialTheme.typography.headlineLarge,
                            fontSize = 26.sp,
                            color = TextOnDarkPrimary
                        )

                        Text(
                            text = step.instruction,
                            style = MaterialTheme.typography.bodyMedium,
                            color = OnSurface,
                            lineHeight = 22.sp
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(SurfaceContainer)
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = SadhanaGold,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "Benefit: ${step.benefit}",
                                style = MaterialTheme.typography.bodySmall,
                                color = LotusLavender
                            )
                        }
                    }
                }

                // Nav Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (currentStepIndex > 0) currentStepIndex -= 1
                        },
                        enabled = currentStepIndex > 0,
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainerHigh)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Previous")
                    }

                    if (currentStepIndex < flow.steps.size - 1) {
                        Button(
                            onClick = { currentStepIndex += 1 },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer)
                        ) {
                            Text("Next Asana")
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                        }
                    } else {
                        Button(
                            onClick = { onComplete(flow.durationMinutes) },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = SadhanaGold)
                        ) {
                            Icon(Icons.Default.Done, contentDescription = null, tint = CelestialMidnight)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Complete & Log Flow", color = CelestialMidnight, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

// 2. Surya Namaskar 12-Step Runner
@Composable
fun SuryaNamaskarRunnerDialog(
    onDismiss: () -> Unit,
    onComplete: () -> Unit
) {
    var stepIndex by remember { mutableIntStateOf(0) }
    val step = YogaContent.suryaNamaskar12Steps[stepIndex]

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "SURYA NAMASKAR",
                            style = MaterialTheme.typography.labelSmall,
                            color = SadhanaGold,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Pose ${step.stepNumber} of 12",
                            style = MaterialTheme.typography.headlineMedium,
                            color = TextOnDarkPrimary
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = OnSurface)
                    }
                }

                // Asana Card
                SacredGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    backgroundColor = TwilightDeep.copy(alpha = 0.95f),
                    borderColor = SadhanaGold.copy(alpha = 0.4f)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = step.mantra,
                            style = MaterialTheme.typography.titleMedium,
                            color = SadhanaGold,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = step.sanskritName,
                            style = MaterialTheme.typography.headlineLarge,
                            fontSize = 24.sp,
                            color = TextOnDarkPrimary
                        )

                        Text(
                            text = step.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = LotusLavender
                        )

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(SurfaceContainer)
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "Breath: ${step.breathingCue}",
                                style = MaterialTheme.typography.labelLarge,
                                color = PeaceMint
                            )
                        }

                        Text(
                            text = step.instruction,
                            style = MaterialTheme.typography.bodyMedium,
                            color = OnSurface,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                // Next / Complete Controls
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { if (stepIndex > 0) stepIndex -= 1 },
                        enabled = stepIndex > 0,
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainerHigh)
                    ) {
                        Text("Back")
                    }

                    if (stepIndex < 11) {
                        Button(
                            onClick = { stepIndex += 1 },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer)
                        ) {
                            Text("Next Step")
                        }
                    } else {
                        Button(
                            onClick = onComplete,
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = SadhanaGold)
                        ) {
                            Text("Finish 12 Steps", color = CelestialMidnight, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

// 3. Scripture Reader Dialog
@Composable
fun ScriptureReaderDialog(
    book: SpiritualBook,
    onDismiss: () -> Unit,
    onFinished: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Surface
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(TwilightDeep)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = book.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = SadhanaGold
                        )
                        Text(
                            text = book.subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = LotusLavender
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = OnSurface)
                    }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    book.chapters.forEach { chapter ->
                        item {
                            SacredGlassCard(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                backgroundColor = SurfaceContainerHigh
                            ) {
                                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Text(
                                        text = "Chapter ${chapter.chapterNumber}: ${chapter.title}",
                                        style = MaterialTheme.typography.titleLarge,
                                        color = TextOnDarkPrimary,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = chapter.summary,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = OnSurfaceVariant
                                    )
                                }
                            }
                        }

                        items(chapter.verses) { verse ->
                            SacredGlassCard(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                backgroundColor = TwilightDeep.copy(alpha = 0.85f),
                                borderColor = SadhanaGold.copy(alpha = 0.2f)
                            ) {
                                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Verse ${verse.verseNumber}",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = SadhanaGold
                                        )
                                    }

                                    Text(
                                        text = verse.sanskritText,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontSize = 15.sp,
                                        color = TextOnDarkPrimary,
                                        lineHeight = 24.sp
                                    )

                                    Text(
                                        text = verse.transliteration,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = LotusLavender
                                    )

                                    Text(
                                        text = "Meaning: ${verse.englishMeaning}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = OnSurface
                                    )

                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(SurfaceContainer)
                                            .padding(10.dp)
                                    ) {
                                        Text(
                                            text = "Student Practical Application: ${verse.studentApplication}",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = PeaceMint
                                        )
                                    }
                                }
                            }
                        }
                    }

                    item {
                        Button(
                            onClick = onFinished,
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text("Mark Chapter Read (+20m Swadhyaya)")
                        }
                    }
                }
            }
        }
    }
}

// 4. Sanyama Solution Finder Dialog
@Composable
fun SanyamaSolutionFinderDialog(
    onDismiss: () -> Unit,
    onSaveLog: (String, String, String, String) -> Unit
) {
    var selectedIssue by remember { mutableStateOf("Digital Distraction & Screen Binge") }
    var reflectionInput by remember { mutableStateOf("") }
    var gratitudeInput by remember { mutableStateOf("Grateful for mindful awareness.") }

    val solutions = mapOf(
        "Digital Distraction & Screen Binge" to "Vedic Antidote: Sensory Fasting (Pratyahara). Place phone in another room. Chant 108 Gayatri beads or take 10 deep Anulom Vilom breaths when impulse arises.",
        "Exam Anxiety & Overthinking" to "Vedic Antidote: Nishkama Karma (Gita 2.47). Relinquish obsession over marks. Pour 100% focused attention onto understanding current paragraph without future projection.",
        "Anger & Annoyance with Peers" to "Vedic Antidote: 5-Om Pause. Before uttering words in agitation, repeat OM silently 5 times. Breath softens speech and prevents harsh reactions.",
        "Morning Sluggishness & Tamas" to "Vedic Antidote: Agni Activation. Splash cold water on eyes, stand in Tadasana, perform 30 rapid Kapalbhati exhalations to flood brain with oxygen."
    )

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "SANYAMA INNER HARMONY",
                            style = MaterialTheme.typography.labelSmall,
                            color = FlameGlow,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Thought & Emotion Solution Finder",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 18.sp,
                            color = TextOnDarkPrimary
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = OnSurface)
                    }
                }

                Text(
                    text = "Select what you are facing right now:",
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceVariant
                )

                solutions.keys.forEach { issue ->
                    FilterChip(
                        selected = selectedIssue == issue,
                        onClick = { selectedIssue = issue },
                        label = { Text(text = issue, fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = FlameGlow,
                            selectedLabelColor = CelestialMidnight
                        )
                    )
                }

                // Solution Display Box
                SacredGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = TwilightDeep.copy(alpha = 0.95f),
                    borderColor = FlameGlow.copy(alpha = 0.3f)
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Icon(Icons.Default.Lightbulb, contentDescription = null, tint = SadhanaGold, modifier = Modifier.size(18.dp))
                            Text("PRACTICAL REMEDY", style = MaterialTheme.typography.labelSmall, color = SadhanaGold)
                        }
                        Text(
                            text = solutions[selectedIssue] ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextOnDarkPrimary,
                            lineHeight = 22.sp
                        )
                    }
                }

                OutlinedTextField(
                    value = reflectionInput,
                    onValueChange = { reflectionInput = it },
                    label = { Text("Record your inner reflection & commitment") },
                    placeholder = { Text("What triggered this? What will you do next?") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = SadhanaGold,
                        unfocusedBorderColor = Color.White.copy(alpha = 0.2f),
                        focusedTextColor = TextOnDarkPrimary,
                        unfocusedTextColor = TextOnDarkPrimary
                    )
                )

                Button(
                    onClick = {
                        val text = if (reflectionInput.isBlank()) "Observed $selectedIssue and applied Vedic remedy." else reflectionInput
                        onSaveLog(selectedIssue, text, "Centered & Calm", gratitudeInput)
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SadhanaGold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Save to Introspection Diary", color = CelestialMidnight, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// 5. Introspection Logger Dialog
@Composable
fun IntrospectionLoggerDialog(
    onDismiss: () -> Unit,
    onSave: (String, String, String, String) -> Unit
) {
    var title by remember { mutableStateOf("Daily Self-Reflection") }
    var reflection by remember { mutableStateOf("") }
    var emotion by remember { mutableStateOf("Peaceful & Centered") }
    var gratitude by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        SacredGlassCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = TwilightDeep
        ) {
            Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "RECORD INTROSPECTION",
                    style = MaterialTheme.typography.titleMedium,
                    color = SadhanaGold
                )

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Prompt Title") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = TextOnDarkPrimary, unfocusedTextColor = TextOnDarkPrimary)
                )

                OutlinedTextField(
                    value = reflection,
                    onValueChange = { reflection = it },
                    label = { Text("Reflection Notes") },
                    placeholder = { Text("What did you observe about your mind today?") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 4,
                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = TextOnDarkPrimary, unfocusedTextColor = TextOnDarkPrimary)
                )

                OutlinedTextField(
                    value = gratitude,
                    onValueChange = { gratitude = it },
                    label = { Text("Gratitude Thought") },
                    placeholder = { Text("What are you grateful for?") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = TextOnDarkPrimary, unfocusedTextColor = TextOnDarkPrimary)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = onDismiss, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                        Text("Cancel", color = OnSurfaceVariant)
                    }
                    Button(
                        onClick = {
                            if (reflection.isNotBlank()) {
                                onSave(title, reflection, emotion, gratitude)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer)
                    ) {
                        Text("Save Entry")
                    }
                }
            }
        }
    }
}

// 6. Add Custom Schedule Item Dialog
@Composable
fun AddScheduleItemDialog(
    onDismiss: () -> Unit,
    onAdd: (String, String, String, Int) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var timeSlot by remember { mutableStateOf("06:00 AM") }
    var category by remember { mutableStateOf("YOGA") }
    var duration by remember { mutableIntStateOf(20) }

    Dialog(onDismissRequest = onDismiss) {
        SacredGlassCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = TwilightDeep
        ) {
            Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "ADD MINDFULNESS SCHEDULE SLOT",
                    style = MaterialTheme.typography.titleMedium,
                    color = SadhanaGold
                )

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Routine Title") },
                    placeholder = { Text("e.g. Evening Dhyana") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = TextOnDarkPrimary, unfocusedTextColor = TextOnDarkPrimary)
                )

                OutlinedTextField(
                    value = timeSlot,
                    onValueChange = { timeSlot = it },
                    label = { Text("Time Slot") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = TextOnDarkPrimary, unfocusedTextColor = TextOnDarkPrimary)
                )

                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("PRANAYAMA", "YOGA", "SWADHYAYA", "MEDITATION").forEach { cat ->
                        FilterChip(
                            selected = category == cat,
                            onClick = { category = cat },
                            label = { Text(cat, fontSize = 10.sp) }
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(onClick = onDismiss, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                        Text("Cancel", color = OnSurfaceVariant)
                    }
                    Button(
                        onClick = {
                            if (title.isNotBlank()) {
                                onAdd(title, timeSlot, category, duration)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer)
                    ) {
                        Text("Add to Schedule")
                    }
                }
            }
        }
    }
}

// 7. Volunteer Attendance Check-In Dialog
@Composable
fun VolunteerAttendanceCheckInDialog(
    onDismiss: () -> Unit,
    onCheckInSuccess: () -> Unit
) {
    var isCheckingIn by remember { mutableStateOf(false) }
    var isCheckedIn by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        SacredGlassCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = TwilightDeep
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.QrCodeScanner,
                    contentDescription = null,
                    tint = PeaceMint,
                    modifier = Modifier.size(48.dp)
                )

                Text(
                    text = "SEVA DIGITAL ATTENDANCE",
                    style = MaterialTheme.typography.titleMedium,
                    color = SadhanaGold,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Location: Student Vedic Center, Hall 2\nEvent: Morning Prayer & Cleanliness Seva\nDate: Today",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OnSurface,
                    textAlign = TextAlign.Center
                )

                if (isCheckedIn) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = PeaceMint)
                        Text("Attendance Verified! Karma hours logged.", color = PeaceMint)
                    }
                } else {
                    Button(
                        onClick = {
                            isCheckingIn = true
                            isCheckedIn = true
                            onCheckInSuccess()
                        },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text("Verify Geo-Attendance")
                    }
                }
            }
        }
    }
}
