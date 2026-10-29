package com.example.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.PsychologyAlt
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.content.ScriptureContent
import com.example.data.content.YogaContent
import com.example.ui.ActiveModal
import com.example.ui.AppTab
import com.example.ui.SadhanaViewModel
import com.example.ui.components.SacredGlassCard
import com.example.ui.theme.CelestialMidnight
import com.example.ui.theme.FlameGlow
import com.example.ui.theme.LotusLavender
import com.example.ui.theme.OnPrimary
import com.example.ui.theme.OnSurface
import com.example.ui.theme.OnSurfaceVariant
import com.example.ui.theme.OnTertiary
import com.example.ui.theme.PeaceMint
import com.example.ui.theme.Primary
import com.example.ui.theme.PrimaryContainer
import com.example.ui.theme.PrimaryFixedDim
import com.example.ui.theme.SacredAmber
import com.example.ui.theme.SadhanaGold
import com.example.ui.theme.Secondary
import com.example.ui.theme.Surface
import com.example.ui.theme.SurfaceBright
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.TertiaryContainer
import com.example.ui.theme.TextOnDarkPrimary
import com.example.ui.theme.TwilightDeep

@Composable
fun HomeScreen(
    viewModel: SadhanaViewModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Surface),
        contentPadding = PaddingValues(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // 1. Top Student Header Banner Card
        item {
            StudentPortalBanner(
                studentName = viewModel.studentName,
                studentClass = viewModel.studentClass,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }

        // 2. DAILY SADHANA Hero Workout Card
        item {
            DailySadhanaHeroCard(
                onBeginSadhana = {
                    viewModel.openModal(ActiveModal.PranayamaStation)
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // 3. Sacred Disciplines Quick-Action Pill Rail
        item {
            SacredDisciplinesRail(
                onJapaClick = { viewModel.openModal(ActiveModal.JapaMalaStation) },
                onMeditateClick = { viewModel.openModal(ActiveModal.MeditationTimer) },
                onIntrospectionClick = { viewModel.openModal(ActiveModal.IntrospectionLogger) },
                onAumClick = { viewModel.openModal(ActiveModal.MeditationTimer) },
                onPranayamClick = { viewModel.openModal(ActiveModal.PranayamaStation) }
            )
        }

        // 4. DAILY YOGA Section
        item {
            DailyYogaSection(
                onStartPranayam = { viewModel.openModal(ActiveModal.PranayamaStation) },
                onSuryaNamaskar = { viewModel.openModal(ActiveModal.SuryaNamaskarRunner) },
                onCategoryWise = { viewModel.selectTab(AppTab.PRACTICE) },
                onFlowClick = { flow -> viewModel.openModal(ActiveModal.YogaFlowRunner(flow)) },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // 5. DAILY SWADHYAYA (Study) Section
        item {
            DailySwadhyayaSection(
                onExplore = {
                    ScriptureContent.books.firstOrNull()?.let {
                        viewModel.openModal(ActiveModal.ScriptureReader(it))
                    }
                },
                onBookClick = { book ->
                    viewModel.openModal(ActiveModal.ScriptureReader(book))
                },
                onGuidelinesClick = { viewModel.selectTab(AppTab.LIBRARY) },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // 6. SANYAMA (Self-Discipline & Emotion Control) Section
        item {
            SanyamaSection(
                onBegin = { viewModel.openModal(ActiveModal.SanyamaSolutionFinder) },
                onPromptClick = { viewModel.openModal(ActiveModal.IntrospectionLogger) },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // 7. SEVA & INVOLVEMENT Section
        item {
            SevaSection(
                onProgramClick = { viewModel.selectTab(AppTab.SEVA) },
                onActionItemClick = { itemType ->
                    when (itemType) {
                        "Attendance" -> viewModel.openModal(ActiveModal.VolunteerAttendanceCheckIn)
                        "TimeTable" -> viewModel.selectTab(AppTab.PLANNER)
                        else -> viewModel.selectTab(AppTab.SEVA)
                    }
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // 8. EVENTS & COURSES Section
        item {
            EventsAndCoursesSection(
                onPdcClick = { viewModel.selectTab(AppTab.LIBRARY) },
                onWorkshopClick = { viewModel.openModal(ActiveModal.MeditationTimer) },
                onTimeTableClick = { viewModel.selectTab(AppTab.PLANNER) },
                onSyllabusClick = { viewModel.selectTab(AppTab.LIBRARY) },
                onDrillClick = { viewModel.selectTab(AppTab.PRACTICE) },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

// 1. Top Student Header Banner Card
@Composable
fun StudentPortalBanner(
    studentName: String,
    studentClass: String,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "halo_anim")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    SacredGlassCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = SurfaceContainerHigh.copy(alpha = 0.92f)
    ) {
        Box(modifier = Modifier.padding(18.dp)) {
            // Ambient glowing circles
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.TopEnd)
                    .scale(pulseScale)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(SadhanaGold.copy(alpha = 0.18f), Color.Transparent)
                        )
                    )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(bottom = 4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(SurfaceContainerHighest)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "STUDENT PORTAL",
                                style = MaterialTheme.typography.labelSmall,
                                color = SadhanaGold,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "Thu, Oct 29",
                            style = MaterialTheme.typography.bodySmall,
                            color = LotusLavender
                        )
                    }

                    Text(
                        text = studentName,
                        style = MaterialTheme.typography.headlineLarge,
                        color = TextOnDarkPrimary,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        Text(
                            text = studentClass,
                            style = MaterialTheme.typography.labelLarge,
                            color = PrimaryFixedDim
                        )
                        Box(
                            modifier = Modifier
                                .size(5.dp)
                                .clip(CircleShape)
                                .background(PeaceMint)
                        )
                        Text(
                            text = "Peaceful Afternoon",
                            style = MaterialTheme.typography.bodySmall,
                            color = PeaceMint
                        )
                    }
                }

                // Diya / Flame Icon container with ambient sacred aura
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainer)
                            .border(1.dp, FlameGlow.copy(alpha = 0.4f), CircleShape)
                            .shadow(8.dp, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .scale(pulseScale)
                                .background(
                                    Brush.radialGradient(
                                        colors = listOf(SacredAmber.copy(alpha = 0.35f), Color.Transparent)
                                    )
                                )
                        )
                        Icon(
                            imageVector = Icons.Default.LocalFireDepartment,
                            contentDescription = "Divine Flame",
                            tint = FlameGlow,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "DIVINE FLAME",
                        style = MaterialTheme.typography.labelSmall,
                        color = SadhanaGold,
                        fontSize = 9.sp
                    )
                }
            }
        }
    }
}

// 2. DAILY SADHNA Hero Workout Card
@Composable
fun DailySadhanaHeroCard(
    onBeginSadhana: () -> Unit,
    modifier: Modifier = Modifier
) {
    SacredGlassCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        backgroundColor = TwilightDeep.copy(alpha = 0.95f),
        borderColor = SadhanaGold.copy(alpha = 0.25f)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header Tags
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(SadhanaGold.copy(alpha = 0.18f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = SadhanaGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "DAILY SADHANA",
                        style = MaterialTheme.typography.labelSmall,
                        color = SadhanaGold,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(SurfaceContainerHigh.copy(alpha = 0.85f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = LotusLavender,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "20 MIN",
                        style = MaterialTheme.typography.labelSmall,
                        color = LotusLavender,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Title & Description
            Column {
                Text(
                    text = "20-Minute Spiritual Workout",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextOnDarkPrimary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Guided Sadhana: Mantra Meditation, Mindful Centering & Inner Reflection.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Secondary
                )
            }

            // Action row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onBeginSadhana,
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer),
                    modifier = Modifier
                        .height(48.dp)
                        .testTag("begin_sadhana_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Begin Sadhana",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy((-6).dp)) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainerHighest)
                            .border(1.dp, LotusLavender.copy(alpha = 0.3f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "OM",
                            style = MaterialTheme.typography.labelSmall,
                            color = LotusLavender
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(TertiaryContainer)
                            .border(1.dp, SadhanaGold.copy(alpha = 0.4f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "14D",
                            style = MaterialTheme.typography.labelSmall,
                            color = SadhanaGold
                        )
                    }
                }
            }
        }
    }
}

// 3. Sacred Disciplines Quick-Action Pill Rail
@Composable
fun SacredDisciplinesRail(
    onJapaClick: () -> Unit,
    onMeditateClick: () -> Unit,
    onIntrospectionClick: () -> Unit,
    onAumClick: () -> Unit,
    onPranayamClick: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SACRED DISCIPLINES",
                style = MaterialTheme.typography.labelSmall,
                color = OnSurfaceVariant,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Slide to explore",
                style = MaterialTheme.typography.bodySmall,
                color = Primary
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            DisciplinePill(
                icon = Icons.Default.AllInclusive,
                iconTint = SadhanaGold,
                title = "Japa Mantra",
                subtitle = "Chant 108 Beads",
                onClick = onJapaClick
            )
            DisciplinePill(
                icon = Icons.Default.SelfImprovement,
                iconTint = PeaceMint,
                title = "Meditate",
                subtitle = "Dhyana Silence",
                onClick = onMeditateClick
            )
            DisciplinePill(
                icon = Icons.Default.MenuBook,
                iconTint = LotusLavender,
                title = "Introspection",
                subtitle = "Daily Diary",
                onClick = onIntrospectionClick
            )
            DisciplinePill(
                icon = Icons.Default.Groups,
                iconTint = SadhanaGold,
                title = "Aum Sacred",
                subtitle = "Community Circle",
                onClick = onAumClick
            )
            DisciplinePill(
                icon = Icons.Default.Air,
                iconTint = PeaceMint,
                title = "Pranayam",
                subtitle = "Breath Harmony",
                onClick = onPranayamClick
            )
        }
    }
}

@Composable
fun DisciplinePill(
    icon: ImageVector,
    iconTint: Color,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .background(SurfaceContainerHigh)
            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(30.dp))
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(TwilightDeep),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
        }
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = TextOnDarkPrimary
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = OnSurfaceVariant,
                fontSize = 11.sp
            )
        }
    }
}

// 4. DAILY YOGA Section
@Composable
fun DailyYogaSection(
    onStartPranayam: () -> Unit,
    onSuryaNamaskar: () -> Unit,
    onCategoryWise: () -> Unit,
    onFlowClick: (com.example.data.content.YogaFlow) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SacredGlassCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = SurfaceContainerHigh
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
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
                            imageVector = Icons.Default.FitnessCenter,
                            contentDescription = null,
                            tint = PeaceMint,
                            modifier = Modifier.size(22.dp)
                        )
                        Text(
                            text = "DAILY YOGA",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextOnDarkPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = "ACTIVE DISCIPLINE",
                        style = MaterialTheme.typography.labelSmall,
                        color = PeaceMint
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    YogaNavRow(
                        icon = Icons.Default.Air,
                        iconTint = PeaceMint,
                        title = "Start Pranayam",
                        onClick = onStartPranayam
                    )
                    YogaNavRow(
                        icon = Icons.Default.WbSunny,
                        iconTint = SadhanaGold,
                        title = "Steps for Surya Namaskar",
                        onClick = onSuryaNamaskar
                    )
                    YogaNavRow(
                        icon = Icons.Default.Category,
                        iconTint = LotusLavender,
                        title = "Category-Wise Yoga",
                        onClick = onCategoryWise
                    )
                }
            }
        }

        // Horizontal scroll of Yoga Focus cards
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "TARGETED ASANA FLOWS",
                style = MaterialTheme.typography.labelSmall,
                color = OnSurfaceVariant,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                YogaContent.flows.forEach { flow ->
                    YogaFlowCard(flow = flow, onClick = { onFlowClick(flow) })
                }
            }
        }
    }
}

@Composable
fun YogaNavRow(
    icon: ImageVector,
    iconTint: Color,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainer)
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(SurfaceContainerHighest),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = OnSurface
            )
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = OnSurfaceVariant,
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
fun YogaFlowCard(
    flow: com.example.data.content.YogaFlow,
    onClick: () -> Unit
) {
    SacredGlassCard(
        modifier = Modifier
            .width(180.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        backgroundColor = SurfaceContainerHigh
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
            ) {
                AsyncImage(
                    model = flow.imageUrl,
                    contentDescription = flow.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(CelestialMidnight.copy(alpha = 0.85f))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = flow.tag,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(flow.badgeColorHex)
                    )
                }
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = flow.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextOnDarkPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = flow.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

// 5. DAILY SWADHYAYA (Self-Study) Section
@Composable
fun DailySwadhyayaSection(
    onExplore: () -> Unit,
    onBookClick: (com.example.data.content.SpiritualBook) -> Unit,
    onGuidelinesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SacredGlassCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = SurfaceContainerHigh
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
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
                            imageVector = Icons.Default.AutoStories,
                            contentDescription = null,
                            tint = SadhanaGold,
                            modifier = Modifier.size(22.dp)
                        )
                        Text(
                            text = "DAILY SWADHYAYA",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextOnDarkPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = "SELF-STUDY",
                        style = MaterialTheme.typography.labelSmall,
                        color = SadhanaGold
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    SwadhyayaLinkRow("Proper Guidelines For Study", onGuidelinesClick)
                    SwadhyayaLinkRow("Books For Particular Skills", onGuidelinesClick)
                    SwadhyayaLinkRow("Student Related Spiritual Book", onGuidelinesClick)
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = onExplore,
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer),
                        modifier = Modifier.height(42.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Explore",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(SurfaceContainer)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = null,
                            tint = LotusLavender,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "20 MIN",
                            style = MaterialTheme.typography.labelSmall,
                            color = LotusLavender
                        )
                    }
                }
            }
        }

        // Scriptural Wisdom Book Shelf Carousel
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "RECOMMENDED SCRIPTURAL WISDOM",
                style = MaterialTheme.typography.labelSmall,
                color = OnSurfaceVariant,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ScriptureContent.books.forEach { book ->
                    BookShelfCard(book = book, onClick = { onBookClick(book) })
                }
            }
        }
    }
}

@Composable
fun SwadhyayaLinkRow(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(SurfaceContainer)
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = OnSurface
        )
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = OnSurfaceVariant,
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
fun BookShelfCard(
    book: com.example.data.content.SpiritualBook,
    onClick: () -> Unit
) {
    SacredGlassCard(
        modifier = Modifier
            .width(140.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        backgroundColor = SurfaceContainer
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.75f)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                AsyncImage(
                    model = book.coverImageUrl,
                    contentDescription = book.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, CelestialMidnight.copy(alpha = 0.85f))
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(6.dp)
                ) {
                    Text(
                        text = book.categoryBadge,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(book.badgeColorHex)
                    )
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = book.title,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 13.sp,
                color = TextOnDarkPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = book.subtitle,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 11.sp,
                color = OnSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// 6. SANYAMA Section
@Composable
fun SanyamaSection(
    onBegin: () -> Unit,
    onPromptClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        SacredGlassCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = TwilightDeep.copy(alpha = 0.85f),
            borderColor = FlameGlow.copy(alpha = 0.2f)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
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
                            imageVector = Icons.Default.Psychology,
                            contentDescription = null,
                            tint = FlameGlow,
                            modifier = Modifier.size(22.dp)
                        )
                        Text(
                            text = "SANYAMA",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextOnDarkPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = "INNER HARMONY",
                        style = MaterialTheme.typography.labelSmall,
                        color = FlameGlow
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Self-Discipline & Emotion Control for academic serenity and mental poise.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Secondary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceContainerHigh.copy(alpha = 0.9f))
                        .clickable { onBegin() }
                        .padding(horizontal = 14.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Give answer and find Solution To Control Your Thought, Emotion And Action",
                        style = MaterialTheme.typography.bodyMedium,
                        color = OnSurface,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = SadhanaGold,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = onBegin,
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = SadhanaGold),
                        modifier = Modifier.height(42.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = OnTertiary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Begin",
                            style = MaterialTheme.typography.labelLarge,
                            color = OnTertiary,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(SurfaceContainer)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = null,
                            tint = OnSurface,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "10 MIN",
                            style = MaterialTheme.typography.labelSmall,
                            color = OnSurface
                        )
                    }
                }
            }
        }

        // Horizontal reflection cards
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SacredGlassCard(
                modifier = Modifier
                    .width(260.dp)
                    .clickable { onPromptClick() },
                shape = RoundedCornerShape(16.dp),
                backgroundColor = SurfaceContainerHigh
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "PROMPT OF THE DAY",
                        style = MaterialTheme.typography.labelSmall,
                        color = SadhanaGold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Impulse & Distraction Log",
                        style = MaterialTheme.typography.titleMedium,
                        color = TextOnDarkPrimary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Observe urges today without immediate reaction. Record inner triggers.",
                        style = MaterialTheme.typography.bodySmall,
                        color = OnSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            SacredGlassCard(
                modifier = Modifier
                    .width(260.dp)
                    .clickable { onBegin() },
                shape = RoundedCornerShape(16.dp),
                backgroundColor = SurfaceContainerHigh
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "SOLUTION ARCHIVE",
                        style = MaterialTheme.typography.labelSmall,
                        color = PeaceMint
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Anger Transformation",
                        style = MaterialTheme.typography.titleMedium,
                        color = TextOnDarkPrimary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Chant Om mentally 5 times when agitated before replying to others.",
                        style = MaterialTheme.typography.bodySmall,
                        color = OnSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

// 7. SEVA & INVOLVEMENT Section
@Composable
fun SevaSection(
    onProgramClick: () -> Unit,
    onActionItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SacredGlassCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = SurfaceContainerHigh
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
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
                        imageVector = Icons.Default.VolunteerActivism,
                        contentDescription = null,
                        tint = Primary,
                        modifier = Modifier.size(22.dp)
                    )
                    Text(
                        text = "SEVA & INVOLVEMENT",
                        style = MaterialTheme.typography.titleMedium,
                        color = TextOnDarkPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "KARMA YOGA",
                    style = MaterialTheme.typography.labelSmall,
                    color = Primary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceContainer)
                    .clickable { onProgramClick() }
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Take Part in Service and Be an active Volunteer - Improve inner Happiness and Satisfaction",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OnSurface,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = PrimaryFixedDim,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Badges
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(PrimaryContainer)
                        .clickable { onProgramClick() }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Handshake,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Our Program",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White
                    )
                }

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(SurfaceContainer)
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(PeaceMint)
                    )
                    Text(
                        text = "Active Volunteer",
                        style = MaterialTheme.typography.labelMedium,
                        color = PeaceMint
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 5-Action Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SevaActionGridButton(Icons.Default.AssignmentTurnedIn, SadhanaGold, "Task") {
                    onActionItemClick("Task")
                }
                SevaActionGridButton(Icons.Default.FactCheck, PeaceMint, "Attendance") {
                    onActionItemClick("Attendance")
                }
                SevaActionGridButton(Icons.Default.CalendarToday, LotusLavender, "TimeTable") {
                    onActionItemClick("TimeTable")
                }
                SevaActionGridButton(Icons.Default.Info, Secondary, "Details") {
                    onActionItemClick("Details")
                }
                SevaActionGridButton(Icons.Default.Campaign, FlameGlow, "Events") {
                    onActionItemClick("Events")
                }
            }
        }
    }
}

@Composable
fun SevaActionGridButton(
    icon: ImageVector,
    iconTint: Color,
    label: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(58.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(SurfaceContainer)
            .clickable { onClick() }
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = iconTint,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontSize = 10.sp,
            color = OnSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

// 8. EVENTS & COURSES Section
@Composable
fun EventsAndCoursesSection(
    onPdcClick: () -> Unit,
    onWorkshopClick: () -> Unit,
    onTimeTableClick: () -> Unit,
    onSyllabusClick: () -> Unit,
    onDrillClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(14.dp)) {
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
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = null,
                    tint = SadhanaGold,
                    modifier = Modifier.size(22.dp)
                )
                Text(
                    text = "EVENTS & COURSES",
                    style = MaterialTheme.typography.titleMedium,
                    color = TextOnDarkPrimary,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "SEMESTER VIEW",
                style = MaterialTheme.typography.labelSmall,
                color = Primary
            )
        }

        // Row 1: Workshops & Schedule
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "CORE WORKSHOPS & SCHEDULE",
                style = MaterialTheme.typography.labelSmall,
                color = OnSurfaceVariant,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // PDC Courses
                SacredGlassCard(
                    modifier = Modifier
                        .width(190.dp)
                        .clickable { onPdcClick() },
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = SurfaceContainerHigh
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(PrimaryContainer)
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "CERTIFICATION",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontSize = 9.sp,
                                    color = OnPrimary
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "PDC Courses",
                                style = MaterialTheme.typography.titleMedium,
                                color = TextOnDarkPrimary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Personality Development & Character Building.",
                                style = MaterialTheme.typography.bodySmall,
                                color = OnSurfaceVariant
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Enrolled • Module 3",
                            style = MaterialTheme.typography.labelSmall,
                            color = PrimaryFixedDim
                        )
                    }
                }

                // 2-Hour Weekly Workshop
                SacredGlassCard(
                    modifier = Modifier
                        .width(190.dp)
                        .clickable { onWorkshopClick() },
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = SurfaceContainerHigh
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(TertiaryContainer)
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "WEEKLY LIVE",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontSize = 9.sp,
                                    color = SadhanaGold
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "2-Hr Workshop",
                                style = MaterialTheme.typography.titleMedium,
                                color = TextOnDarkPrimary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Interactive spiritual debate & meditation practice.",
                                style = MaterialTheme.typography.bodySmall,
                                color = OnSurfaceVariant
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Sunday 08:00 AM",
                            style = MaterialTheme.typography.labelSmall,
                            color = SadhanaGold
                        )
                    }
                }

                // TimeTable
                SacredGlassCard(
                    modifier = Modifier
                        .width(170.dp)
                        .clickable { onTimeTableClick() },
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = SurfaceContainer
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = null,
                            tint = PeaceMint,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "TimeTable",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextOnDarkPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Daily routine chart.",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "View Slots →",
                            style = MaterialTheme.typography.labelSmall,
                            color = PeaceMint
                        )
                    }
                }

                // Syllabus Details
                SacredGlassCard(
                    modifier = Modifier
                        .width(170.dp)
                        .clickable { onSyllabusClick() },
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = SurfaceContainer
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = null,
                            tint = LotusLavender,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Syllabus Details",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextOnDarkPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Curriculum guide.",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Read Guide →",
                            style = MaterialTheme.typography.labelSmall,
                            color = LotusLavender
                        )
                    }
                }

                // Annual Events
                SacredGlassCard(
                    modifier = Modifier
                        .width(170.dp)
                        .clickable { onWorkshopClick() },
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = SurfaceContainer
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Icon(
                            imageVector = Icons.Default.Celebration,
                            contentDescription = null,
                            tint = FlameGlow,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Annual Events",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextOnDarkPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Gita Jayanti festival.",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Coming Up →",
                            style = MaterialTheme.typography.labelSmall,
                            color = FlameGlow
                        )
                    }
                }
            }
        }

        // Row 2: Specialized Student Modules
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "SPECIALIZED STUDENT MODULES",
                style = MaterialTheme.typography.labelSmall,
                color = OnSurfaceVariant,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Study Booster
                SacredGlassCard(
                    modifier = Modifier
                        .width(175.dp)
                        .clickable { onDrillClick() },
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = SurfaceContainerHigh
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PsychologyAlt,
                                contentDescription = null,
                                tint = PeaceMint,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "FOCUS",
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 9.sp,
                                color = PeaceMint
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Study Booster",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextOnDarkPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Exam focus techniques",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Start Drill",
                            style = MaterialTheme.typography.labelSmall,
                            color = PeaceMint
                        )
                    }
                }

                // Vedic Science
                SacredGlassCard(
                    modifier = Modifier
                        .width(175.dp)
                        .clickable { onPdcClick() },
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = SurfaceContainerHigh
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Science,
                                contentDescription = null,
                                tint = Primary,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "RATIONALE",
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 9.sp,
                                color = Primary
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Vedic Science",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextOnDarkPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Scientific reasoning behind traditions",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "6 Chapters",
                            style = MaterialTheme.typography.labelSmall,
                            color = Primary
                        )
                    }
                }

                // Skills & Values
                SacredGlassCard(
                    modifier = Modifier
                        .width(175.dp)
                        .clickable { onPdcClick() },
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = SurfaceContainerHigh
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.WorkspacePremium,
                                contentDescription = null,
                                tint = SadhanaGold,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "VALUES",
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 9.sp,
                                color = SadhanaGold
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Skills & Values",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextOnDarkPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Integrity, speech & seva",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Explore",
                            style = MaterialTheme.typography.labelSmall,
                            color = SadhanaGold
                        )
                    }
                }

                // Fasting Discipline
                SacredGlassCard(
                    modifier = Modifier
                        .width(175.dp)
                        .clickable { onPdcClick() },
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = SurfaceContainerHigh
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Spa,
                                contentDescription = null,
                                tint = FlameGlow,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "UPAVASA",
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 9.sp,
                                color = FlameGlow
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Fasting Rules",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextOnDarkPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Ekadashi & sattvic food",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Calendar",
                            style = MaterialTheme.typography.labelSmall,
                            color = FlameGlow
                        )
                    }
                }

                // Ask Doubt
                SacredGlassCard(
                    modifier = Modifier
                        .width(175.dp)
                        .clickable { onPdcClick() },
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = SurfaceContainerHigh
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Forum,
                                contentDescription = null,
                                tint = LotusLavender,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "VADA",
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 9.sp,
                                color = LotusLavender
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Ask Doubt",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextOnDarkPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Student philosophical debate",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Open Forum",
                            style = MaterialTheme.typography.labelSmall,
                            color = LotusLavender
                        )
                    }
                }
            }
        }
    }
}
