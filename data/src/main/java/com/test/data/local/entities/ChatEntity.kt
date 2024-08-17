package com.test.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("chats")
class ChatEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("photo")
    val photo: String
)