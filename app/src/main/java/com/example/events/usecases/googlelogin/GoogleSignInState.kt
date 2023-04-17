package com.example.events.usecases.googlelogin

import com.google.firebase.auth.AuthResult

data class GoogleSignInState(
    val loading: Boolean = false,
    val success: AuthResult? = null,
    val error: String? = ""
)
