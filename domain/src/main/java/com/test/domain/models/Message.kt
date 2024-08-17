package com.test.domain.models

data class Message(
    val id: Int,
    val text: String,
    val photos: List<String>,
    val date: Long,
    val senderId: Int,
    val senderPhoto: String,
    val senderName: String,
    val isOwner: Boolean
)
