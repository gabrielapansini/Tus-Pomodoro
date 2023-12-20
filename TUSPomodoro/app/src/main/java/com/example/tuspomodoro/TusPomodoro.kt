package com.example.tuspomodoro

import android.app.Application
import com.google.firebase.FirebaseApp

class TusPomodoro: Application() {

    //initialize firebase

    override fun onCreate(){
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}