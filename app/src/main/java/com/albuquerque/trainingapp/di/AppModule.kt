package com.albuquerque.trainingapp.di

import com.albuquerque.trainingapp.repository.Repository
import com.albuquerque.trainingapp.repository.RepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(
        firebaseAuth: FirebaseAuth
    ): Repository {
        return RepositoryImpl(firebaseAuth)
    }

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}