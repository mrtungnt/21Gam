package com.example.twentyonegam

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class User {
    var id = ""
    var name = ""
    var number = 0
}

class UserRepository {
    private val firestore = FirebaseFirestore.getInstance();

    init {
        firestore.useEmulator("10.0.2.2", 8080);

        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build();
        firestore.firestoreSettings = settings;
    }

    private val db = Firebase.firestore

    private val tag = "EXCEPTION"

    suspend fun testQuery(): List<User?> {
        val l = mutableListOf<User?>()
        try {
            db.collection("users")
//                .whereEqualTo("name", "Nam")
                .get()
                .await()
                .documents
                .forEach { val u = it.toObject<User>(); u?.id = it.id;l.add(u) }

        } catch (e: Exception) {
            Log.d(tag, e.message ?: "?")
        }
        return l
    }
}