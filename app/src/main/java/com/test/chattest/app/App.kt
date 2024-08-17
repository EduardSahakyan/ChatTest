package com.test.chattest.app

import android.app.Application
import com.test.domain.repositories.ChatRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class App: Application() {

    @Inject
    lateinit var chatRepository: ChatRepository

    override fun onCreate() {
        super.onCreate()
        generateFakeData()
    }

    private fun generateFakeData() {
        MainScope().launch(Dispatchers.IO) {
            chatRepository.generateFakeData()
        }
    }

}