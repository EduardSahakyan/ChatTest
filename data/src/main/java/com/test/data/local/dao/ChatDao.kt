package com.test.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.data.local.entities.ChatEntity
import com.test.data.local.entities.MessageEntity
import com.test.data.local.entities.UserChatEntity
import com.test.data.local.entities.UserEntity
import com.test.data.local.views.ChatView
import com.test.data.local.views.MessageView

@Dao
interface ChatDao {

    @Query("SELECT * FROM message_view WHERE chatId = :chatId ORDER BY date DESC")
    fun loadMessages(chatId: Int): PagingSource<Int, MessageView>

    @Query("SELECT * FROM chat_view WHERE id = :chatId")
    suspend fun getChatById(chatId: Int): ChatView

    @Insert
    suspend fun insertMessage(messageEntity: MessageEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChat(chatEntity: ChatEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserChat(userChatEntity: UserChatEntity)

}