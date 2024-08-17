package com.test.data.mappers

import com.test.data.local.views.MessageView
import com.test.domain.models.Message
import kotlinx.serialization.json.Json

fun MessageView.toMessage(currentUserId: Int) = Message(
    id = id,
    text = text,
    photos = Json.decodeFromString(photos),
    date = date,
    senderId = senderId,
    senderPhoto = senderPhoto,
    senderName = senderName,
    isOwner = currentUserId == senderId
)