package com.test.domain.repositories

import androidx.paging.PagingData
import com.test.domain.models.Chat
import com.test.domain.models.Message
import com.test.domain.models.MessageRequest
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun generateFakeData()

    fun loadMessages(chatId: Int): Flow<PagingData<Message>>

    suspend fun getChatById(chatId: Int): Chat

    suspend fun sendMessage(messageRequest: MessageRequest)

    suspend fun generateMessage(chatId: Int)

}