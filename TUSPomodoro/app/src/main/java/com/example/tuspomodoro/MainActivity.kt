package com.example.tuspomodoro

import ContactUs
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tuspomodoro.ui.AuthRegViewModel
import com.example.tuspomodoro.ui.LoginScreen
import com.example.tuspomodoro.ui.Pomodoro
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

//References: https://developer.android.com/guide/navigation/get-started


//function to navigate
//representing the navigation graph of the application.
//startDestination The starting destination of the navigation graph.
@Composable
fun NavGraph(startDestination: String = Screen.SignUpScreen.route) {
    // Create a NavController to navigate between composables
    val navController = rememberNavController()

    //Create an instance of AuthRegViewModel to handle authentication and registration logic
    val viewModel: AuthRegViewModel = viewModel()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.SignUpScreen.route) { SignUpScreen(navController) }
        composable(Screen.LoginScreen.route) { LoginScreen(navController) }
        composable(Screen.PomodoroScreen.route) { Pomodoro(navController, userId = null) }
        composable(Screen.Contact.route) { ContactUs(navController) }
        composable(Screen.TimeTableScreen.route) { TimetableScreen(navController) }
        composable(Screen.ToDoListScreen.route) { ToDoList(navController) }
        composable("pomodoro/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            Pomodoro(navController, userId)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TUSPomodoroTheme {
        // CustomBoxWithText()
    }
}



