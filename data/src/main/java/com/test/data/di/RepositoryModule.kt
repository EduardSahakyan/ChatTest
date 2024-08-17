package com.test.data.di

import android.content.Context
import com.test.data.local.config.UserConfig
import com.test.data.local.dao.ChatDao
import com.test.data.local.filemanager.FileManager
import com.test.data.repositories.ChatRepositoryImpl
import com.test.domain.repositories.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideChatRepository(
        chatDao: ChatDao,
        userConfig: UserConfig,
        fileManager: FileManager
    ): ChatRepository {
        return ChatRepositoryImpl(chatDao, userConfig, fileManager)
    }

}