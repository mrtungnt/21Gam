package com.example.twentyonegam

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FirebaseRepository @Inject constructor() {
    private val firestore = FirebaseFirestore.getInstance();

    init {
        firestore.useEmulator("10.0.2.2", 8080);

        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build();
        firestore.firestoreSettings = settings;
    }

    val db = Firebase.firestore
}