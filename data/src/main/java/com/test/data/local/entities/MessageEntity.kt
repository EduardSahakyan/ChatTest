package com.test.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "messages",
    foreignKeys = [
        ForeignKey(
            entity = ChatEntity::class,
            parentColumns = ["id"],
            childColumns = ["chatId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["senderId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("chatId", index = true)
    val chatId: Int,
    @ColumnInfo("senderId", index = true)
    val senderId: Int,
    @ColumnInfo("text")
    val text: String,
    @ColumnInfo("photos")
    val photos: String,
    @ColumnInfo("date")
    val date: Long
)