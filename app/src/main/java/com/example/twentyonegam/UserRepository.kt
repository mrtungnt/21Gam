package com.example.twentyonegam

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepository @Inject constructor(firebaseRepository: FirebaseRepository) {
    private val db = firebaseRepository.db

    private val tag = "EXCEPTION"

    fun queryUsersByName(name: String): Query =
        db.collection("users").whereEqualTo("name", name)

    fun queryAllUsers() = db.collection("users")

    suspend fun getUsersAsDocumentSnapShot(query: Query): List<DocumentSnapshot> {
        return try {
            query
                .get()
                .await()
                .documents

        } catch (e: Exception) {
            Log.d(tag, e.message ?: "?")
            emptyList<DocumentSnapshot>()
        }
    }
}