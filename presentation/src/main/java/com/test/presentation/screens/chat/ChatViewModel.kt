package com.test.presentation.screens.chat

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.test.domain.models.Chat
import com.test.domain.models.Message
import com.test.domain.models.MessageColorMode
import com.test.domain.models.MessageRequest
import com.test.domain.models.MessagePreferences
import com.test.domain.usecases.GenerateMessageUseCase
import com.test.domain.usecases.GetChatByIdUseCase
import com.test.domain.usecases.LoadMessagesUseCase
import com.test.domain.usecases.SendMessageUseCase
import com.test.domain.utils.DEFAULT_CHAT_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val loadMessagesUseCase: LoadMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val generateMessageUseCase: GenerateMessageUseCase,
    private val getChatByIdUseCase: GetChatByIdUseCase
): ViewModel() {

    private val _chatState = MutableStateFlow(ChatState())
    val chatState: StateFlow<ChatState> = _chatState.asStateFlow()

    val pagingState: Flow<PagingData<Message>> = loadMessagesUseCase(DEFAULT_CHAT_ID).cachedIn(viewModelScope)

    fun generateMessage() {
        viewModelScope.launch(Dispatchers.IO) {
            generateMessageUseCase(DEFAULT_CHAT_ID)
        }
    }

    fun sendMessage(text: String) {
        val messageRequest = MessageRequest(DEFAULT_CHAT_ID, text, chatState.value.selectedPhotos)
        viewModelScope.launch(Dispatchers.IO) {
            sendMessageUseCase(messageRequest)
        }
        _chatState.update { it.copy(selectedPhotos = emptyList()) }
    }

    fun getChatData() {
        viewModelScope.launch(Dispatchers.IO) {
            val chat = getChatByIdUseCase(DEFAULT_CHAT_ID)
            _chatState.update { it.copy(chatInfo = chat) }
        }
    }

    fun attachPhotos(photos: List<Uri>) {
        val mapped = photos.map { it.toString() }
        _chatState.update { it.copy(selectedPhotos = mapped) }
    }

    data class ChatState(
        val chatInfo: Chat? = null,
        val selectedPhotos: List<String> = emptyList(),
        val messagePreferences: MessagePreferences = MessagePreferences(
            backgroundColorMode = MessageColorMode.PRIMARY,
            cornerRadius = 12,
            textSize = 14
        )
    )

}