package com.example.twentyonegam

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val queriedUsers: StateFlow<List<User?>> =
        savedStateHandle.getStateFlow<List<User?>>("users", initialValue = emptyList())

    val queriedUsers2: StateFlow<List<DocumentSnapshot>> =
        savedStateHandle.getStateFlow<List<DocumentSnapshot>>("users2", initialValue = emptyList())

    suspend fun testQuery() {
        savedStateHandle["users"] = userRepository.testQuery()
    }

    suspend fun testQuery2() {
        savedStateHandle["users2"] = userRepository.testQuery2()
    }

}