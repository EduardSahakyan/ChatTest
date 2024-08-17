package com.test.domain.usecases

import com.test.domain.models.Chat
import com.test.domain.repositories.ChatRepository

class GetChatByIdUseCase(
    private val chatRepository: ChatRepository
) {

    suspend operator fun invoke(chatId: Int): Chat {
        return chatRepository.getChatById(chatId)
    }

}