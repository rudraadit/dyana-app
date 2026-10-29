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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun ProfileScreen(
    viewModel: SadhanaViewModel,
    modifier: Modifier = Modifier
) {
    val introspections by viewModel.introspections.collectAsState()
    var hapticsEnabled by remember { mutableStateOf(true) }
    var chimeSoundEnabled by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Surface),
        contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Student Profile ID Card
        item {
            StudentIdHeroCard(
                studentName = viewModel.studentName,
                studentClass = viewModel.studentClass
            )
        }

        // Spiritual Badges & Milestones
        item {
            SpiritualBadgesSection()
        }

        // Introspection Diary History
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "STUDENT INTROSPECTION LOGS",
                    style = MaterialTheme.typography.labelSmall,
                    color = OnSurfaceVariant,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceContainerHigh)
                        .clickable { viewModel.openModal(ActiveModal.IntrospectionLogger) }
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "+ Log Entry",
                        style = MaterialTheme.typography.labelSmall,
                        color = SadhanaGold
                    )
                }
            }
        }

        if (introspections.isEmpty()) {
            item {
                Text(
                    text = "No introspection logs yet. Record your first self-reflection!",
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceVariant,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        } else {
            items(introspections, key = { it.id }) { entry ->
                SacredGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    backgroundColor = SurfaceContainerHigh
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = entry.promptTitle,
                                style = MaterialTheme.typography.titleMedium,
                                fontSize = 13.sp,
                                color = SadhanaGold
                            )
                            Text(
                                text = "${entry.date} • ${entry.time}",
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 10.sp,
                                color = OnSurfaceVariant
                            )
                        }
                        Text(
                            text = entry.reflectionText,
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 12.sp,
                            color = TextOnDarkPrimary
                        )
                        if (entry.gratitudeNote.isNotBlank()) {
                            Text(
                                text = "Gratitude: ${entry.gratitudeNote}",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 11.sp,
                                color = PeaceMint
                            )
                        }
                    }
                }
            }
        }

        // Student Settings
        item {
            Text(
                text = "PRACTICE PREFERENCES",
                style = MaterialTheme.typography.labelSmall,
                color = OnSurfaceVariant,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            SacredGlassCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                backgroundColor = SurfaceContainerHigh
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    // Haptics
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Vibration,
                                contentDescription = null,
                                tint = SadhanaGold,
                                modifier = Modifier.size(20.dp)
                            )
                            Column {
                                Text(
                                    text = "Tactile Japa Bead Vibration",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontSize = 13.sp,
                                    color = TextOnDarkPrimary
                                )
                                Text(
                                    text = "Subtle haptic tick on each bead count",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = OnSurfaceVariant
                                )
                            }
                        }
                        Switch(
                            checked = hapticsEnabled,
                            onCheckedChange = { hapticsEnabled = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = SadhanaGold,
                                checkedTrackColor = TwilightDeep
                            )
                        )
                    }

                    // Chimes
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.VolumeUp,
                                contentDescription = null,
                                tint = PeaceMint,
                                modifier = Modifier.size(20.dp)
                            )
                            Column {
                                Text(
                                    text = "Sacred Temple Chimes",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontSize = 13.sp,
                                    color = TextOnDarkPrimary
                                )
                                Text(
                                    text = "Interval bell during dhyana meditation",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = OnSurfaceVariant
                                )
                            }
                        }
                        Switch(
                            checked = chimeSoundEnabled,
                            onCheckedChange = { chimeSoundEnabled = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = PeaceMint,
                                checkedTrackColor = TwilightDeep
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StudentIdHeroCard(
    studentName: String,
    studentClass: String
) {
    SacredGlassCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        backgroundColor = TwilightDeep.copy(alpha = 0.95f),
        borderColor = SadhanaGold.copy(alpha = 0.35f)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    listOf(SadhanaGold.copy(alpha = 0.3f), CelestialMidnight)
                                )
                            )
                            .border(1.5.dp, SadhanaGold, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null,
                            tint = SadhanaGold,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Column {
                        Text(
                            text = studentName,
                            style = MaterialTheme.typography.headlineMedium,
                            color = TextOnDarkPrimary,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = studentClass,
                                style = MaterialTheme.typography.bodySmall,
                                color = Primary
                            )
                            Box(
                                modifier = Modifier
                                    .size(4.dp)
                                    .clip(CircleShape)
                                    .background(SadhanaGold)
                            )
                            Text(
                                text = "ID: #SDK-2026-09",
                                style = MaterialTheme.typography.bodySmall,
                                color = OnSurfaceVariant
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceContainerHighest)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "VERIFIED",
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 9.sp,
                        color = PeaceMint,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Divine Flame Level
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(
                            imageVector = Icons.Default.LocalFireDepartment,
                            contentDescription = null,
                            tint = FlameGlow,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Divine Flame: Level 4 Disciple",
                            style = MaterialTheme.typography.labelSmall,
                            color = SadhanaGold,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = "820 / 1000 XP",
                        style = MaterialTheme.typography.labelSmall,
                        color = OnSurfaceVariant
                    )
                }
                LinearProgressIndicator(
                    progress = { 0.82f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = FlameGlow,
                    trackColor = SurfaceContainerHighest,
                )
            }
        }
    }
}

@Composable
fun SpiritualBadgesSection() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = "SPIRITUAL ATTAINMENT BADGES",
            style = MaterialTheme.typography.labelSmall,
            color = OnSurfaceVariant,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BadgeItem(Icons.Default.LocalFireDepartment, FlameGlow, "14-Day", "Streak")
            BadgeItem(Icons.Default.AllInclusive, SadhanaGold, "1,000", "Japa Beads")
            BadgeItem(Icons.Default.WbSunny, PeaceMint, "Surya", "Yogi")
            BadgeItem(Icons.Default.VolunteerActivism, Primary, "Karma", "Hero")
        }
    }
}

@Composable
fun BadgeItem(
    icon: ImageVector,
    iconTint: Color,
    line1: String,
    line2: String
) {
    Column(
        modifier = Modifier
            .width(76.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(SurfaceContainerHigh)
            .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(14.dp))
            .padding(vertical = 12.dp, horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(TwilightDeep),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = line1,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = TextOnDarkPrimary,
            fontSize = 11.sp
        )
        Text(
            text = line2,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 10.sp,
            color = OnSurfaceVariant
        )
    }
}
