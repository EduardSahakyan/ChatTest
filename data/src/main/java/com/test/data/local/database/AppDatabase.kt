package com.test.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.data.local.dao.ChatDao
import com.test.data.local.entities.ChatEntity
import com.test.data.local.entities.MessageEntity
import com.test.data.local.entities.UserChatEntity
import com.test.data.local.entities.UserEntity
import com.test.data.local.views.ChatView
import com.test.data.local.views.MessageView

@Database(
    entities = [ChatEntity::class, MessageEntity::class, UserEntity::class, UserChatEntity::class],
    views = [MessageView::class, ChatView::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
}