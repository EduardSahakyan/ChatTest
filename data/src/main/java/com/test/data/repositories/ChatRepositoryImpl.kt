package com.test.data.repositories

import android.icu.util.Calendar
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.test.data.local.config.UserConfig
import com.test.data.local.dao.ChatDao
import com.test.data.local.entities.ChatEntity
import com.test.data.local.entities.MessageEntity
import com.test.data.local.entities.UserChatEntity
import com.test.data.local.entities.UserEntity
import com.test.data.local.filemanager.FileManager
import com.test.data.mappers.toChat
import com.test.data.mappers.toMessage
import com.test.domain.models.Chat
import com.test.domain.models.Message
import com.test.domain.models.MessageRequest
import com.test.domain.repositories.ChatRepository
import com.test.domain.utils.DEFAULT_CHAT_ID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.UUID

class ChatRepositoryImpl(
    private val chatDao: ChatDao,
    private val userConfig: UserConfig,
    private val fileManager: FileManager
) : ChatRepository {

    override suspend fun generateFakeData() {
        chatDao.insertUser(UserEntity(UserConfig.MY_ID, "Eduard", ""))
        chatDao.insertUser(UserEntity(UserConfig.OTHER_ID, "New User", "https://fiverr-res.cloudinary.com/images/t_main1,q_auto,f_auto,q_auto,f_auto/gigs/115043840/original/2a08efbf22bb57fb0572b5b6792ceb2ea962dfb7/design-a-logo-or-profile-picture-for-you.jpg"))
        chatDao.insertChat(ChatEntity(DEFAULT_CHAT_ID, "Some Chat", "https://images-platform.99static.com//yX_hJnvr7TFJSzYF-b_Z5euG1kg=/62x794:812x1544/fit-in/500x500/99designs-contests-attachments/128/128777/attachment_128777932"))
        chatDao.insertUserChat(UserChatEntity(UserConfig.MY_ID, DEFAULT_CHAT_ID))
        chatDao.insertUserChat(UserChatEntity(UserConfig.OTHER_ID, DEFAULT_CHAT_ID))
        userConfig.saveUserId(UserConfig.MY_ID)
    }

    override fun loadMessages(chatId: Int): Flow<PagingData<Message>> {
        val pageSize = userConfig.getPageSize()
        return Pager(
            config = PagingConfig(pageSize = pageSize, initialLoadSize = pageSize),
            pagingSourceFactory = { chatDao.loadMessages(chatId) }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toMessage(userConfig.getUserId())
            }
        }
    }

    override suspend fun getChatById(chatId: Int): Chat {
        return chatDao.getChatById(chatId).toChat()
    }

    override suspend fun sendMessage(messageRequest: MessageRequest) {
        chatDao.insertMessage(createEntityFromRequest(messageRequest))
    }

    override suspend fun generateMessage(chatId: Int) {
        chatDao.insertMessage(generateMessageEntity(chatId))
    }

    private fun createEntityFromRequest(messageRequest: MessageRequest): MessageEntity {
        val photos = fileManager.copyImagesToInternalStorage(messageRequest.photos)
        return MessageEntity(
            id = 0,
            chatId = messageRequest.chatId,
            senderId = userConfig.getUserId(),
            text = messageRequest.text,
            photos = Json.encodeToString(photos),
            date = Calendar.getInstance().timeInMillis
        )
    }

    private fun generateMessageEntity(chatId: Int): MessageEntity {
        return MessageEntity(
            id = 0,
            chatId = chatId,
            senderId = UserConfig.OTHER_ID,
            text = "Some text + ${UUID.randomUUID()}",
            photos = Json.encodeToString(emptyList<String>()),
            date = Calendar.getInstance().timeInMillis
        )
    }

}