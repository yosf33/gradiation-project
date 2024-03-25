package com.example.gradiationproject.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.gradiationproject.data.Post
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class AddPostViewModel: ViewModel() {
    private val db = Firebase.firestore
    private val TAG = "AddPostViewModel"



     fun addPostToFirestore() {
        val post = Post(
            "youssef",
            "25/3/2024",
            "need help in facebook"
        )
        db.collection("posts")
            .add(post)
            .addOnSuccessListener {
                Log.d(TAG, "Successfully added")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}

