package com.example.tuspomodoro.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthRegViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()


    //References: https://auth0.com/blog/get-started-with-android-authentication-using-kotlin-part-1/
    //I learned to implement the logic in this code lab

    //even if the user minimize the app, when returning it will be the same user ID
    // LiveData to hold the error message
    val errorMessage = MutableLiveData<String>()

    // LiveData to hold the user's id
    val userId = MutableLiveData<String?>()

    //Registration Function
    fun registerUser(email: String, password: String) {
        // Check if the email or password field is empty
        if (email.isEmpty() || password.isEmpty()) {
            errorMessage.postValue("email or password cannot be empty")
            return
        }
        //auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // User is registered
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        // Post the user ID to a LiveData
                        userId.postValue(currentUser.uid)

                    } else {
                        // Handle the case where currentUser is null
                        errorMessage.postValue("An error has occurred, Please Try again later")
                    }
                } else {
                    // Handle failure
                    errorMessage.postValue(task.exception?.message)
                }
            }
    }

    //Login User Function
    fun loginUser(email: String, password: String) {
        // Check if the email or password field is empty
        if (email.isEmpty() || password.isEmpty()) {
            errorMessage.postValue("email or password cannot be empty")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // User is logged in
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        // Post the user ID to a LiveData
                        userId.postValue(currentUser.uid)
                    } else {
                        // Handle the case where currentUser is null
                        errorMessage.postValue("An error has occurred, Please Try again later")
                    }
                } else {
                    // Handle failure
                    errorMessage.postValue(task.exception?.message)
                }
            }
    }



//    //Log out function
//    fun logoutUser() {
//        auth.signOut()
//        // Post null to the userId LiveData to indicate that the user is logged out
//        userId.postValue(null)
//    }


}