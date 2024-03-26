package com.example.gradiationproject.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.gradiationproject.data.Post
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeScreenViewModel:ViewModel() {
    private val db = Firebase.firestore
    private val TAG = "HomeScreenViewModel"


    // State to hold the list of posts
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    fun retrievePostToFirestore() {
        db.collection("posts")
            .get()
            .addOnSuccessListener { result ->
                val postsList = mutableListOf<Post>()
                for (document in result) {
                    val post = document.toObject(Post::class.java)
                    postsList.add(post)
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                _posts.value = postsList
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}

