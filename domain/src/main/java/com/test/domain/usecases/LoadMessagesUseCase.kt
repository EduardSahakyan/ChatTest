package com.test.domain.usecases

import androidx.paging.PagingData
import com.test.domain.models.Message
import com.test.domain.repositories.ChatRepository
import kotlinx.coroutines.flow.Flow

class LoadMessagesUseCase(
    private val chatRepository: ChatRepository
) {

    operator fun invoke(chatId: Int): Flow<PagingData<Message>> {
        return chatRepository.loadMessages(chatId)
    }

}