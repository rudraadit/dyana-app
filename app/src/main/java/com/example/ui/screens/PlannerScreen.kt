package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MindfulnessScheduleItem
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

@Composable
fun PlannerScreen(
    viewModel: SadhanaViewModel,
    modifier: Modifier = Modifier
) {
    val scheduleItems by viewModel.scheduleItems.collectAsState()
    val analytics by viewModel.analytics.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Surface)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 90.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Real-time Analytics Cards
            item {
                AnalyticsOverviewCard(analytics = analytics)
            }

            // 2. Weekly Consistency Bar Chart
            item {
                WeeklyAnalyticsChartCard(analytics = analytics)
            }

            // 3. Personalized Mindfulness Schedule Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "TODAY'S MINDFULNESS SCHEDULE",
                            style = MaterialTheme.typography.labelSmall,
                            color = SadhanaGold,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Personalized Vedic Routine",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 18.sp,
                            color = TextOnDarkPrimary
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(SurfaceContainerHigh)
                            .clickable { viewModel.openModal(ActiveModal.AddScheduleItem) }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                            .testTag("add_schedule_slot_button")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Slot",
                                tint = SadhanaGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Add Slot",
                                style = MaterialTheme.typography.labelSmall,
                                color = SadhanaGold
                            )
                        }
                    }
                }
            }

            // 4. Schedule Items List
            items(scheduleItems, key = { it.id }) { item ->
                ScheduleItemCard(
                    item = item,
                    onToggle = { viewModel.toggleScheduleItem(item.id, item.isCompleted) }
                )
            }
        }
    }
}

@Composable
fun AnalyticsOverviewCard(analytics: com.example.ui.AnalyticsSummary) {
    SacredGlassCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        backgroundColor = TwilightDeep.copy(alpha = 0.92f),
        borderColor = SadhanaGold.copy(alpha = 0.3f)
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
                        imageVector = Icons.Default.TrendingUp,
                        contentDescription = null,
                        tint = SadhanaGold,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "REAL-TIME PROGRESS ANALYTICS",
                        style = MaterialTheme.typography.labelSmall,
                        color = SadhanaGold,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Live Sync",
                    style = MaterialTheme.typography.labelSmall,
                    color = PeaceMint
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 4 Metric Columns
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Streak
                AnalyticsMetricBox(
                    icon = Icons.Default.LocalFireDepartment,
                    iconTint = FlameGlow,
                    value = "${analytics.currentStreakDays} Days",
                    label = "Active Streak"
                )
                // Today Minutes
                AnalyticsMetricBox(
                    icon = Icons.Default.Schedule,
                    iconTint = PeaceMint,
                    value = "${analytics.totalMinutesToday}m",
                    label = "Logged Today"
                )
                // Consistency
                AnalyticsMetricBox(
                    icon = Icons.Default.Check,
                    iconTint = SadhanaGold,
                    value = "${analytics.consistencyScore}%",
                    label = "Consistency"
                )
                // Karma Hours
                AnalyticsMetricBox(
                    icon = Icons.Default.TrendingUp,
                    iconTint = LotusLavender,
                    value = "${analytics.totalKarmaHours}h",
                    label = "Karma Seva"
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Discipline Distribution Progress Bar
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Today's Discipline Balance",
                        style = MaterialTheme.typography.bodySmall,
                        color = OnSurfaceVariant
                    )
                    Text(
                        text = "Yoga (35%) • Japa (25%) • Study (40%)",
                        style = MaterialTheme.typography.labelSmall,
                        color = LotusLavender,
                        fontSize = 10.sp
                    )
                }
                LinearProgressIndicator(
                    progress = { 0.82f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = SadhanaGold,
                    trackColor = SurfaceContainerHighest,
                )
            }
        }
    }
}

@Composable
fun AnalyticsMetricBox(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    value: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainer)
            .padding(horizontal = 8.dp, vertical = 10.dp)
            .width(72.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = TextOnDarkPrimary,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = OnSurfaceVariant,
            fontSize = 9.sp
        )
    }
}

@Composable
fun WeeklyAnalyticsChartCard(analytics: com.example.ui.AnalyticsSummary) {
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
                Text(
                    text = "WEEKLY SADHANA MINUTES",
                    style = MaterialTheme.typography.labelSmall,
                    color = OnSurfaceVariant,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Target: 60m / day",
                    style = MaterialTheme.typography.labelSmall,
                    color = PeaceMint
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bar Chart Canvas
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val maxMinutes = 90f
                    val barWidth = 24.dp.toPx()
                    val totalBars = analytics.dailyBreakdown.size
                    val spacing = (size.width - (barWidth * totalBars)) / (totalBars + 1)
                    val chartHeight = size.height - 24.dp.toPx()

                    // Target dotted guideline at 60m
                    val targetY = chartHeight - (60f / maxMinutes) * chartHeight
                    drawLine(
                        color = PeaceMint.copy(alpha = 0.35f),
                        start = Offset(0f, targetY),
                        end = Offset(size.width, targetY),
                        strokeWidth = 1.dp.toPx(),
                        pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                    )

                    analytics.dailyBreakdown.forEachIndexed { index, day ->
                        val x = spacing + index * (barWidth + spacing)
                        val barHeight = (day.minutes.toFloat() / maxMinutes) * chartHeight
                        val y = chartHeight - barHeight

                        val barBrush = Brush.verticalGradient(
                            colors = if (day.minutes >= 60) {
                                listOf(SadhanaGold, SacredAmber)
                            } else {
                                listOf(Primary, PrimaryContainer)
                            }
                        )

                        // Draw rounded vertical bar
                        drawRoundRect(
                            brush = barBrush,
                            topLeft = Offset(x, y),
                            size = Size(barWidth, barHeight),
                            cornerRadius = CornerRadius(6.dp.toPx(), 6.dp.toPx())
                        )
                    }
                }

                // Day Labels Row below bars
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    analytics.dailyBreakdown.forEach { day ->
                        Text(
                            text = day.dayLabel,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 10.sp,
                            color = OnSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ScheduleItemCard(
    item: MindfulnessScheduleItem,
    onToggle: () -> Unit
) {
    val categoryColor = when (item.category.uppercase()) {
        "PRANAYAMA" -> PeaceMint
        "YOGA" -> SadhanaGold
        "SWADHYAYA" -> LotusLavender
        "SANYAMA" -> FlameGlow
        else -> Primary
    }

    SacredGlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() },
        shape = RoundedCornerShape(16.dp),
        backgroundColor = if (item.isCompleted) SurfaceContainer.copy(alpha = 0.7f) else SurfaceContainerHigh,
        borderColor = if (item.isCompleted) PeaceMint.copy(alpha = 0.3f) else Color.White.copy(alpha = 0.12f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                // Interactive Checkbox
                Checkbox(
                    checked = item.isCompleted,
                    onCheckedChange = { onToggle() },
                    colors = CheckboxDefaults.colors(
                        checkedColor = PeaceMint,
                        uncheckedColor = OnSurfaceVariant,
                        checkmarkColor = CelestialMidnight
                    ),
                    modifier = Modifier.testTag("schedule_checkbox_${item.id}")
                )

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(categoryColor.copy(alpha = 0.2f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = item.category,
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 9.sp,
                                color = categoryColor,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "${item.durationMinutes} min",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 11.sp,
                            color = OnSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 14.sp,
                        color = if (item.isCompleted) OnSurfaceVariant else TextOnDarkPrimary,
                        textDecoration = if (item.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                    )

                    if (item.notes.isNotBlank()) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = item.notes,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 11.sp,
                            color = OnSurfaceVariant
                        )
                    }
                }
            }

            // Time slot badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceContainerHighest)
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text(
                    text = item.timeSlot,
                    style = MaterialTheme.typography.labelSmall,
                    color = SadhanaGold
                )
            }
        }
    }
}
