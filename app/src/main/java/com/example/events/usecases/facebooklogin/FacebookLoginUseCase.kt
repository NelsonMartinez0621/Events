package com.example.events.usecases.facebooklogin

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FacebookLoginUseCase @Inject constructor(
    private val auth: FirebaseAuth
        ) {


    fun facebookLogin(credential: AuthCredential) {
        auth.signInWithCredential(credential)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }
}