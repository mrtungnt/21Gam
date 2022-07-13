package com.example.twentyonegam

import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class WorkRepository @Inject constructor(firebaseRepository: FirebaseRepository) {
    private val db = firebaseRepository.db

    suspend fun buildTechnicalDivisionWorkItems() {
        val collectionSize = db.collection("tech_work_items").get().await().size()
        if (collectionSize > 0) return

        val workItemList = mutableListOf<Map<String, *>>()
        var id = 0
        tech_work_item_literature.forEach() {
            val item = mapOf("work_item" to it, "id" to id); workItemList.add(item); id++
        }

        workItemList.forEach() { db.collection("tech_work_items").add(it).await() }
    }
}