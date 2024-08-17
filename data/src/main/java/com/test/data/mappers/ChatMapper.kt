package com.test.data.mappers

import com.test.data.local.views.ChatView
import com.test.domain.models.Chat

fun ChatView.toChat() = Chat(
    id = id,
    title = title,
    photo = photo,
    membersQuantity = membersQuantity
)