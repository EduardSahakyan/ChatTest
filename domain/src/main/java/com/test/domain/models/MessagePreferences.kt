package com.test.domain.models

data class MessagePreferences(
    val backgroundColorMode: MessageColorMode,
    val cornerRadius: Int,
    val textSize: Int
)

enum class MessageColorMode {
    PRIMARY
}