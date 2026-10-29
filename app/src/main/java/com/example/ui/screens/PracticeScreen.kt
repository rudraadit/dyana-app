package com.example.ui.screens

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.content.YogaContent
import com.example.ui.ActiveModal
import com.example.ui.SadhanaViewModel
import com.example.ui.components.SacredGlassCard
import com.example.ui.theme.CelestialMidnight
import com.example.ui.theme.FlameGlow
import com.example.ui.theme.LotusLavender
import com.example.ui.theme.OnPrimary
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
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun PracticeScreen(
    viewModel: SadhanaViewModel,
    modifier: Modifier = Modifier
) {
    var selectedSection by remember { mutableIntStateOf(0) }
    val sections = listOf("Pranayama", "Japa Mala", "Meditation", "Surya Namaskar")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Surface)
    ) {
        // Top Section Tab Bar
        TabRow(
            selectedTabIndex = selectedSection,
            containerColor = TwilightDeep.copy(alpha = 0.9f),
            contentColor = SadhanaGold,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedSection]),
                    color = SadhanaGold,
                    height = 3.dp
                )
            }
        ) {
            sections.forEachIndexed { index, title ->
                Tab(
                    selected = selectedSection == index,
                    onClick = { selectedSection = index },
                    text = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = if (selectedSection == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedSection == index) SadhanaGold else OnSurfaceVariant
                        )
                    }
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 90.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (selectedSection) {
                0 -> {
                    item {
                        InteractivePranayamaMandalaView(viewModel = viewModel)
                    }
                }
                1 -> {
                    item {
                        InteractiveJapaMalaBeadView(viewModel = viewModel)
                    }
                }
                2 -> {
                    item {
                        GuidedMeditationTimerView(viewModel = viewModel)
                    }
                }
                3 -> {
                    item {
                        SuryaNamaskarQuickRunner(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

// 1. Interactive Pranayama Breathing Mandala
@Composable
fun InteractivePranayamaMandalaView(viewModel: SadhanaViewModel) {
    var isRunning by remember { mutableStateOf(false) }
    var currentPhase by remember { mutableStateOf("Inhale") } // Inhale, Hold, Exhale, Hold
    var secondsInPhase by remember { mutableStateOf(4) }
    var totalCyclesCompleted by remember { mutableIntStateOf(0) }
    var selectedPace by remember { mutableStateOf("Box Breathing (4-4-4-4)") }

    val context = LocalContext.current
    val vibrator = remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val manager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
            manager?.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        }
    }

    // Breathing Mandala Expansion Scale
    val breathScale = remember { Animatable(0.7f) }

    LaunchedEffect(isRunning, currentPhase) {
        if (isRunning) {
            // Haptic cue at start of phase
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator?.vibrate(VibrationEffect.createOneShot(40, VibrationEffect.DEFAULT_AMPLITUDE))
            }

            when (currentPhase) {
                "Inhale" -> {
                    breathScale.animateTo(
                        targetValue = 1.25f,
                        animationSpec = tween(durationMillis = 4000, easing = LinearEasing)
                    )
                    currentPhase = "Hold (Full)"
                }
                "Hold (Full)" -> {
                    delay(4000)
                    currentPhase = "Exhale"
                }
                "Exhale" -> {
                    breathScale.animateTo(
                        targetValue = 0.7f,
                        animationSpec = tween(durationMillis = 4000, easing = LinearEasing)
                    )
                    currentPhase = "Hold (Empty)"
                }
                "Hold (Empty)" -> {
                    delay(4000)
                    totalCyclesCompleted += 1
                    if (totalCyclesCompleted % 3 == 0) {
                        viewModel.logSadhanaActivity(meditationMin = 2, disciplineName = "Pranayama Breath")
                    }
                    currentPhase = "Inhale"
                }
            }
        }
    }

    SacredGlassCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        backgroundColor = TwilightDeep.copy(alpha = 0.95f),
        borderColor = PeaceMint.copy(alpha = 0.3f)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Air,
                        contentDescription = null,
                        tint = PeaceMint,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "PRANAYAMA MANDALA",
                        style = MaterialTheme.typography.labelSmall,
                        color = PeaceMint,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "Cycle $totalCyclesCompleted of 9",
                    style = MaterialTheme.typography.labelSmall,
                    color = SadhanaGold
                )
            }

            // Central Sacred Breathing Mandala
            Box(
                modifier = Modifier
                    .size(230.dp)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                // Expanding outer aura petals
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(breathScale.value)
                ) {
                    val radius = size.minDimension / 2.3f
                    val center = Offset(size.width / 2, size.height / 2)

                    // Draw 8 glowing mandala petals
                    for (i in 0 until 8) {
                        val angle = (i * (2 * PI / 8)).toFloat()
                        val petalCenter = Offset(
                            x = center.x + (radius * 0.45f) * cos(angle),
                            y = center.y + (radius * 0.45f) * sin(angle)
                        )
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(PeaceMint.copy(alpha = 0.35f), Color.Transparent),
                                center = petalCenter,
                                radius = radius * 0.6f
                            ),
                            center = petalCenter,
                            radius = radius * 0.5f
                        )
                    }

                    // Concentric rings
                    drawCircle(
                        color = PeaceMint.copy(alpha = 0.5f),
                        radius = radius,
                        center = center,
                        style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
                    )
                    drawCircle(
                        color = SadhanaGold.copy(alpha = 0.4f),
                        radius = radius * 0.75f,
                        center = center,
                        style = Stroke(width = 1.5.dp.toPx())
                    )
                }

                // Inner Status Core
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .background(CelestialMidnight.copy(alpha = 0.85f))
                        .border(1.dp, SadhanaGold.copy(alpha = 0.4f), CircleShape)
                        .padding(8.dp)
                ) {
                    Text(
                        text = if (isRunning) currentPhase.uppercase() else "READY",
                        style = MaterialTheme.typography.labelSmall,
                        color = if (currentPhase.contains("Inhale")) PeaceMint else if (currentPhase.contains("Exhale")) LotusLavender else SadhanaGold,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = if (isRunning) "4s" else "Tap Start",
                        style = MaterialTheme.typography.titleMedium,
                        color = TextOnDarkPrimary
                    )
                }
            }

            // Pace & Pattern Selector
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                listOf("Box Breathing (4s)", "Anulom Vilom (5s)", "Deep Om (6s)").forEach { pattern ->
                    FilterChip(
                        selected = selectedPace.startsWith(pattern.take(3)),
                        onClick = { selectedPace = pattern },
                        label = { Text(text = pattern, fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = PrimaryContainer,
                            selectedLabelColor = Color.White
                        ),
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }

            // Control Trigger Button
            Button(
                onClick = { isRunning = !isRunning },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isRunning) FlameGlow else PrimaryContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("pranayama_toggle_button")
            ) {
                Icon(
                    imageVector = if (isRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isRunning) "Pause Breathing Session" else "Start Mindful Pranayama",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )
            }
        }
    }
}

// 2. Interactive Japa Mala Counter (108 Beads)
@Composable
fun InteractiveJapaMalaBeadView(viewModel: SadhanaViewModel) {
    val count by viewModel.japaCount.collectAsState()
    val rounds by viewModel.japaRounds.collectAsState()
    var selectedMantra by remember { mutableStateOf("Gayatri Mantra") }

    val mantras = listOf(
        "Gayatri Mantra" to "ॐ भूर्भुवः स्वः तत्सवितुर्वरेण्यं भर्गो देवस्य धीमहि धियो यो नः प्रचोदयात्",
        "Maha Mrityunjaya" to "ॐ त्र्यम्बकं यजामहे सुगन्धिं पुष्टिवर्धनम् उर्वारुकमिव बन्धनान् मृत्योर्मुक्षीय मामृतात्",
        "Hare Krishna" to "हरे कृष्ण हरे कृष्ण कृष्ण कृष्ण हरे हरे । हरे राम हरे राम राम राम हरे हरे ॥",
        "Om Namah Shivaya" to "ॐ नमः शिवाय"
    )

    val context = LocalContext.current
    val vibrator = remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val manager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
            manager?.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        }
    }

    SacredGlassCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        backgroundColor = SurfaceContainerHigh,
        borderColor = SadhanaGold.copy(alpha = 0.35f)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AllInclusive,
                        contentDescription = null,
                        tint = SadhanaGold,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "108 JAPA MALA BEADS",
                        style = MaterialTheme.typography.labelSmall,
                        color = SadhanaGold,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Rounds: $rounds",
                        style = MaterialTheme.typography.labelMedium,
                        color = PeaceMint
                    )
                    IconButton(
                        onClick = { viewModel.resetJapa() },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Reset",
                            tint = OnSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            // Mantra selector chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                mantras.take(3).forEach { (name, _) ->
                    FilterChip(
                        selected = selectedMantra == name,
                        onClick = { selectedMantra = name },
                        label = { Text(text = name, fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = PrimaryContainer,
                            selectedLabelColor = Color.White
                        ),
                        modifier = Modifier.padding(horizontal = 3.dp)
                    )
                }
            }

            // Display selected mantra text
            val currentMantraText = mantras.firstOrNull { it.first == selectedMantra }?.second ?: ""
            Text(
                text = currentMantraText,
                style = MaterialTheme.typography.bodyMedium,
                color = LotusLavender,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            // Bead Ring Visualization
            Box(
                modifier = Modifier
                    .size(210.dp)
                    .clickable {
                        // Vibration tick
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            vibrator?.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE))
                        }
                        viewModel.incrementJapaBead()
                    }
                    .testTag("japa_bead_click_area"),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val radius = size.minDimension / 2.3f
                    val center = Offset(size.width / 2, size.height / 2)

                    // Draw 108 micro beads along circumference
                    val totalBeads = 108
                    for (i in 0 until totalBeads) {
                        val angle = (i * (2 * PI / totalBeads) - PI / 2).toFloat()
                        val beadPos = Offset(
                            x = center.x + radius * cos(angle),
                            y = center.y + radius * sin(angle)
                        )
                        val isChanted = i < count
                        drawCircle(
                            color = if (isChanted) SadhanaGold else SurfaceContainerHighest,
                            radius = if (isChanted) 3.5.dp.toPx() else 2.dp.toPx(),
                            center = beadPos
                        )
                    }

                    // Meru bead at top
                    drawCircle(
                        color = FlameGlow,
                        radius = 6.dp.toPx(),
                        center = Offset(center.x, center.y - radius)
                    )
                }

                // Center Touch Target
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .size(130.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(TwilightDeep, CelestialMidnight)
                            )
                        )
                        .border(2.dp, SadhanaGold.copy(alpha = 0.5f), CircleShape)
                ) {
                    Text(
                        text = "$count",
                        style = MaterialTheme.typography.headlineLarge,
                        fontSize = 36.sp,
                        color = SadhanaGold,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "OF 108 BEADS",
                        style = MaterialTheme.typography.labelSmall,
                        color = OnSurfaceVariant,
                        fontSize = 9.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Tap Bead",
                        style = MaterialTheme.typography.labelSmall,
                        color = PeaceMint
                    )
                }
            }
        }
    }
}

// 3. Guided Meditation Timer
@Composable
fun GuidedMeditationTimerView(viewModel: SadhanaViewModel) {
    var timerMinutes by remember { mutableIntStateOf(15) }
    var secondsLeft by remember { mutableIntStateOf(15 * 60) }
    var isTimerActive by remember { mutableStateOf(false) }
    var ambientSound by remember { mutableStateOf("Om Drone 432Hz") }

    LaunchedEffect(isTimerActive, secondsLeft) {
        if (isTimerActive && secondsLeft > 0) {
            delay(1000)
            secondsLeft -= 1
        } else if (isTimerActive && secondsLeft == 0) {
            isTimerActive = false
            viewModel.logSadhanaActivity(meditationMin = timerMinutes, disciplineName = "Dhyana Meditation")
        }
    }

    SacredGlassCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        backgroundColor = TwilightDeep.copy(alpha = 0.95f),
        borderColor = SadhanaGold.copy(alpha = 0.3f)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.SelfImprovement,
                        contentDescription = null,
                        tint = SadhanaGold,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "DHYANA MEDITATION TIMER",
                        style = MaterialTheme.typography.labelSmall,
                        color = SadhanaGold,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = null,
                        tint = PeaceMint,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = ambientSound,
                        style = MaterialTheme.typography.labelSmall,
                        color = PeaceMint
                    )
                }
            }

            // Duration selector
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                listOf(5, 10, 15, 20, 30).forEach { mins ->
                    FilterChip(
                        selected = timerMinutes == mins,
                        onClick = {
                            timerMinutes = mins
                            secondsLeft = mins * 60
                            isTimerActive = false
                        },
                        label = { Text(text = "$mins m", fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = PrimaryContainer,
                            selectedLabelColor = Color.White
                        ),
                        modifier = Modifier.padding(horizontal = 3.dp)
                    )
                }
            }

            // Circular Timer Countdown Display
            Box(
                modifier = Modifier.size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                val progress = if (timerMinutes > 0) (secondsLeft.toFloat() / (timerMinutes * 60)) else 1f

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val radius = size.minDimension / 2.3f
                    val center = Offset(size.width / 2, size.height / 2)

                    // Track background
                    drawCircle(
                        color = SurfaceContainerHighest,
                        radius = radius,
                        center = center,
                        style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
                    )

                    // Active progress arc
                    drawArc(
                        brush = Brush.sweepGradient(
                            listOf(SadhanaGold, FlameGlow, PeaceMint, SadhanaGold)
                        ),
                        startAngle = -90f,
                        sweepAngle = 360f * progress,
                        useCenter = false,
                        style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val min = secondsLeft / 60
                    val sec = secondsLeft % 60
                    Text(
                        text = String.format("%02d:%02d", min, sec),
                        style = MaterialTheme.typography.headlineLarge,
                        fontSize = 38.sp,
                        color = TextOnDarkPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (isTimerActive) "Deep Stillness" else "Ready to Begin",
                        style = MaterialTheme.typography.bodySmall,
                        color = LotusLavender
                    )
                }
            }

            // Action Button
            Button(
                onClick = { isTimerActive = !isTimerActive },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isTimerActive) FlameGlow else PrimaryContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("meditation_timer_toggle")
            ) {
                Icon(
                    imageVector = if (isTimerActive) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isTimerActive) "Pause Meditation" else "Begin Silent Dhyana",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )
            }
        }
    }
}

// 4. Surya Namaskar Quick Runner
@Composable
fun SuryaNamaskarQuickRunner(viewModel: SadhanaViewModel) {
    SacredGlassCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        backgroundColor = SurfaceContainerHigh
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.WbSunny,
                        contentDescription = null,
                        tint = SadhanaGold,
                        modifier = Modifier.size(22.dp)
                    )
                    Text(
                        text = "SURYA NAMASKAR (12 STEPS)",
                        style = MaterialTheme.typography.titleMedium,
                        color = TextOnDarkPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Vedic Sun Flow",
                    style = MaterialTheme.typography.labelSmall,
                    color = SadhanaGold
                )
            }

            Text(
                text = "Classical sequence honoring the solar vitality within. Synchronizes breath with prana flow.",
                style = MaterialTheme.typography.bodyMedium,
                color = OnSurfaceVariant
            )

            // Step Preview List
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                YogaContent.suryaNamaskar12Steps.take(4).forEach { step ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(SurfaceContainer)
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(26.dp)
                                    .clip(CircleShape)
                                    .background(SurfaceContainerHighest),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${step.stepNumber}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = SadhanaGold
                                )
                            }
                            Column {
                                Text(
                                    text = step.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontSize = 13.sp,
                                    color = TextOnDarkPrimary
                                )
                                Text(
                                    text = step.sanskritName,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontSize = 11.sp,
                                    color = LotusLavender
                                )
                            }
                        }
                        Text(
                            text = step.breathingCue,
                            style = MaterialTheme.typography.labelSmall,
                            color = PeaceMint
                        )
                    }
                }
            }

            Button(
                onClick = { viewModel.openModal(ActiveModal.SuryaNamaskarRunner) },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("launch_surya_runner_button")
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Launch Guided 12-Step Session",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )
            }
        }
    }
}
