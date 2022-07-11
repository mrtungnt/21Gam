package com.example.twentyonegam

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainViewModel : ViewModel() {
    private val db = Firebase.firestore
    var data by
    mutableStateOf("")

    fun TestQuery() {
        db.collection("users")
            .whereEqualTo("name", "Nam")
            .get()
            .addOnSuccessListener()
            { docs -> data = "${docs.firstOrNull()?.data?.get("name") as String} ${docs.firstOrNull()?.id}" }
    }
}