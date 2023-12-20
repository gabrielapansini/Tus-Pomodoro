package com.example.tuspomodoro.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class TimeTableViewModel: ViewModel() {


    //References: https://firebase.google.com/docs/android/setup
    //I learned in this website how to implement the firebase

    // Initialize Firestore instance
    private val db = Firebase.firestore

    //catch exception in case there is errors
    fun getWeeklySchedule(
        department: String, group: String
    ): Flow<Map<String, DaySchedule>> = callbackFlow {
        // Reference to the document containing the weekly schedule
        val docRef = db.collection("departments").document(department)
            .collection("groups").document(group)

        // Add a snapshot listener to receive real-time updates
        val listenerRegistration = docRef.addSnapshotListener { snapshot, e ->
            // Check for errors
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            // Process the snapshot if it exists and has data
            if (snapshot != null && snapshot.exists()) {
                Log.d(TAG, "Current data: ${snapshot.data}")

                // Map raw data to DaySchedule objects
                val weeklySchedule = snapshot.data?.mapValues {
                    DaySchedule(it.value as HashMap<String, Any>)
                }

                // Attempt to send the weekly schedule through the Flow
                if (weeklySchedule != null) {
                    try {
                        // Use trySend to send data through the Flow
                        trySend(weeklySchedule).isSuccess
                    } catch (ex: Exception) {
                        Log.e(TAG, "Failed to send data", ex)
                    }
                }
            } else {
                Log.d(TAG, "Current data: null")
            }
        }

        // Remove the listener when the Flow is closed
        awaitClose { listenerRegistration.remove() }
    }
}
