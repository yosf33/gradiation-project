package com.example.gradiationproject.screen.bottomNavigationScreens

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gradiationproject.MyApplication.Companion.appContext
import com.example.gradiationproject.components.ButtonComponent
import com.example.gradiationproject.components.ButtonComponent1
import com.example.gradiationproject.data.Post
import com.example.gradiationproject.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {
    val posts by viewModel.posts.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.retrievePostToFirestore()
    }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn {
                items(posts) { post ->
                    PostCard(post = post)
                }
            }
        }
    }
}

@Composable
fun PostCard(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation =  CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "Title: ${post.title}", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Price: ${post.price}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Description: ${post.description}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Number of Parts: ${post.numberOfParts}")

            ButtonComponent1("Apply Now",{val toast = Toast.makeText(appContext, "You have applied", Toast.LENGTH_SHORT)
                toast.show()},true)
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(viewModel = HomeScreenViewModel())
}
