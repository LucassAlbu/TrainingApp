package com.albuquerque.trainingapp.repository

import com.google.firebase.auth.FirebaseUser
import com.projetoFirebase.util.DefaultListenerResponse

interface Repository {
    val currentUser: FirebaseUser?

    suspend fun loginFirebase(
        email: String,
        password: String,
        listenerResponse: DefaultListenerResponse<FirebaseUser, String>
    )

    suspend fun signUpFirebase(
        email: String,
        password: String,
        listenerResponse: DefaultListenerResponse<FirebaseUser, String>
    )

    suspend fun recoverAccountFireBase(
        email: String,
        listenerResponse: DefaultListenerResponse<FirebaseUser, String>
    )

    fun logout()
}