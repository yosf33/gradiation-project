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



     fun addPostToFirestore(title: String, price: String, description: String, numberOfParts: Int) {
         val post = Post(
             title = title,
             price = price,
             description= description,
             numberOfParts=numberOfParts
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

