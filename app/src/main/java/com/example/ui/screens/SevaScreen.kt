package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.SevaTaskItem
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
import com.example.ui.theme.SadhanaGold
import com.example.ui.theme.Secondary
import com.example.ui.theme.Surface
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.TextOnDarkPrimary
import com.example.ui.theme.TwilightDeep

data class ForumDoubt(
    val author: String,
    val question: String,
    val answer: String,
    val category: String
)

@Composable
fun SevaScreen(
    viewModel: SadhanaViewModel,
    modifier: Modifier = Modifier
) {
    val sevaTasks by viewModel.sevaTasks.collectAsState()
    val analytics by viewModel.analytics.collectAsState()

    var newQuestionText by remember { mutableStateOf("") }
    val sampleDoubts = remember {
        mutableStateListOf(
            ForumDoubt(
                author = "Rahul S. (Class 2)",
                question = "How to deal with extreme sleepiness during early morning Brahma Muhurta study?",
                answer = "Wash face with cold water, perform 2 minutes of vigorous Kapalbhati breath, and sit with spine erect without leaning against the wall.",
                category = "Routine"
            ),
            ForumDoubt(
                author = "Priya M. (Class 1)",
                question = "In Bhagavad Gita 2.47, what does it mean to have no right to results?",
                answer = "It frees mental bandwidth from anxiety. When your intellect is not calculating future rewards, 100% of your cognition is applied to the present problem.",
                category = "Philosophy"
            )
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Surface),
        contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Seva Banner
        item {
            SacredGlassCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                backgroundColor = TwilightDeep.copy(alpha = 0.95f),
                borderColor = Primary.copy(alpha = 0.3f)
            ) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
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
                                text = "KARMA YOGA & VOLUNTEER SEVA",
                                style = MaterialTheme.typography.labelSmall,
                                color = Primary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "${analytics.totalKarmaHours} Hours Logged",
                            style = MaterialTheme.typography.labelSmall,
                            color = PeaceMint
                        )
                    }

                    Text(
                        text = "Selfless service elevates the soul, dissolves academic ego, and purifies the subconscious mind for higher learning.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = OnSurface
                    )

                    Button(
                        onClick = { viewModel.openModal(ActiveModal.VolunteerAttendanceCheckIn) },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer),
                        modifier = Modifier
                            .height(44.dp)
                            .testTag("check_in_attendance_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.FactCheck,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Digital Attendance Check-In",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White
                        )
                    }
                }
            }
        }

        // Section: Active Volunteer Tasks
        item {
            Text(
                text = "ACTIVE SEVA TASKS",
                style = MaterialTheme.typography.labelSmall,
                color = OnSurfaceVariant,
                fontWeight = FontWeight.Bold
            )
        }

        items(sevaTasks, key = { it.id }) { task ->
            SevaTaskCard(
                task = task,
                onToggle = { viewModel.toggleSevaTask(task.id, task.isCompleted) }
            )
        }

        // Section: Ask Doubt / Debate Forum
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
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
                            imageVector = Icons.Default.Forum,
                            contentDescription = null,
                            tint = LotusLavender,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "ASK DOUBT • PHILOSOPHICAL DEBATE",
                            style = MaterialTheme.typography.labelSmall,
                            color = LotusLavender,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = "Vada Forum",
                        style = MaterialTheme.typography.labelSmall,
                        color = SadhanaGold
                    )
                }

                // Question Input Box
                SacredGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = SurfaceContainerHigh
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        OutlinedTextField(
                            value = newQuestionText,
                            onValueChange = { newQuestionText = it },
                            placeholder = { Text("Ask spiritual doubt or discuss student discipline...", color = OnSurfaceVariant) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("ask_doubt_input"),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = SadhanaGold,
                                unfocusedBorderColor = Color.White.copy(alpha = 0.15f),
                                focusedTextColor = TextOnDarkPrimary,
                                unfocusedTextColor = TextOnDarkPrimary
                            ),
                            maxLines = 3
                        )

                        Button(
                            onClick = {
                                if (newQuestionText.isNotBlank()) {
                                    sampleDoubts.add(
                                        0,
                                        ForumDoubt(
                                            author = "ABC KUMAR (Class 1)",
                                            question = newQuestionText,
                                            answer = "Mentor reviewing: Remember to apply conscious breathing to observe thoughts before reactions.",
                                            category = "Student Inquiry"
                                        )
                                    )
                                    newQuestionText = ""
                                }
                            },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = SadhanaGold),
                            modifier = Modifier
                                .align(Alignment.End)
                                .height(40.dp)
                                .testTag("submit_doubt_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Send,
                                contentDescription = null,
                                tint = CelestialMidnight,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Post Doubt",
                                style = MaterialTheme.typography.labelSmall,
                                color = CelestialMidnight,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // Render forum doubts
                sampleDoubts.forEach { doubt ->
                    ForumDoubtCard(doubt = doubt)
                }
            }
        }
    }
}

@Composable
fun SevaTaskCard(
    task: SevaTaskItem,
    onToggle: () -> Unit
) {
    SacredGlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() },
        shape = RoundedCornerShape(14.dp),
        backgroundColor = if (task.isCompleted) SurfaceContainer.copy(alpha = 0.7f) else SurfaceContainerHigh,
        borderColor = if (task.isCompleted) PeaceMint.copy(alpha = 0.3f) else Color.White.copy(alpha = 0.1f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.weight(1f)
            ) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = { onToggle() },
                    colors = CheckboxDefaults.colors(
                        checkedColor = PeaceMint,
                        uncheckedColor = OnSurfaceVariant,
                        checkmarkColor = CelestialMidnight
                    ),
                    modifier = Modifier.testTag("seva_checkbox_${task.id}")
                )

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 14.sp,
                        color = if (task.isCompleted) OnSurfaceVariant else TextOnDarkPrimary,
                        textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = OnSurfaceVariant
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(SurfaceContainerHighest)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "${task.hours} hr",
                    style = MaterialTheme.typography.labelSmall,
                    color = SadhanaGold
                )
            }
        }
    }
}

@Composable
fun ForumDoubtCard(doubt: ForumDoubt) {
    SacredGlassCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        backgroundColor = SurfaceContainerHigh
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = doubt.author,
                    style = MaterialTheme.typography.labelSmall,
                    color = SadhanaGold
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SurfaceContainerHighest)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = doubt.category,
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 9.sp,
                        color = LotusLavender
                    )
                }
            }

            Text(
                text = "Q: ${doubt.question}",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 13.sp,
                color = TextOnDarkPrimary
            )

            Text(
                text = "A: ${doubt.answer}",
                style = MaterialTheme.typography.bodySmall,
                color = PeaceMint
            )
        }
    }
}
