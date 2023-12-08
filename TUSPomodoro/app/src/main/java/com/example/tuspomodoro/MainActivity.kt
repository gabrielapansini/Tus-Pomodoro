package com.example.tuspomodoro

import ToDoList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tuspomodoro.ui.CustomBoxWithText
import com.example.tuspomodoro.ui.LoginScreen
import com.example.tuspomodoro.ui.Screen
import com.example.tuspomodoro.ui.SignUpScreen
import com.example.tuspomodoro.ui.TimetableScreen
import com.example.tuspomodoro.ui.theme.TUSPomodoroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TUSPomodoroTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    //CustomBoxWithText()
                    //LoginScreen()
                    NavGraph()

                }
            }
        }
    }
}

@Composable
fun NavGraph(startDestination: String = Screen.SignUpScreen.route){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination){
        composable(Screen.SignUpScreen.route){ SignUpScreen(navController)}
        composable(Screen.LoginScreen.route){ LoginScreen()}
        composable(Screen.PomodoroScreen.route){ CustomBoxWithText()}
        composable(Screen.TimeTableScreen.route){ TimetableScreen()}
        composable(Screen.ToDoListScreen.route){ ToDoList()}
    }
}
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TUSPomodoroTheme {
        CustomBoxWithText()
    }
}



