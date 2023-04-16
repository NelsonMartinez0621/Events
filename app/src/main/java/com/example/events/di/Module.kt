package com.example.events.di

import com.example.events.database.Database
import com.example.events.database.EventDao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
/*
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    abstract fun bindLocalRepository(
        localRepositoryImpl: LocalRepositoryImpl
    ): LocalRepository

    @Binds
    abstract fun bindFirestoreRepository(
        firestoreRepositoryImpl: FirestoreRepositoryImpl
    ): FirestoreRepository

}*/

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesFireStore(): FirebaseFirestore = Firebase.firestore

}

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun providesEventDao(appDatabase: Database): EventDao {
        return appDatabase.getEventsDao()
    }
}
