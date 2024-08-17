package com.test.presentation.screens.chat.components

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.test.domain.models.Message
import com.test.domain.models.MessageColorMode
import com.test.domain.models.MessagePreferences
import com.test.presentation.common.extensions.thenIf
import com.test.presentation.common.theme.Black50
import java.util.Locale

@Composable
fun MessageItem(
    messagePreferences: MessagePreferences,
    message: Message
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val minimumContainerWidth = (screenWidth * 0.5).dp
    val maximumContainerWidth = (screenWidth * 0.7).dp

    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.thenIf(message.isOwner) {
                align(Alignment.CenterEnd)
            }
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            ) {
                if (message.isOwner.not()) {
                    AsyncImage(
                        model = message.senderPhoto,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(AbsoluteRoundedCornerShape(32.dp))
                    )
                }
                Column(
                    modifier = Modifier
                        .widthIn(min = minimumContainerWidth, max = maximumContainerWidth)
                        .thenIf(message.isOwner.not()) {
                            padding(start = 8.dp)
                        }
                        .clip(RoundedCornerShape(messagePreferences.cornerRadius.dp))
                        .background(getContainerColor(message.isOwner, messagePreferences.backgroundColorMode))

                ) {
                    if (message.isOwner.not()) {
                        Text(
                            text = message.senderName,
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W500,
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .padding(horizontal = 12.dp)

                        )
                    }
                    if (message.text.isNotBlank()) {
                        Text(
                            text = message.text,
                            fontSize = messagePreferences.textSize.sp,
                            fontWeight = FontWeight.W400,
                            lineHeight = 18.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .padding(horizontal = 12.dp)
                        )
                    }
                    message.photos.getOrNull(0)?.let {
                        AsyncImage(
                            model = it,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(
                                width = maximumContainerWidth,
                                height = maximumContainerWidth / 1.5F
                            )
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 24.dp, bottom = 2.dp)
                    .thenIf(message.photos.isNotEmpty()) {
                        clip(RoundedCornerShape(12.dp))
                        .background(Black50)
                    }
            ) {
                Text(
                    text = formatTime(message.date),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.W400,
                    color = getTimeTextColor(message.photos.isNotEmpty()),
                    modifier = Modifier.padding(horizontal = 6.dp)
                )
            }
        }
    }
}

@Composable
private fun getContainerColor(isOwner: Boolean, mode: MessageColorMode): Color {
    return when(mode) {
        MessageColorMode.PRIMARY -> {
            if (isOwner) MaterialTheme.colorScheme.surfaceVariant
            else MaterialTheme.colorScheme.surface
        }
    }
}

private fun getTimeTextColor(hasPhotos: Boolean): Color {
    return if (hasPhotos) Color.White else Color.Gray
}

private fun formatTime(timeInMillis: Long): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(timeInMillis)
}