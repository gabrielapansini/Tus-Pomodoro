package com.example.tuspomodoro

import android.app.Application
import com.google.firebase.FirebaseApp

class TusPomodoro: Application() {
    override fun onCreate(){
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}