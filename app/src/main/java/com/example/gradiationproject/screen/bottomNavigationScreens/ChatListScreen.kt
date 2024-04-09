package com.example.gradiationproject.screen.bottomNavigationScreens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

@Composable
fun ChatListScreen() {

    // 1 - Set up the OfflinePlugin for offline storage
    val offlinePluginFactory = StreamOfflinePluginFactory(appContext = appContext)
    val statePluginFactory =
        StreamStatePluginFactory(config = StatePluginConfig(), appContext = appContext)

    // 2 - Set up the client for API calls and with the plugin for offline storage
    val client = ChatClient.Builder("jgpyg2janup5", appContext)
        .withPlugins(offlinePluginFactory, statePluginFactory)
        .logLevel(ChatLogLevel.ALL) // Set to NOTHING in prod
        .build()

    val user = User(
        id = "tutorial-droid",
        name = "Tutorial Droid",
        image = "https://bit.ly/2TIt8NR"
    )
    client.connectUser(
        user = user,
        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoidHV0b3JpYWwtZHJvaWQifQ.WwfBzU1GZr0brt_fXnqKdKhz3oj0rbDUm2DqJO_SS5U"
    ).enqueue()

    val clientInitialisationState by client.clientState.initializationState.collectAsState()

    ChatTheme {
        when (clientInitialisationState) {
            InitializationState.COMPLETE -> {
                ChannelsScreen(
                    title = stringResource(id = R.string.app_name),
                    isShowingSearch = true,
                    onItemClick = { channel ->
                        TODO()
                    },
                    onBackPressed = {  }
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

@Preview(showBackground = true)
@Composable
fun ChatListScreenPreview() {
    ChatListScreen()
}