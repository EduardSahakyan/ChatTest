package com.test.domain.di

import com.test.domain.repositories.ChatRepository
import com.test.domain.usecases.GenerateMessageUseCase
import com.test.domain.usecases.GetChatByIdUseCase
import com.test.domain.usecases.LoadMessagesUseCase
import com.test.domain.usecases.SendMessageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideLoadMessagesUseCase(chatRepository: ChatRepository) = LoadMessagesUseCase(chatRepository)

    @Provides
    fun provideSendMessageUseCase(chatRepository: ChatRepository) = SendMessageUseCase(chatRepository)

    @Provides
    fun provideGenerateMessageUseCase(chatRepository: ChatRepository) = GenerateMessageUseCase(chatRepository)

    @Provides
    fun provideGetChatByIdUseCase(chatRepository: ChatRepository) = GetChatByIdUseCase(chatRepository)

}