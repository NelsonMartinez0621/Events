package com.example.events.usecases.login

import com.example.events.util.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val auth: FirebaseAuth
){
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading())

        val result = auth.signInWithEmailAndPassword(email, password).await()
        //await means we will wait to define this variable until signInWithEmailAndPassword is done

        emit(Resource.Success(result))
    }.catch {
        //Catch the exceptions
        emit(Resource.Error(it.message.toString()))
    }

    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>> = flow {
        //pretty much the same as loginUser, just a different result method; createUser instead
        //login
        emit(Resource.Loading())

        val result = auth.createUserWithEmailAndPassword(email, password).await()

        emit(Resource.Success(result))
    }.catch {
        //Catch the exceptions
        emit(Resource.Error(it.message.toString()))
    }
}