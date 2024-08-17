package com.test.presentation.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.test.presentation.screens.chat.components.ChatInputBar
import com.test.presentation.screens.chat.components.ChatTopBar
import com.test.presentation.screens.chat.components.ChatView

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = "chat_screen") {
        viewModel.getChatData()
    }

    val state = viewModel.chatState.collectAsState()

    Scaffold(
        topBar = {
            state.value.chatInfo?.let { chat ->
                ChatTopBar(chatInfo = chat)
            }
        },
        bottomBar = {
            ChatInputBar(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .navigationBarsPadding()
                    .imePadding()
                    .padding(vertical = 10.dp),
                isPhotosAttached = state.value.selectedPhotos.isNotEmpty(),
                onImagesPicked = viewModel::attachPhotos,
                onSendClicked = viewModel::sendMessage,
                onVoiceClicked = viewModel::generateMessage
            )
        },
        content = { paddingValues ->
            ChatView(
                pagingState = viewModel.pagingState,
                messagePreferences = state.value.messagePreferences,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(paddingValues)
            )
        }
    )

}