package com.test.domain.usecases

import com.test.domain.models.MessageRequest
import com.test.domain.repositories.ChatRepository

class SendMessageUseCase(
    private val chatRepository: ChatRepository
) {

    suspend operator fun invoke(request: MessageRequest) {
        chatRepository.sendMessage(request)
    }

}