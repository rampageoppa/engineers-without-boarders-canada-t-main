package com.example.missingseven

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.missingseven.Navigation.NavControl
import dagger.hilt.android.AndroidEntryPoint

/***
 * only activity in our project
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavControl(rememberNavController()).SetUpNavGraph()
        }
    }
}