package com.test.presentation.screens.chat.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.test.domain.models.Chat
import com.test.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(
    modifier: Modifier = Modifier,
    chatInfo: Chat
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = chatInfo.photo,
                        contentDescription = null,
                        modifier = Modifier
                            .size(34.dp)
                            .clip(AbsoluteRoundedCornerShape(32.dp))
                    )
                    Column(
                        modifier = Modifier.padding(start = 16.dp),
                    ) {
                        Text(
                            text = chatInfo.title,
                            fontWeight = FontWeight.W500,
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = pluralStringResource(
                                id = R.plurals.chat_members,
                                count = chatInfo.membersQuantity,
                                chatInfo.membersQuantity
                            ),
                            fontWeight = FontWeight.W400,
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            color = Color.Gray
                        )
                    }
                }
            },
            navigationIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            },
            actions = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        )
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onTertiary)
    }


}