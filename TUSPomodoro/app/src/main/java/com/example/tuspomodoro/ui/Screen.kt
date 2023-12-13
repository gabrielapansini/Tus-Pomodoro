package com.example.tuspomodoro.ui

sealed class Screen(val route: String){
    object LoginScreen: Screen("login")
    object SignUpScreen: Screen("signup")
    object ToDoListScreen: Screen("todolist")
    object TimeTableScreen: Screen("timetable")

    object PomodoroScreen: Screen("pomodoro")

    object Contact: Screen("contact")
}
