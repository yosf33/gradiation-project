package com.example.gradiationproject.screen.bottomNavigationScreens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gradiationproject.screen.bottomNavigationScreens.ui.theme.GradiationProjectTheme
import io.getstream.chat.android.compose.ui.messages.MessagesScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.compose.viewmodel.messages.MessagesViewModelFactory

class ChannelActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1 - Load the ID of the selected channel
        val channelId = intent.getStringExtra(KEY_CHANNEL_ID)!!

        setContent {
            ChatTheme {
                MessagesScreen(
                    viewModelFactory = MessagesViewModelFactory(
                        context = this,
                        channelId = channelId,
                        messageLimit = 30
                    ),
                    onBackPressed = { finish() }
                )
            }
        }
    }
        // 3 - Create an intent to start this Activity, with a given channelId
        companion object {
            private const val KEY_CHANNEL_ID = "channelId"

            fun getIntent(context: Context, channelId: String): Intent {
                return Intent(context, ChannelActivity::class.java).apply {
                    putExtra(KEY_CHANNEL_ID, channelId)
                }
            }
        }


    }


