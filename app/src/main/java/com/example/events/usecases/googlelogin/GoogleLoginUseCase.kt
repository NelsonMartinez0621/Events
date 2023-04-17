package com.example.events.usecases.googlelogin

import com.example.events.util.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GoogleLoginUseCase @Inject constructor(
    private val auth: FirebaseAuth
        ){

    fun googleLogin(credential: AuthCredential): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading())
        //This one takes a credential rather than email and password
        val result = auth.signInWithCredential(credential).await()
        emit(Resource.Success(result))
    }.catch {
        //Catch the exceptions
        emit(Resource.Error(it.message.toString()))
    }
}