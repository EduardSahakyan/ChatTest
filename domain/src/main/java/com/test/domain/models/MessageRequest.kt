package com.test.domain.models

data class MessageRequest(
    val chatId: Int,
    val text: String,
    val photos: List<String>,
)