package com.example.tuspomodoro.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class TimeTableViewModel: ViewModel() {
    private val db = Firebase.firestore


    fun getWeeklySchedule(
        department: String, group: String
    ): Flow<Map<String, DaySchedule>> = callbackFlow {
        val docRef = db.collection("departments").document(department)
            .collection("groups").document(group)

        val listenerRegistration = docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                Log.d(TAG, "Current data: ${snapshot.data}")
                val weeklySchedule = snapshot.data?.mapValues {
                    DaySchedule(it.value as HashMap<String, Any>)
                }

                if (weeklySchedule != null) {
                    try {
                        trySend(weeklySchedule).isSuccess
                    } catch (ex: Exception) {
                        Log.e(TAG, "Failed to send data", ex)
                    }
                }
            } else {
                Log.d(TAG, "Current data: null")
            }
        }

        awaitClose { listenerRegistration.remove() }
    }




}