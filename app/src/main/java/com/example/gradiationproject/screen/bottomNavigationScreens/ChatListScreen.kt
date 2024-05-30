package com.example.gradiationproject.screen.bottomNavigationScreens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gradiationproject.MyApplication.Companion.appContext
import com.example.gradiationproject.R
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.models.InitializationState
import io.getstream.chat.android.models.User
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory
import io.getstream.chat.android.state.plugin.config.StatePluginConfig
import io.getstream.chat.android.state.plugin.factory.StreamStatePluginFactory
import kotlinx.coroutines.runBlocking

@Composable
fun ChatListScreen() {
    val context = LocalContext.current
    // 1 - Set up the OfflinePlugin for offline storage
    val offlinePluginFactory = StreamOfflinePluginFactory(appContext = appContext)
    val statePluginFactory = StreamStatePluginFactory(config = StatePluginConfig(), appContext = appContext)

    // 2 - Set up the client for API calls and with the plugin for offline storage
    val client = ChatClient.Builder("jgpyg2janup5", appContext)
        .withPlugins(offlinePluginFactory, statePluginFactory)
        .logLevel(ChatLogLevel.ALL) // Set to NOTHING in prod
        .build()

    val user = User(
        id = "tutorial-droid",
        name = "Youssef Soliman",
        image = "https://bit.ly/2TIt8NR"
    )

    // 3 - Connect the user
    LaunchedEffect(Unit) {
        client.connectUser(
            user = user,
            token = client.devToken(user.id)
        ).enqueue { result ->
            if (result.isSuccess) {
                // User connected successfully, create channels if needed
                createDefaultChannels(client, user.id)
            }
        }
    }

    val clientInitializationState by client.clientState.initializationState.collectAsState()

    ChatTheme {
        when (clientInitializationState) {
            InitializationState.COMPLETE -> {
                ChannelsScreen(
                    title = stringResource(id = R.string.app_name),
                    isShowingSearch = true,
                    onItemClick = { channel ->
                        context.startActivity(ChannelActivity.getIntent(context, channel.cid))
                    },
                    onBackPressed = { }
                )
            }

            InitializationState.INITIALIZING -> {
                Text(text = "Initialising...")
            }

            InitializationState.NOT_INITIALIZED -> {
                Text(text = "Not initialized...")
            }
        }
    }
}

// Function to create multiple default channels
fun createDefaultChannels(client: ChatClient, userId: String) {
    val channels = listOf(
        "Youssef" to "Youssef",
        "Amany" to "Amany",
        "Katrin" to "Katrin",
        "Abanuob" to "Abanuob",
        "Mario" to "Mario"
    )

    runBlocking {
        channels.forEach { (channelId, channelName) ->
            val result = client.createChannel(
                channelType = "messaging",
                channelId = channelId,
                memberIds = listOf(userId),
                extraData = mapOf("name" to channelName)
            ).await()

            if (!result.isSuccess) {
                // Handle error
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatListScreenPreview() {
    ChatListScreen()
}