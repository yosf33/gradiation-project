package com.example.gradiationproject.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class HomeScreenViewModel:ViewModel() {
    private val db = Firebase.firestore
    private val TAG = "HomeScreenViewModel"




    fun retrievePostToFirestore() {
        db.collection("posts")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}

