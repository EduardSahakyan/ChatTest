package com.test.data.local.views

import androidx.room.DatabaseView

@DatabaseView(
    viewName = "message_view",
    value = "SELECT M.id, M.chatId, M.text, M.photos, M.date, M.senderId, U.photo AS senderPhoto, U.name AS senderName " +
        "FROM messages AS M INNER JOIN users AS U ON U.id = M.senderId"
)
data class MessageView(
    val id: Int,
    val chatId: Int,
    val text: String,
    val photos: String,
    val date: Long,
    val senderId: Int,
    val senderPhoto: String,
    val senderName: String
)
