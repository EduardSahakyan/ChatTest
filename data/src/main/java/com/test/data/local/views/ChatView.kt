package com.test.data.local.views

import androidx.room.DatabaseView

@DatabaseView(
    viewName = "chat_view",
    value = "SELECT C.id, C.title , C.photo, COUNT(UC.userId) AS membersQuantity FROM chats AS C " +
            "LEFT JOIN user_chat UC ON C.id = UC.chatId GROUP BY C.id"
)
class ChatView(
    val id: Int,
    val title: String,
    val photo: String,
    val membersQuantity: Int
)