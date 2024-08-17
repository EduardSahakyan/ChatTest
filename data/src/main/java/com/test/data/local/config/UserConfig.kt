package com.test.data.local.config

import android.content.SharedPreferences

class UserConfig(
    private val userPreferences: SharedPreferences
) {

    fun saveUserId(id: Int) {
        userPreferences.edit().putInt(USER_ID, id).apply()
    }

    fun getUserId(): Int {
        val id = userPreferences.getInt(USER_ID, UNKNOWN_ID)
        if (id == UNKNOWN_ID) throw IllegalStateException("Not logged in")
        return id
    }

    fun getPageSize(): Int {
        return userPreferences.getInt(PAGE_SIZE, DEFAULT_PAGE_SIZE)
    }

    fun setPageSize(size: Int) {
        userPreferences.edit().putInt(PAGE_SIZE, size).apply()
    }

    companion object {
        private const val USER_ID = "user_id"
        private const val PAGE_SIZE = "page_size"
        private const val DEFAULT_PAGE_SIZE = 30
        private const val UNKNOWN_ID = -1
        const val MY_ID = 1
        const val OTHER_ID = 2
    }

}