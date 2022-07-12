package com.example.twentyonegam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.twentyonegam.ui.theme.TwentyOneGamTheme
import dagger.hilt.android.AndroidEntryPoint

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
                    val v = viewModel<MainViewModel>()
                    LaunchedEffect(key1 = Unit, block = { v.testQuery(); v.testQuery2() })
                    val state = v.queriedUsers.collectAsState()
                    val state2 = v.queriedUsers2.collectAsState()
                    Column() {
                        state.value.forEach { key(it?.id) { Text(text = "${it?.name} ${it?.number}") } }
                        state2.value.forEach { key(it.id) { Text(text = "${it["name"]} ${RoleEnum.values()[(it["role"] as Long).toInt()]}") } }
                    }
                }
            }
        }
    }
}