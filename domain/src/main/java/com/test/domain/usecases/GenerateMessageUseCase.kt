package com.test.domain.usecases

import com.test.domain.repositories.ChatRepository

class GenerateMessageUseCase(
    private val chatRepository: ChatRepository
) {

    suspend operator fun invoke(chatId: Int) {
        chatRepository.generateMessage(chatId)
    }

}