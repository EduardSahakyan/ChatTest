package com.test.data.di

import android.content.Context
import androidx.room.Room
import com.test.data.local.config.UserConfig
import com.test.data.local.dao.ChatDao
import com.test.data.local.database.AppDatabase
import com.test.data.local.filemanager.FileManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideChatDao(@ApplicationContext context: Context): ChatDao {
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "chat.db").build()
        return db.chatDao()
    }

    @Provides
    @Singleton
    fun provideUserConfig(@ApplicationContext context: Context): UserConfig {
        val userPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        return UserConfig(
            userPreferences = userPreferences
        )
    }

    @Provides
    @Singleton
    fun provideFileManager(@ApplicationContext context: Context) = FileManager(context)

}