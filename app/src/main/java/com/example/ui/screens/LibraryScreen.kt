package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.content.ScriptureContent
import com.example.data.content.SpiritualBook
import com.example.ui.ActiveModal
import com.example.ui.SadhanaViewModel
import com.example.ui.components.SacredGlassCard
import com.example.ui.theme.LotusLavender
import com.example.ui.theme.OnSurface
import com.example.ui.theme.OnSurfaceVariant
import com.example.ui.theme.PeaceMint
import com.example.ui.theme.Primary
import com.example.ui.theme.PrimaryContainer
import com.example.ui.theme.SadhanaGold
import com.example.ui.theme.Surface
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.TextOnDarkPrimary
import com.example.ui.theme.TwilightDeep

@Composable
fun LibraryScreen(
    viewModel: SadhanaViewModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Surface),
        contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Column {
                Text(
                    text = "SWADHYAYA & SCRIPTURAL VAULT",
                    style = MaterialTheme.typography.labelSmall,
                    color = SadhanaGold,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Vedic Wisdom for Student Life",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextOnDarkPrimary
                )
            }
        }

        // Guidelines for Study Banner
        item {
            StudyGuidelinesBanner()
        }

        // Section Title: Spiritual Books
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SACRED STUDY TEXTS",
                    style = MaterialTheme.typography.labelSmall,
                    color = OnSurfaceVariant,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "4 Key Classics",
                    style = MaterialTheme.typography.labelSmall,
                    color = PeaceMint
                )
            }
        }

        // Book cards
        items(ScriptureContent.books, key = { it.id }) { book ->
            ScriptureBookRowCard(
                book = book,
                onOpen = { viewModel.openModal(ActiveModal.ScriptureReader(book)) }
            )
        }
    }
}

@Composable
fun StudyGuidelinesBanner() {
    SacredGlassCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = TwilightDeep.copy(alpha = 0.95f),
        borderColor = SadhanaGold.copy(alpha = 0.25f)
    ) {
        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.School,
                    contentDescription = null,
                    tint = SadhanaGold,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "PROPER GUIDELINES FOR STUDY",
                    style = MaterialTheme.typography.labelSmall,
                    color = SadhanaGold,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "1. Wake in Brahma Muhurta: study challenging subjects when brain waves are theta-calm.\n2. 50-10 Pomodoro: study for 50 minutes, then do 3 minutes of deep Pranayama.\n3. Pure Sensory Diet: avoid digital arguments before sleep to allow hippocampal memory consolidation.",
                style = MaterialTheme.typography.bodyMedium,
                color = OnSurface,
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
fun ScriptureBookRowCard(
    book: SpiritualBook,
    onOpen: () -> Unit
) {
    SacredGlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpen() },
        shape = RoundedCornerShape(18.dp),
        backgroundColor = SurfaceContainerHigh
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Book cover image
            Box(
                modifier = Modifier
                    .width(75.dp)
                    .aspectRatio(0.75f)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                AsyncImage(
                    model = book.coverImageUrl,
                    contentDescription = book.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Book Details
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(book.badgeColorHex).copy(alpha = 0.2f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = book.categoryBadge,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 9.sp,
                            color = Color(book.badgeColorHex),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = book.author,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 11.sp,
                        color = OnSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextOnDarkPrimary
                )

                Text(
                    text = book.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = LotusLavender
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = book.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Read",
                tint = SadhanaGold,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
