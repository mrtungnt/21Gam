package com.example.twentyonegam

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

const val ALL_USERS_QUERY = "all_users"
const val USERS_BY_NAME_QUERY = "users_by_name"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var workRepository: WorkRepository

    val allUserQuery: StateFlow<List<DocumentSnapshot>> =
        savedStateHandle.getStateFlow<List<DocumentSnapshot>>(
            ALL_USERS_QUERY,
            initialValue = emptyList()
        )

    val usersByNameQuery: StateFlow<List<DocumentSnapshot>> =
        savedStateHandle.getStateFlow<List<DocumentSnapshot>>(
            USERS_BY_NAME_QUERY,
            initialValue = emptyList()
        )

    suspend fun getAllUsersAsDocumentSnapshot() {
        savedStateHandle[ALL_USERS_QUERY] =
            userRepository.getUsersAsDocumentSnapShot(userRepository.queryAllUsers())
    }

    suspend fun getUsersByNameAsDocumentSnapshot(name: String) {
        savedStateHandle[USERS_BY_NAME_QUERY] =
            userRepository.getUsersAsDocumentSnapShot(userRepository.queryUsersByName(name))
    }

    suspend fun buildTechnicalDivisionWorkItems() {
        workRepository.buildTechnicalDivisionWorkItems()
    }

}