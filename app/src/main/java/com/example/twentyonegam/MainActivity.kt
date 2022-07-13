package com.example.twentyonegam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.twentyonegam.ui.theme.TwentyOneGamTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwentyOneGamTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = viewModel<MainViewModel>()

                    /*LaunchedEffect(
                        key1 = Unit, // Effect will follow the lifecycle of the call site.
                        // See https://developer.android.com/jetpack/compose/side-effects#restarting-effects
                    ) {
                        viewModel.getAllUsersAsDocumentSnapshot()
                        viewModel.getUsersByNameAsDocumentSnapshot("ME")
                    }*/

                    val allUsersQueryState = viewModel.allUserQuery.collectAsState()
                    val usersByNameQueryState = viewModel.usersByNameQuery.collectAsState()

                    Column {
                        val staffRoles by remember {
                            lazy { // lazyOf() is more convenient. This is used just to demonstrate:
                                println("Getting staff roles"); // The lambda ís executed only once:
                                // when staffRoles is accessed the first time.
                                // If staffRoles is never accessed, the lambda is never executed.
                                // See https://kotlinlang.org/docs/delegated-properties.html#lazy-properties
                                StaffRole.values()
                            }
                        }

                        allUsersQueryState.value.forEach {
                            key(it.id) { //  With key, even if the elements on the list change,
                                // Compose recognizes individual calls to composable Text and can reuse them.
                                // See https://developer.android.com/jetpack/compose/lifecycle#add-info-smart-recomposition
                                val role: Int? =
                                    it[KEY_ROLE]?.let { role -> (role as Long).toInt() }
                                Text(
                                    text = "${it[KEY_NAME]} " +
                                            "${
                                                if (role == null) {
                                                    "UNSPECIFIED ROLE"
                                                } else {
                                                    staffRoles[role]
                                                }
                                            }"
                                )
                            }
                        }

                        usersByNameQueryState.value.forEach {
                            key(it.id) {
                                Text(
                                    text = "${it[KEY_NAME]}"
                                )
                            }
                        }

                        val crScope = rememberCoroutineScope()
                        Button(onClick = { crScope.launch { viewModel.buildTechnicalDivisionWorkItems() } }) {
                            Text(text = "Build technical division work items")
                        }
                    }
                }
            }
        }
    }
}