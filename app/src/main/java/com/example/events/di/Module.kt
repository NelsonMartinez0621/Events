package com.example.events.di

import android.content.Context
import androidx.room.Room
import com.example.events.database.Database
import com.example.events.database.EventDao
import com.example.events.firebase.FirestoreRepository
import com.example.events.firebase.FirestoreRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

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

    @Provides
    fun providesFirebaseAuth():FirebaseAuth = Firebase.auth

    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun providesFirestoreRepository(
        firestore: FirebaseFirestore
    ): FirestoreRepository {
        return FirestoreRepositoryImpl(firestore)
    }

    @Provides
    fun providesCoroutineScope(ioDispatcher: CoroutineDispatcher):CoroutineScope = CoroutineScope(ioDispatcher)
}

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun providesEventDao(appDatabase: Database): EventDao {
        return appDatabase.getEventsDao()
    }
    @Provides
    fun provideDB(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java, "database-events"
        ).build()
    }
}
