package com.example.twentyonegam

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
//    private val userRepository: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val queriedUsers: StateFlow<List<User?>> =
        savedStateHandle.getStateFlow<List<User?>>("users", initialValue = emptyList())
    /* by savedStateHandle.saveable() {
        mutableStateOf(emptyList())
    }*/


    private val userRepository = UserRepository()
    suspend fun testQuery() {
//        viewModelScope.launch { queriedUsers = userRepository.testQuery() }
        savedStateHandle["users"] = userRepository.testQuery()
    }
}