package com.test.presentation.screens.chat.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.presentation.R
import com.test.presentation.common.extensions.thenIf

@Composable
fun ChatInputBar(
    modifier: Modifier = Modifier,
    isPhotosAttached: Boolean,
    onImagesPicked: (List<Uri>) -> Unit,
    onSendClicked: (String) -> Unit,
    onVoiceClicked: () -> Unit
) {

    val text = remember { mutableStateOf("") }
    val isVoiceMode = remember(isPhotosAttached) { derivedStateOf { text.value.isEmpty() && isPhotosAttached.not() } }
    val imagePickerLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickMultipleVisualMedia(10)) { uris ->
        onImagesPicked(uris)
    }

    Column(
        modifier = modifier
    ) {
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onTertiary)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .height(48.dp)
                    .weight(1F)
                    .clip(RoundedCornerShape(32.dp))
                    .background(color = MaterialTheme.colorScheme.tertiary)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_attach),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .clickable {
                            imagePickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        }
                )
                TextField(
                    value = text.value,
                    onValueChange = {
                        text.value = it
                    },
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 13.sp,
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.message_hint),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            lineHeight = 16.sp
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        cursorColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(42.dp)
                    .clip(AbsoluteRoundedCornerShape(32.dp))
                    .background(MaterialTheme.colorScheme.secondary)
                    .clickable {
                        if (isVoiceMode.value) {
                            onVoiceClicked()
                        } else {
                            onSendClicked(text.value)
                            text.value = ""
                        }
                    }
            ) {
                val drawableId = if (isVoiceMode.value) R.drawable.ic_voice else R.drawable.ic_send
                Icon(
                    painter = painterResource(id = drawableId),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.thenIf(isVoiceMode.value.not()) {
                        padding(start = 2.dp)
                    }
                )
            }
        }
    }

}